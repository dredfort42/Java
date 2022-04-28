package edu.school21.app;

import edu.school21.models.Car;
import edu.school21.models.User;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

public class Program {
	private static final String PACKAGE_NAME = "edu.school21.models.";
	private EmbeddedDatabase dataSource;
	private OrmManager manager;


	public static void main(String[] args) {
		Program program = new Program();
		program.dataSource = new EmbeddedDatabaseBuilder().build();
		program.manager = new OrmManager(PACKAGE_NAME, program.dataSource);
		program.manager.init();
		program.testSave();
		program.testFind();
		program.testUpdate();;
		program.dataSource.shutdown();
	}


	private void testSave(){
		System.out.println("[SAVE TEST]");
		User user1 = new User(1L, "Dima", "No", 40);
		User user2 = new User(2L, "Artem", "On", 9);
		manager.save(user1);
		manager.save(user2);

		Car car1 = new Car(1L, "LADA", 1200000.12, 180, true);
		Car car2 = new Car(2L, "BMW", 4100000.14, 240, false);
		manager.save(car1);
		manager.save(car2);
	}

	private void testFind() {
		System.out.println("[FIND TEST]");
		User user;
		if ((user = manager.findById(2L, User.class)) != null) {
			System.out.println(user);
		}
		Car car;
		if ((car = manager.findById(1L, Car.class)) != null) {
			System.out.println(car);
		}
	}

	private void testUpdate() {
		System.out.println("[UPDATE TEST]");
		User user;
		if ((user = manager.findById(1L, User.class)) != null) {
			System.out.println("Before update:" + user);
		}
		user = new User(1L, "Metra", "NOON", 88);
		manager.update(user);

		if ((user = manager.findById(1L, User.class)) != null) {
			System.out.println("After update:" + user);
		}

		Car car;
		if ((car = manager.findById(2L, Car.class)) != null) {
			System.out.println("Before update:" + car);
		}
		car = new Car(2L, "OKA", 555555.55, 120, false);
		manager.update(car);

		if ((car = manager.findById(2L, Car.class)) != null) {
			System.out.println("After update:" + car);
		}
	}
}
