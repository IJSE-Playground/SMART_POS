package lk.ijse.dep11.pos.db;

import lk.ijse.dep11.pos.tm.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class OrderDataAccessTest {

    @BeforeEach
    void setUp() throws SQLException {
        SingleConnectionDataSource.getInstance().getConnection().setAutoCommit(false);
    }

    @AfterEach
    void tearDown() throws SQLException {
        SingleConnectionDataSource.getInstance().getConnection().rollback();
        SingleConnectionDataSource.getInstance().getConnection().setAutoCommit(true);
    }

    @Test
    void existsOrderByCustomerId() throws SQLException {
        assertDoesNotThrow(()-> assertFalse(OrderDataAccess.existsOrderByCustomerId("ABC")));
        CustomerDataAccess.saveCustomer(new Customer("ABC", "Crazy", "Panadura"));
        SingleConnectionDataSource.getInstance().getConnection().createStatement()
                .executeUpdate("INSERT INTO \"order\" (id, customer_id) VALUES ('111111', 'ABC')");
        assertDoesNotThrow(()-> assertTrue(OrderDataAccess.existsOrderByCustomerId("ABC")));
    }
}