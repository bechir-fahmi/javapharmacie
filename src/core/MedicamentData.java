package core;

import ConnectionDataBase.ConnectionDB;
import models.Medicament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentData {
    private final ConnectionDB db;

    public MedicamentData(ConnectionDB db) {
        this.db = db;
    }
    public List<Medicament> GetAllMedicament() throws SQLException {
        List<Medicament> MedicamentList = new ArrayList<>();
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM medicaments")) {

        while (rs.next()){
            Medicament medicament=new Medicament();
            medicament.setId_med(rs.getInt("id_med"));
            medicament.setMed_name(rs.getString("name_med"));
            medicament.setDLC(rs.getDate("DLC"));
            medicament.setLot(rs.getString("lot"));
            medicament.setPrix(rs.getFloat("prix"));
            medicament.setQuantite(rs.findColumn("quantite"));
            MedicamentList.add(medicament);
        }
        } catch (SQLException ex) {
            // handle the exception
        }


        return MedicamentList;
    }
    public void AddMedicament(Medicament medicament) throws SQLException {
         String REQ = "INSERT INTO medicaments (name_med, quantite, DLC, lot, prix) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(REQ)) {
            pstmt.setString(1, medicament.getMed_name());
            pstmt.setInt(2, medicament.getQuantite());
            pstmt.setDate(3, new java.sql.Date(medicament.getDLC().getTime()));
            pstmt.setString(4, medicament.getLot());
            pstmt.setDouble(5, medicament.getPrix());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Medicament inserted successfully.");
            } else {
                System.out.println("Failed to insert medicament.");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    public void deleteMedicament(int id) throws SQLException {
        String deleteQuery = "DELETE FROM medicaments WHERE id_med = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Medicament with ID " + id + " deleted successfully.");
            } else {
                System.out.println("No medicament found with ID " + id + ".");
            }
        } catch (SQLException ex) {
            System.out.println("Error deleting medicament: " + ex.getMessage());
        }
    }
    public Medicament getMedicamentById(int id) throws SQLException {
        Medicament medicament = null;
        String selectQuery = "SELECT * FROM medicaments WHERE id_med = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                medicament = new Medicament();
                medicament.setId_med(rs.getInt("id_med"));
                medicament.setMed_name(rs.getString("med_name"));
                medicament.setQuantite(rs.getInt("quantite"));
                medicament.setDLC(rs.getDate("DLC"));
                medicament.setLot(rs.getString("lot"));
                medicament.setPrix(rs.getFloat("prix"));
            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving medicament: " + ex.getMessage());
        }

        return medicament;
    }
    public void updateMedicament(Medicament medicament) throws SQLException {
        String updateQuery = "UPDATE medicaments SET name_med = ?, quantite = ?, DLC = ?, lot = ?, prix = ? WHERE id_med = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            pstmt.setString(1, medicament.getMed_name());
            pstmt.setInt(2, medicament.getQuantite());
            pstmt.setDate(3, new java.sql.Date(medicament.getDLC().getTime()));
            pstmt.setString(4, medicament.getLot());
            pstmt.setFloat(5, medicament.getPrix());
            pstmt.setInt(6, medicament.getId_med());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Medicament with ID " + medicament.getId_med() + " modified successfully.");
            } else {
                System.out.println("No medicament found with ID " + medicament.getId_med() + ".");
            }
        } catch (SQLException ex) {
            System.out.println("Error modifying medicament: " + ex.getMessage());
        }
    }
    public List<Medicament> searchMedicamentsByName(String name) throws SQLException {
        List<Medicament> medicamentList = new ArrayList<>();
        String searchQuery = "SELECT * FROM medicaments WHERE name_med LIKE ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {

            pstmt.setString(1, "%" + name + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Medicament medicament = new Medicament();
                medicament.setId_med(rs.getInt("id_med"));
                medicament.setMed_name(rs.getString("name_med"));
                medicament.setQuantite(rs.getInt("quantite"));
                medicament.setDLC(rs.getDate("DLC"));
                medicament.setLot(rs.getString("lot"));
                medicament.setPrix(rs.getFloat("prix"));
                medicamentList.add(medicament);
            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error searching medicaments: " + ex.getMessage());
        }

        return medicamentList;
    }
}
