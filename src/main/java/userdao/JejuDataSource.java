package userdao;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
public class JejuDataSource implements DataSource {

    @Value("${db.className}")
    private String className; //= "com.mysql.cj.jdbc.Driver";
    @Value("${db.url}")
    private String url; // = "jdbc:mysql://localhost/jeju?serverTimezone=UTC";
    @Value("${db.username}")
    private String jeju;
    @Value("${db.password}")
    private String jejupw;

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(className);
        return DriverManager.getConnection(url, jeju, jejupw);
    }
}
