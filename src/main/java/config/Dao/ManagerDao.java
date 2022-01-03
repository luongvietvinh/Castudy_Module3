package config.Dao;
import model.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDao {
    ConnectionJDBC connectionJDBC = new ConnectionJDBC();

    public void createManagerDao(Admin admin) {
        String saveManager = "INSERT INTO manager (user_name,passwords,full_name,email,phone,address,img,salary,coefficients_salary,id_role,status,create_date,modify_date) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connectionJDBC.getConnection().prepareStatement(saveManager);
            preparedStatement.setString(1, admin.getUser_name());
            preparedStatement.setString(2, admin.getPasswords());
            preparedStatement.setString(3, admin.getFull_name());
            preparedStatement.setString(4, admin.getEmail());
            preparedStatement.setString(5, admin.getPhone());
            preparedStatement.setString(6, admin.getAddress());
            preparedStatement.setInt(7, admin.getId_role());
            preparedStatement.setString(8, admin.getImg());
            preparedStatement.setDouble(9,admin.getSalary());
            preparedStatement.setDouble(10,admin.getCoefficients_salary());
            preparedStatement.setString(11,admin.getStatus());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Admin> showListManager() {
        String showManager = "select manager.*,role.name from manager join role on manager.id_role = role.id";
        List<Admin> admins = new ArrayList<>();

        try (Connection connection = connectionJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(showManager)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id  = rs.getInt("id");

                String user_name = rs.getString("user_name");
                String passwords = rs.getString("passs_word");
                String full_name = rs.getString("full_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String img = rs.getString("img");
                double salary = rs.getDouble("salary");
                double coefficients_salary = rs.getDouble("coefficients_salary");
                String status = rs.getString("status");
                Date create_date = (rs.getDate("create_date"));
                Date modify_date =(rs.getDate("modify_date"));
                String name_role = rs.getString("name");
                admins.add(new Admin(id, user_name, passwords,full_name,email,phone,address,img,salary,coefficients_salary,status,create_date,modify_date,name_role));

            }
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void deleteManagerDao(int id) {
        String deleteSQL = "DELETE  from manager where id=?";
        try {
            PreparedStatement preparedStatement = connectionJDBC.getConnection().prepareStatement(deleteSQL);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    public void updateManager(int id,Admin admin ){
        String editManager = "update manager set full_name = ? ,passwords = ? ,email = ?,phone = ?,address = ? ,id_role = ?,img = ? ,salary = ?,coefficients_salary = ? where id = ?";

        try (Connection connection = connectionJDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(editManager)) {
            statement.setString(1, admin.getUser_name());
            statement.setString(2, admin.getPasswords());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getPhone());
            statement.setString(5, admin.getAddress());
            statement.setInt   (6, admin.getId_role());
            statement.setString(7, admin.getImg());
            statement.setDouble(8, admin.getSalary());
            statement.setDouble(9, admin.getCoefficients_salary());
            statement.setInt(10,id);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Admin> searchByName(String findname) {
        String getall = "select manager.* from manager" +
                "  where manager.full_name like '%" + findname +"%\'";


        try {
            Statement statement = connectionJDBC.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(getall);
            List<Admin> managerList = new ArrayList<>();
            while (rs.next()) {
                int id  = rs.getInt("id");
                String user_name = rs.getString("user_name");
                String passwords = rs.getString("passs_word");
                String full_name = rs.getString("full_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String img = rs.getString("img");
                double salary = rs.getDouble("salary");
                double coefficients_salary = rs.getDouble("coefficients_salary");
                String status = rs.getString("status");
                Date create_date = Date.valueOf(rs.getString("create_date"));
                Date modify_date = Date.valueOf(rs.getString("modify_date"));
                String name_role = rs.getString("name");
                managerList.add(new Admin(id, user_name, passwords,full_name,email,phone,address,img,salary,coefficients_salary,status,create_date,modify_date,name_role));

            }
            return managerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
