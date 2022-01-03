package config.Dao;

import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Roledao {
    ConnectionJDBC connectionJDBC = new ConnectionJDBC();

    public List<Role> showRole() {
        String getall = "select * from role ";
        List<Role> roleList = new ArrayList<>();

        try (Connection connection = connectionJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getall)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String code = rs.getString("code");

                roleList.add(new Role(id, name,code));
            }
            return roleList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
