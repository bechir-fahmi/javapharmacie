import ConnectionDataBase.ConnectionDB;
import tables.CreateTableUsers;
import core.PharmacieData;
import models.Pharmacien;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        System.out.println("Hello world!");
        CreateTableUsers.CreateIfNotExist();
        try {
            ConnectionDB db = new ConnectionDB("jdbc:mysql://localhost:3306/pharmaciedb", "root", "");
//            db.getConnection();
            PharmacieData userData = new PharmacieData(db);
             userData.GetAllPharmacie();
             for (Pharmacien pharmacien:userData.GetAllPharmacie()){
               System.out.println(pharmacien);
             }
            db.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}