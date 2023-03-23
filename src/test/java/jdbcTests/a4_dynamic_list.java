package jdbcTests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;

public class a4_dynamic_list {

    String dbUrl = "jdbc:oracle:thin:@52.3.253.62:1521:XE"; // @52.3.253.62 part is our AWS Instance ip adress
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum <6");


        //in order to get column names we need result setmetadata
        ResultSetMetaData rsmd = resultSet.getMetaData();

        //list of maps to keep all information
        List<Map<String,Object>> queryData = new ArrayList<>();

        //number of columns
        int colCount = rsmd.getColumnCount();

        //loop through each row
        while(resultSet.next()) {

            Map<String, Object> row = new LinkedHashMap<>();

            //some code to fill the dynamically
            for (int i = 1; i <= colCount; i++) {

                row.put(rsmd.getColumnName(i), resultSet.getObject(i));

            }
            //add ready map row to the list
            queryData.add(row);
        }

        //print each row inside the list
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }



        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

}
