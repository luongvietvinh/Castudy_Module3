package config.Dao;

import model.Customer;
import model.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
ConnectionJDBC connectionJDBC = new ConnectionJDBC();

//
//    public Manager login(String user , String pass) {
//        String query = "select * from manager \n" +
//                "where user_name = ? and passs_word = ?;";
//
//        try {
//            Connection conn = connectionJDBC.getConnection();
//            PreparedStatement ps = conn.prepareStatement(query);
//
//            ps.setString(1,user);
//            ps.setString(2,pass);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                return new  Manager (
//                        rs.getString(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getDouble(7),
//                        rs.getDouble(8),
//                        rs.getInt(9),
//                        rs.getString(10),
//                        rs.getDate(11),
//                        rs.getDate(12));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
