package edu.school21.app;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;

import javax.sql.DataSource;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.*;

public class OrmManager {
	private String packageName;
	private Connection connection;


	public OrmManager(String packageName, DataSource dataSource) {
		this.packageName = packageName;
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public void init() {
		String scannedPath = packageName.replace('.', '/');
		URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
		if (scannedUrl == null) { System.out.println("Not found classes!"); }
		File scannedDir = new File(scannedUrl.getFile());
		try {
			PreparedStatement statement;
			for (File file : scannedDir.listFiles()) {
				String className = file.getName().replace(".class", "");
				Class<?> aClass = Class.forName(packageName + className);

				if (aClass.isAnnotationPresent(OrmEntity.class)) {
					OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
					String sql_drop_table = "DROP TABLE " + entity.table() + " IF EXISTS;";
					statement = connection.prepareStatement(sql_drop_table);
					statement.execute();
					System.out.println(sql_drop_table);
					String sql_create_table = "CREATE TABLE " + entity.table() + "(\n";
					for (Field f : aClass.getDeclaredFields()) {
						if (f.isAnnotationPresent(OrmColumnId.class)) {
							String typeName = f.getType().getSimpleName();
							if (typeName.equals("Long")) {
								sql_create_table += "\t" + f.getName() + " BIGINT PRIMARY KEY,\n";
							} else {
								throw new IllegalArgumentException(aClass.getName() + " PRIMARY KEY field \"" + f.getName() + "\" must be a Long type!");
							}
						} else if (f.isAnnotationPresent(OrmColumn.class)) {
							OrmColumn column = f.getAnnotation(OrmColumn.class);
							sql_create_table += "\t" + column.name() + " " +
									sqlType(f.getType().getSimpleName(), 10, aClass.getName() + "." + f.getName()) + ",\n";
						}
					}
					sql_create_table += ");";
					statement = connection.prepareStatement(sql_create_table);
					statement.execute();
					System.out.println(sql_create_table);
				}
			}
		} catch (ClassNotFoundException | IllegalArgumentException | SQLException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	private String sqlType(String typeName, int length, String classWithField) {
		if (typeName.equals("Integer")) { return "INT"; }
		else if (typeName.equals("Long")) {	return "BIGINT"; }
		else if (typeName.equals("String")) { return "VARCHAR(" + length + ")"; }
		else if (typeName.equals("Double")) { return "DOUBLE"; }
		else if (typeName.equals("Boolean")) { return "BOOLEAN"; }
		else {
			throw new IllegalArgumentException(classWithField + " contains not valid type \"" + typeName+"\"!");
		}
	}

	public void save(Object object) {
		Class<?> aClass = object.getClass();
		if (aClass.isAnnotationPresent(OrmEntity.class)) {
			try {
				OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
				String sql_save = "INSERT INTO " + entity.table() + "(id, ";
				String sql_fields = "";
				String sql_values = "";
				String id = "1, ";
				for (Field f : aClass.getDeclaredFields()) {
					f.setAccessible(true);
					if (f.isAnnotationPresent(OrmColumnId.class)) {
						id = f.get(object).toString() + ", ";
					} else if (f.isAnnotationPresent(OrmColumn.class)) {
						OrmColumn column = f.getAnnotation(OrmColumn.class);
						sql_fields += column.name() + ", ";
						if (f.getType().getSimpleName().equals("String")) {
							sql_values += "'" + f.get(object) + "', ";
						} else {
							sql_values += f.get(object) + ", ";
						}
					}
				}
				if (sql_fields.equals("") && sql_values.equals("")) {
					System.out.println("Not data for save!");
					return;
				}
				sql_fields = sql_fields.substring(0, sql_fields.length() - 2);
				sql_values = sql_values.substring(0, sql_values.length() - 2);
				sql_save += sql_fields + ") VALUES (" + id + sql_values + ");";
				PreparedStatement statement = connection.prepareStatement(sql_save);
				statement.execute();
				System.out.println(sql_save);
			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("The id field must be an unique! The record was not saved!");
			} catch (IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public <T> T findById(Long id, Class<T> aClass) {
		if (aClass.isAnnotationPresent(OrmEntity.class)) {
			try {
				OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
				String sql_find_by_id = "SELECT * FROM " + entity.table() + " WHERE id = " + id + ";";
				PreparedStatement statement = connection.prepareStatement(sql_find_by_id);
				statement.execute();
				System.out.println(sql_find_by_id);
				ResultSet resultSet = statement.getResultSet();
				if(resultSet.next()) {
					T object = aClass.newInstance();
					for (Field f : aClass.getDeclaredFields()) {
						f.setAccessible(true);
						if (f.isAnnotationPresent(OrmColumnId.class)) {
							f.set(object, resultSet.getLong("id"));
						} else if (f.isAnnotationPresent(OrmColumn.class)) {
							OrmColumn column = f.getAnnotation(OrmColumn.class);
							f.set(object, resultSet.getObject(column.name()));
						}
					}
					return object;
				}
				System.out.println("The record was not found!");
				return null;
			} catch (IllegalAccessException | SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				System.out.println("Not found constructor without parameters!");
				return null;
			}
		}
		System.out.println("The class is not associated with a database!");
		return null;
	}

	public void update(Object object) {
		Class<?> aClass = object.getClass();
		if (aClass.isAnnotationPresent(OrmEntity.class)) {
			try {
				OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
				String sql_update = "UPDATE " + entity.table() + " SET ";
				String id = "1";
				for (Field f : aClass.getDeclaredFields()) {
					f.setAccessible(true);
					if (f.isAnnotationPresent(OrmColumnId.class)) {
						id = f.get(object).toString();
					} else if (f.isAnnotationPresent(OrmColumn.class)) {
						OrmColumn column = f.getAnnotation(OrmColumn.class);
						sql_update += column.name() + " = ";
						if (f.getType().getSimpleName().equals("String")) {
							sql_update += "'" + f.get(object) + "', ";
						} else {
							sql_update += f.get(object) + ", ";
						}
					}
				}
				sql_update = sql_update.substring(0, sql_update.length() - 2);
				sql_update += " WHERE id = " + id + ";";
				PreparedStatement statement = connection.prepareStatement(sql_update);
				statement.execute();
				System.out.println(sql_update);
			} catch (IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
