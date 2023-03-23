package jdbcTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.sql.*;

public class jdbc_examples {
    String dbUrl = "jdbc:oracle:thin:@52.3.253.62:1521:XE"; // @52.3.253.62 part is our AWS Instance ip adress
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet= statement.executeQuery("SELECT * from departments");

    }

}
