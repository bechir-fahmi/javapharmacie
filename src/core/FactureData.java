package core;

import ConnectionDataBase.ConnectionDB;
import models.Facture;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FactureData {
    private final ConnectionDB db;

    public FactureData(ConnectionDB db) {
        this.db = db;
    }

    public List<Facture> getAllFactures() throws SQLException {
        List<Facture> factureList = new ArrayList<>();
        String selectQuery = "SELECT * FROM factures WHERE isdeleted IS NULL";

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            while (rs.next()) {
                Facture facture = new Facture();
                facture.setId(rs.getInt("id"));
                facture.setNumero(rs.getString("numero"));
                facture.setDate(rs.getDate("date"));
                facture.setMontant(rs.getFloat("montant"));
                factureList.add(facture);
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving factures: " + ex.getMessage());
        }

        return factureList;
    }

    public void addFacture(Facture facture) throws SQLException {
        String insertQuery = "INSERT INTO factures (numero, date, montant) VALUES (?, ?, ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, facture.getNumero());
            pstmt.setDate(2, new java.sql.Date(facture.getDate().getTime()));
            pstmt.setFloat(3, facture.getMontant());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Facture inserted successfully.");
            } else {
                System.out.println("Failed to insert facture.");
            }
        } catch (SQLException ex) {
            System.out.println("Error inserting facture: " + ex.getMessage());
        }
    }

    public void deleteFacture(int id) throws SQLException {
        String updateQuery = "UPDATE factures SET isdeleted = ? WHERE id_fac = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            pstmt.setDate(1, new java.sql.Date(new Date().getTime()));
            pstmt.setInt(2, id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Facture with ID " + id + " deleted successfully.");
            } else {
                System.out.println("No facture found with ID " + id + ".");
            }
        } catch (SQLException ex) {
            System.out.println("Error deleting facture: " + ex.getMessage());
        }
    }

    public Facture getFactureById(int id) throws SQLException {
        Facture facture = null;
        String selectQuery = "SELECT * FROM factures WHERE id_fac = ? AND isdeleted IS NULL";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                facture = new Facture();
                facture.setId(rs.getInt("id_fac"));
                facture.setNumero(rs.getString("numero"));
                facture.setDate(rs.getDate("date"));
                facture.setMontant(rs.getFloat("montant"));
            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving facture: " + ex.getMessage());
        }

        return facture;
    }

    public void updateFacture(Facture facture) throws SQLException {
        String updateQuery = "UPDATE factures SET numero = ?, date = ?, montant = ? WHERE id_fac = ? AND isdeleted IS NULL";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            pstmt.setString(1, facture.getNumero());
            pstmt.setDate(2, new java.sql.Date(facture.getDate().getTime()));
            pstmt.setFloat(3, facture.getMontant());
            pstmt.setInt(4, facture.getId());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Facture with ID " + facture.getId() + " modified successfully.");
            } else {
                System.out.println("No facture found with ID " + facture.getId() + ".");
            }
        } catch (SQLException ex) {
            System.out.println("Error modifying facture: " + ex.getMessage());
        }
    }

    public List<Facture> searchFacturesByNumero(String numero) throws SQLException {
        List<Facture> factureList = new ArrayList<>();
        String searchQuery = "SELECT * FROM factures WHERE numero LIKE ? AND isdeleted IS NULL";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {

            pstmt.setString(1, "%" + numero + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Facture facture = new Facture();
                facture.setId(rs.getInt("id_fac"));
                facture.setNumero(rs.getString("numero"));
                facture.setDate(rs.getDate("date"));
                facture.setMontant(rs.getFloat("montant"));
                factureList.add(facture);
            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error searching factures: " + ex.getMessage());
        }

        return factureList;
    }
}
