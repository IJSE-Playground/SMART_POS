package lk.ijse.dep11.pos.db;

import lk.ijse.dep11.pos.tm.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDataAccess {

    private static final PreparedStatement STM_INSERT;
    private static final PreparedStatement STM_UPDATE;
    private static final PreparedStatement STM_DELETE;
    private static final PreparedStatement STM_GET_ALL;

    static {
        try {
            Connection connection = SingleConnectionDataSource.getInstance().getConnection();
            STM_GET_ALL = connection.prepareStatement("SELECT * FROM customer");
            STM_INSERT = connection
                    .prepareStatement("INSERT INTO customer (id, name, address) VALUES (?, ?, ?)");
            STM_UPDATE = connection
                    .prepareStatement("UPDATE customer SET name=?, address=? WHERE id=?");
            STM_DELETE = connection.prepareStatement("DELETE FROM customer WHERE id=?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Customer> getAllCustomers() throws SQLException {
        ResultSet rst = STM_GET_ALL.executeQuery();
        List<Customer> customerList = new ArrayList<>();
        while (rst.next()) {
            String id = rst.getString("id");
            String name = rst.getString("name");
            String address = rst.getString("address");
            customerList.add(new Customer(id, name, address));
        }
        return customerList;
    }

    public static void saveCustomer(Customer customer) throws SQLException {
        STM_INSERT.setString(1, customer.getId());
        STM_INSERT.setString(2, customer.getName());
        STM_INSERT.setString(3, customer.getAddress());
        STM_INSERT.executeUpdate();
    }

    public static void updateCustomer(Customer customer) throws SQLException {
        STM_UPDATE.setString(1, customer.getName());
        STM_UPDATE.setString(2, customer.getAddress());
        STM_UPDATE.setString(3, customer.getId());
        STM_UPDATE.executeUpdate();
    }

    public static void deleteCustomer(String customerId) throws SQLException {
        STM_DELETE.setString(1, customerId);
        STM_DELETE.executeUpdate();
    }

    public static String getLastCustomerId() throws SQLException {
        List<Customer> customerList = getAllCustomers();
        return customerList.isEmpty() ? null : customerList.get(customerList.size() - 1).getId();
    }

}