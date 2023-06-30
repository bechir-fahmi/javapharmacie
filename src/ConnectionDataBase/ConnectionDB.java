package ConnectionDataBase;
import java.sql.*;

public class ConnectionDB {
    private String url;
    private String username;
    private String password;
    private Connection conn;


    public ConnectionDB(String url, String username, String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
        conn = DriverManager.getConnection(url, username, password);

    }
    public ResultSet executeQuery(String sql) throws SQLException {
        try (Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            return result;
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void close() throws SQLException {
        conn.close();
    }
}