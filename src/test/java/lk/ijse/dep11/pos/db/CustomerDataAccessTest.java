package lk.ijse.dep11.pos.db;

import lk.ijse.dep11.pos.tm.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class CustomerDataAccessTest {

    @Test
    void sqlSyntax() {
        assertDoesNotThrow(()-> Class.forName("lk.ijse.dep11.pos.db.CustomerDataAccess"));
    }

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
    void saveCustomer() {
        assertDoesNotThrow(()->{
            CustomerDataAccess.saveCustomer(new Customer("ABC", "Kasun", "Galle"));
            CustomerDataAccess.saveCustomer(new Customer("EDF", "Ruwan", "Galle"));
            assertThrows(SQLException.class, ()-> CustomerDataAccess
                    .saveCustomer(new Customer("ABC", "Kasun", "Galle")));
        });
    }

    @Test
    void getAllCustomers() throws SQLException {
        CustomerDataAccess.saveCustomer(new Customer("ABC", "Kasun", "Galle"));
        CustomerDataAccess.saveCustomer(new Customer("EDF", "Ruwan", "Galle"));
        assertDoesNotThrow(()->{
            List<Customer> customerList = CustomerDataAccess.getAllCustomers();
            assertTrue(customerList.size() >= 2);
        });
    }

    @Test
    void updateCustomer() throws SQLException {
        CustomerDataAccess.saveCustomer(new Customer("ABC", "Kasun", "Galle"));
        assertDoesNotThrow(()-> CustomerDataAccess
                .updateCustomer(new Customer("ABC", "Ruwan", "Matara")));
    }

    @Test
    void deleteCustomer() throws SQLException {
        CustomerDataAccess.saveCustomer(new Customer("ABC", "Kasun", "Galle"));
        int size = CustomerDataAccess.getAllCustomers().size();
        assertDoesNotThrow(()-> {
            CustomerDataAccess.deleteCustomer("ABC");
            assertEquals(size - 1, CustomerDataAccess.getAllCustomers().size());
        });
    }

    @Test
    void getLastCustomerId() throws SQLException {
        String lastCustomerId = CustomerDataAccess.getLastCustomerId();
        if (CustomerDataAccess.getAllCustomers().isEmpty()){
            assertNull(lastCustomerId);
        }else{
            CustomerDataAccess.saveCustomer(new Customer("ABC", "Kasun", "Galle"));
            lastCustomerId = CustomerDataAccess.getLastCustomerId();
            assertNotNull(lastCustomerId);
        }
    }
}