package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    private DataSource dataSource;
    private ProductsRepository productsRepository;

    private final List<Product> findAllExpectedResult = Arrays.asList(
            new Product(0L, "BMW", 4343888),
            new Product(1L, "VW", 2454523),
            new Product(2L, "Lada", 1023421)
    );
    private final Product findByIdExpectedResult = findAllExpectedResult.get(2);
    private final Product updateExpectedResult = new Product(3L, "UPDATE TEST", 98765);
    private final Product saveExpectedResult = new Product(3L, "SAVE TEST", 43210);

    @BeforeEach
    void dataPreset() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void dataSourceGetConnectionTest() throws SQLException {
        Assertions.assertNotNull(dataSource.getConnection());
    }

    @ParameterizedTest
    @ValueSource (ints = {0, 1, 2})
    void findAllTest(int line) throws SQLException {
        Assertions.assertEquals(findAllExpectedResult.get(line).getId(), productsRepository.findAll().get(line).getId());
        Assertions.assertEquals(findAllExpectedResult.get(line).getName(), productsRepository.findAll().get(line).getName());
        Assertions.assertEquals(findAllExpectedResult.get(line).getPrice(), productsRepository.findAll().get(line).getPrice());
    }

    @Test
    void findByIdTest() throws SQLException {
        Assertions.assertEquals(findByIdExpectedResult.getId(), productsRepository.findById(2L).get().getId());
        Assertions.assertEquals(findByIdExpectedResult.getName(), productsRepository.findById(2L).get().getName());
        Assertions.assertEquals(findByIdExpectedResult.getPrice(), productsRepository.findById(2L).get().getPrice());
    }

    @Test
    void saveTest() throws SQLException {
        productsRepository.save(saveExpectedResult);
        Assertions.assertEquals(saveExpectedResult.getId(), productsRepository.findById(3L).get().getId());
        Assertions.assertEquals(saveExpectedResult.getName(), productsRepository.findById(3L).get().getName());
        Assertions.assertEquals(saveExpectedResult.getPrice(), productsRepository.findById(3L).get().getPrice());
    }

    @Test
    void updateTest() throws SQLException {
        productsRepository.update(updateExpectedResult);
        Assertions.assertEquals(updateExpectedResult.getId(), productsRepository.findById(3L).get().getId());
        Assertions.assertEquals(updateExpectedResult.getName(), productsRepository.findById(3L).get().getName());
        Assertions.assertEquals(updateExpectedResult.getPrice(), productsRepository.findById(3L).get().getPrice());
    }

    @Test
    void testDelete() throws SQLException {
        productsRepository.delete(1L);
        Assertions.assertThrows(SQLException.class, () -> productsRepository.findById(1L));
    }
}