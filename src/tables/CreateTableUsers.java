package tables;
import ConnectionDataBase.ConnectionDB;

import java.sql.*;
public class CreateTableUsers {
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "lastName VARCHAR(50) NOT NULL," +
                    "firstName VARCHAR(50) NOT NULL," +
                    "email VARCHAR(50) NOT NULL," +
                    "phone VARCHAR(50) NOT NULL," +
                    "PRIMARY KEY (id)" +
                    ")";
 public  static void  CreateIfNotExist() throws SQLException {

    Connection conn = null;
    Statement stmt = null;

    try {
        ConnectionDB connectionDB=new ConnectionDB("jdbc:mysql://localhost:3306/pharmaciedb", "root", "");
//        conn=
//        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmaciedb", "root", "");
        conn = connectionDB.getConnection();
        stmt = conn.createStatement();
        stmt.executeUpdate(CREATE_TABLE_SQL);
        System.out.println("Table 'users' created successfully.");
    } catch (SQLException e) {
        System.out.println("Error creating table: " + e.getMessage());
    } finally {
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    }

}
