package models;

import java.util.ArrayList;
import java.util.Date;

public class Medicament {
    private int id_med;
    private int quantite;
    private Date DLC;
    private String lot;
    private float prix;

    public Medicament(int id_med, int quantite, Date DLC, String lot, float prix) {
        this.id_med = id_med;
        this.quantite = quantite;
        this.DLC = DLC;
        this.lot = lot;
        this.prix = prix;
    }

    public int getId_med() {
        return id_med;
    }

    public int getQuantite() {
        return quantite;
    }

    public Date getDLC() {
        return DLC;
    }

    public String getLot() {
        return lot;
    }

    public float getPrix() {
        return prix;
    }

    public void setId_med(int id_med) {
        this.id_med = id_med;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setDLC(Date DLC) {
        this.DLC = DLC;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "medicament{" +
                "id_med=" + id_med +
                ", quantite=" + quantite +
                ", DLC=" + DLC +
                ", lot='" + lot + '\'' +
                ", prix=" + prix +
                '}';
    }

    public String addMed(ArrayList<Medicament> listMedicament, Medicament medicament) {
        try {
            listMedicament.add(medicament);
            return "Medicament Added Successfully";

        }catch(Exception ex) {
            return ex.getMessage();

        }

    }
    public String editMedicament(ArrayList<Medicament> listMedicaments, Medicament medicament, int index) {
        try {

            listMedicaments.set(index, medicament);
            return "medicament Edited Successfully!";

        }catch(Exception ex) {
            return ex.getMessage();

        }

    }
    public String deleteMedicament(ArrayList<Medicament> listMedicament, int index){
        try {
            listMedicament.remove(index);
            return "Medicament Deleted Successfully!";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    public String afficheMedicament(ArrayList<Medicament> listMedicaments){
        try{
            String med = "";
            for(Medicament medicament : listMedicaments){
                med += medicament.toString();
            }
            return med;
        }catch(Exception ex){
            return ex.getMessage();
        }
    }
}
