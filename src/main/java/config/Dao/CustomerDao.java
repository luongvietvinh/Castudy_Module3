package config.Dao;

import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
ConnectionJDBC connectionJDBC = new ConnectionJDBC();


    public void createCustomerDao(Customer customer) {
        String saveCustomer = "INSERT INTO customer (full_name,passwords,email,phone,address,img,create_date,modify_date) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connectionJDBC.getConnection().prepareStatement(saveCustomer);
            preparedStatement.setString(1, customer.getFull_name());
            preparedStatement.setString(2, customer.getPasswords());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setString(5, customer.getAddress());
//            preparedStatement.setInt(6, customer.getId_role());
            preparedStatement.setString(6, customer.getImg());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Customer> showListCustomer() {
        String ShowCustomer = "select customer.*,role.name from customer join role on customer.id_role = role.id";
        List<Customer> customerList = new ArrayList<>();

        try (Connection connection = connectionJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ShowCustomer)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id  = rs.getInt("id");
                String full_name = rs.getString("full_name");
                String passwords = rs.getString("passwords");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String img = rs.getString("img");
                Date create_date = (rs.getDate("create_date"));
                Date modify_date =(rs.getDate("modify_date"));
                String name_role = rs.getString("name");
                customerList.add(new Customer(id, full_name, passwords,email,phone,address,img,create_date,modify_date,name_role));

            }
          return customerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void deleteCustomeDao(int id) {
        String deleteSQL = "DELETE  from custome where id=?";
        try {
            PreparedStatement preparedStatement = connectionJDBC.getConnection().prepareStatement(deleteSQL);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    public void updateCustomer(int id, Customer customer){
        String editCustomer = "update student set full_name = ? ,passwords = ? ,email = ?,phone = ?,address = ? ,id_role = ?,img = ? where id = ?";

        try (Connection connection = connectionJDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(editCustomer)) {
            statement.setString(1, customer.getFull_name());
            statement.setString(2, customer.getPasswords());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getAddress());
            statement.setInt(6, customer.getId_role());
            statement.setString(7, customer.getImg());
            statement.setInt(8,id);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> searchByName(String findname) {
        String getall = "select customer.* from customer" +
                "  where customer.full_name like '%" + findname +"%\'";


        try {
            Statement statement = connectionJDBC.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(getall);
            List<Customer> customerList = new ArrayList<>();
            while (rs.next()) {
                int id  = rs.getInt("id");
                String full_name = rs.getString("full_name");
                String passwords = rs.getString("passwords");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String img = rs.getString("img");
                Date create_date = Date.valueOf(rs.getString("create_date"));
                Date modify_date = Date.valueOf(rs.getString("modify_date"));
                String name_role = rs.getString("name");
                customerList.add(new Customer(id, full_name, passwords,email,phone,address,img,create_date,modify_date,name_role));

            }
            return customerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    }
