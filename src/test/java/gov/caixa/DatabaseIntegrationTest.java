package gov.caixa;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class DatabaseIntegrationTest {

    @Inject
    DataSource dataSource;

    @Test
    public void testDatabaseConnection() throws Exception {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 AS result");
                ResultSet resultSet = preparedStatement.executeQuery()) {

            assertTrue(resultSet.next(), "Deveria retornar pelo menos uma linha");
            assertEquals(1, resultSet.getInt("result"), "O resultado da query deve ser 1");
        }
    }
}
