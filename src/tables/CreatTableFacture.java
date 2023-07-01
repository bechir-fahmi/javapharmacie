package tables;

import ConnectionDataBase.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatTableFacture {
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS factures (" +
                    "id_fac INT NOT NULL AUTO_INCREMENT," +
                    "id_med INT, "+
                    "id INT,"+
                    "numfac VARCHAR(50) NOT NULL," +
                    "qnt_med INT NOT NULL," +
                    "totale REAL NOT NULL,"+
                    "PRIMARY KEY (id_fac) ," +"FOREIGN KEY (id_med) REFERENCES medicaments(id_med),"+"FOREIGN KEY (id) REFERENCES users(id)" + ")";
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
            System.out.println("Table 'factures' created successfully.");
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
