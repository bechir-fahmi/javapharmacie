package core;

import ConnectionDataBase.ConnectionDB;
import models.Pharmacien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PharmacieData {
    private final ConnectionDB db;

    public PharmacieData(ConnectionDB db) {
        this.db = db;
    }
    public List<Pharmacien> GetAllPharmacie() throws SQLException {
        List<Pharmacien> pharmacienList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmaciedb", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

//        try (ResultSet result = db.executeQuery(rs)) {
            while (rs.next()) {
                Pharmacien pharmacien = new Pharmacien();
                pharmacien.setId(rs.getInt("id"));
                pharmacien.setEmail(rs.getString("email"));
                pharmacien.setFirstName(rs.getString("first_name"));
                pharmacien.setLastName(rs.getString("last_name"));
                pharmacien.setPhone(rs.getString("phone"));
                pharmacienList.add(pharmacien);

               System.out.println(pharmacien.getFirstName());
            }

        } catch (SQLException ex) {
            // handle the exception
        }

//        }
        return pharmacienList;
    }


}
