package config.Dao;

import model.Admin;
import model.Customer;

import java.sql.*;

public class LoginDao {
    ConnectionJDBC connectionJDBC = new ConnectionJDBC();


//    public Admin login(String user, String pass) {
//        String query = "select * from manager \n" +
//                "where user_name = ? and passs_word = ?;";
//
//        try {
//            Connection conn = connectionJDBC.getConnection();
//            PreparedStatement ps = conn.prepareStatement(query);
//
//            ps.setString(1, user);
//            ps.setString(2, pass);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int id  = rs.getInt("id");
//                String full_name = rs.getString("full_name");
//                String passwords = rs.getString("passwords");
//                String email = rs.getString("email");
//                String phone = rs.getString("phone");
//                String address = rs.getString("address");
//                String img = rs.getString("img");
//                Date create_date = (rs.getDate("create_date"));
//                Date modify_date =(rs.getDate("modify_date"));
//                String name_role = rs.getString("name");
//                customerList.add(new Customer(id, full_name, passwords,email,phone,address,img,create_date,modify_date,name_role));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}