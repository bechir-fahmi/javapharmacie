package models;
import java.util.ArrayList;
public class Pharmacien extends User {



    public Pharmacien() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Pharmacien(int id, String lastName, String firstName, String phone, String email) {
        super(id, lastName, firstName, phone, email);
        // TODO Auto-generated constructor stub
    }


    @Override
    public String toString() {
        return super.toString();
    }


    public String addPharmacien(ArrayList<User> listUsers, Pharmacien pharamcien) {
        try {
            return super.addPerson(listUsers, pharamcien);


        }catch(Exception ex) {
            return ex.getMessage();

        }

    }

    public String editPharmacien(ArrayList<User> listUsers, Pharmacien pharamcien, int index) {
        try {

            return super.editPerson(listUsers, pharamcien, index);

        }catch(Exception ex) {
            return ex.getMessage();

        }

    }

    public String deletePharmacien(ArrayList<User> listUsers, int index){
        try {
            return super.deletePerson(listUsers, index);

        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    public String affichePharmacien(ArrayList<User> listUsers){
        try{

            return super.affichePerson(listUsers);

        }catch(Exception ex){
            return ex.getMessage();
        }
    }





}
