package school.hei;

import java.sql.*;

public class DBConnection {


    public Connection getConnection() throws SQLException {
        final String userName = "push_down_user";
        final String password = "123456";
        final  String url = "jdbc:postgresql://localhost:5432/push_down_db";

        return DriverManager.getConnection(url, userName, password);
    }
}
