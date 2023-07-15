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
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

//        try (ResultSet result = db.executeQuery(rs)) {
            while (rs.next()) {
                Pharmacien pharmacien = new Pharmacien();
                pharmacien.setId(rs.getInt("id"));
                pharmacien.setEmail(rs.getString("email"));
                pharmacien.setFirstName(rs.getString("firstName"));
                pharmacien.setLastName(rs.getString("lastName"));
                pharmacien.setPhone(rs.getString("phone"));
                pharmacienList.add(pharmacien);

            }

        } catch (SQLException ex) {
            // handle the exception
        }

//        }
        return pharmacienList;
    }

    public void addPharmacien(Pharmacien pharmacien) throws SQLException {
        String insertQuery = "INSERT INTO users (lastName, firstName,email, phone) VALUES (?, ?, ?, ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, pharmacien.getLastName());
            pstmt.setString(2, pharmacien.getFirstName());
            pstmt.setString(3, pharmacien.getEmail());
            pstmt.setString(4, pharmacien.getPhone());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pharmacien added successfully.");
            } else {
                System.out.println("Failed to add pharmacien.");
            }
        } catch (SQLException ex) {
            System.out.println("Error adding pharmacien: " + ex.getMessage());
        }
    }
    public void deletePharmacien(int id) throws SQLException {
        String deleteQuery = "DELETE FROM users WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pharmacien with ID " + id + " deleted successfully.");
            } else {
                System.out.println("No pharmacien found with ID " + id + ".");
            }
        } catch (SQLException ex) {
            System.out.println("Error deleting pharmacien: " + ex.getMessage());
        }
    }

    public void updatePharmacien(Pharmacien pharmacien) throws SQLException {
        String updateQuery = "UPDATE users SET lastName = ?, firstName = ?, email = ?, phone = ? WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            pstmt.setString(1, pharmacien.getLastName());
            pstmt.setString(2, pharmacien.getFirstName());
            pstmt.setString(3, pharmacien.getEmail());
            pstmt.setString(4, pharmacien.getPhone());
            pstmt.setInt(5, pharmacien.getId());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pharmacien with ID " + pharmacien.getId() + " modified successfully.");
            } else {
                System.out.println("No pharmacien found with ID " + pharmacien.getId() + ".");
            }
        } catch (SQLException ex) {
            System.out.println("Error modifying pharmacien: " + ex.getMessage());
        }
    }



    public List<Pharmacien> searchPharmacienByName(String name) throws SQLException {
        List<Pharmacien> pharmacienList = new ArrayList<>();
        String searchQuery = "SELECT * FROM users WHERE lastName LIKE ? OR firstName LIKE ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {

            pstmt.setString(1, "%" + name + "%");
            pstmt.setString(2, "%" + name + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Pharmacien pharmacien = new Pharmacien();
                pharmacien.setId(rs.getInt("id"));
                pharmacien.setEmail(rs.getString("email"));
                pharmacien.setFirstName(rs.getString("firstName"));
                pharmacien.setLastName(rs.getString("lastName"));
                pharmacien.setPhone(rs.getString("phone"));
                pharmacienList.add(pharmacien);
            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error searching pharmacien: " + ex.getMessage());
        }

        return pharmacienList;
    }

    public Pharmacien getPharmacienById(int pharmacienId) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, pharmacienId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Pharmacien pharmacien = new Pharmacien();
                    pharmacien.setId(rs.getInt("id"));
                    pharmacien.setLastName(rs.getString("lastName"));
                    pharmacien.setFirstName(rs.getString("firstName"));
                    pharmacien.setEmail(rs.getString("email"));
                    pharmacien.setPhone(rs.getString("phone"));
                    return pharmacien;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving pharmacien: " + ex.getMessage());
        }

        return null;
    }
}
