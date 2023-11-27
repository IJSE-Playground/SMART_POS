package lk.ijse.dep11.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingleConnectionDataSource {
    private static SingleConnectionDataSource instance;
    private final Connection connection;

    private SingleConnectionDataSource() {
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("/application.properties"));
            String username = properties.getProperty("app.datasource.username");
            String password = properties.getProperty("app.datasource.password");
            String url = properties.getProperty("app.datasource.url");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static SingleConnectionDataSource getInstance() {
        return (instance == null) ? (instance = new SingleConnectionDataSource()) : instance;
    }
}