package userdao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource {

    public Connection getConnection() throws ClassNotFoundException, SQLException ;
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection("jdbc:mysql://localhost/halla?serverTimezone=UTC", "jeju", "jejupw");

}