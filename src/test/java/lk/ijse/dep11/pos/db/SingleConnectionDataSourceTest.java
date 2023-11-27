package lk.ijse.dep11.pos.db;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class SingleConnectionDataSourceTest {

    @Test
    void getConnection() {
        assertDoesNotThrow(SingleConnectionDataSource.getInstance()::getConnection);
    }

    @Test
    void getInstance() {
        var instance1 = SingleConnectionDataSource.getInstance();
        var instance2 = SingleConnectionDataSource.getInstance();
        var instance3 = SingleConnectionDataSource.getInstance();
        assertEquals(instance1, instance2);
        assertEquals(instance2, instance3);
    }
}