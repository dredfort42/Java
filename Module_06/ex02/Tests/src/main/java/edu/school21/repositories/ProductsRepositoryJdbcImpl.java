package edu.school21.repositories;


import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM product;";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            productList.add(new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price")
            ));
        };
        statement.close();
        connection.close();
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Optional<Product> optionalProduct;
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM product WHERE id = ".concat(id.toString()).concat(";");
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        optionalProduct = Optional.of(new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price")
                )
        );
        statement.close();
        connection.close();
        return optionalProduct;
    }

    @Override
    public void update(Product product) throws SQLException {
        String query = "UPDATE product SET name = ?, price = ? WHERE id = ?;";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.setLong(3, product.getId());
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void save(Product product) throws SQLException {
        String query = "INSERT INTO product(name, price) VALUES (?, ?);";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String query = "DELETE FROM product WHERE id = ".concat(id.toString()).concat(";");
        statement.executeQuery(query);
        statement.close();
        connection.close();
    }
}
