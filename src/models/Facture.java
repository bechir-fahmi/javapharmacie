package models;

import java.util.Date;

public class Facture {
    private int id;
    private String numero;
    private Date date;
    private float montant;
    private Date deletedDate;

    public Facture() {
    }

    public Facture(int id, String numero, Date date, float montant) {
        this.id = id;
        this.numero = numero;
        this.date = date;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", date=" + date +
                ", montant=" + montant +
                ", deletedDate=" + deletedDate +
                '}';
    }
}
