import ConnectionDataBase.ConnectionDB;
import core.FactureData;
import core.MedicamentData;
import models.Facture;
import models.Medicament;
import tables.CreatTableFacture;
import tables.CreatTableMedicament;
import tables.CreateTableUsers;
import core.PharmacieData;
import models.Pharmacien;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
//        System.out.println("Hello world!");
        CreateTableUsers.CreateIfNotExist();
        CreatTableMedicament.CreateIfNotExist();
        CreatTableFacture.CreateIfNotExist();
        boolean exitProgram = false;

        while (!exitProgram) {
            try {
                ConnectionDB db = new ConnectionDB("jdbc:mysql://localhost:3306/pharmaciedb", "root", "");
                PharmacieData pharmacieData = new PharmacieData(db);
                MedicamentData medicamentData = new MedicamentData(db);
                FactureData factureData = new FactureData(db);
                Scanner scanner = new Scanner(System.in);

//                boolean exitProgram = false;
               System.out.println("********************* Super Admin Login *********************");
               System.out.print("Login :");
               String login=scanner.nextLine();
                System.out.print("Password :");
                String password =scanner.nextLine();
                if(login.equals("admin") && password.equals("admin")) {

                    while (!exitProgram) {
                        System.out.println("*********************Programme de Gestion Pharmacie*********************");
                        System.out.println("Menu Principal");
                        System.out.println("1- Gestion Pharmacien");
                        System.out.println("2- Gestion Medicament");
                        System.out.println("3- Gestion Facture");
                        System.out.println("4- Quitter le programme");
                        System.out.print("Choix: ");

                        int choix = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        switch (choix) {

                            case 1:
                                boolean exitPharmacienMenu = false;
                                while (!exitPharmacienMenu) {
                                    System.out.println("*********************Gestion Pharmaciens*********************");
                                    System.out.println("1- Ajouter Pharmacien");
                                    System.out.println("2- Supprimer Pharmacien");
                                    System.out.println("3- Modifier Pharmacien");
                                    System.out.println("4- Afficher Pharmacien");
                                    System.out.println("5- Rechercher un Pharmacien Par prenom");
                                    System.out.println("6- Quitter le Menu Pharmacien");
                                    System.out.print("Choix: ");

                                    int choixPharmacien = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline character

                                    switch (choixPharmacien) {
                                        case 1:
                                            System.out.print("Enter the last name of the pharmacien: ");
                                            String lastName = scanner.nextLine();

                                            while (lastName.isEmpty()) {
                                                System.out.println("Last name cannot be empty. Please enter a valid last name.");
                                                System.out.print("Enter the last name of the pharmacien: ");
                                                lastName = scanner.nextLine();
                                            }
                                            System.out.print("Enter the first name of the pharmacien: ");
                                            String firstName = scanner.nextLine();

                                            while (firstName.isEmpty()) {
                                                System.out.println("First name cannot be empty. Please enter a valid first name.");
                                                System.out.print("Enter the first name of the pharmacien: ");
                                                firstName = scanner.nextLine();
                                            }
                                            String email = null;
                                            boolean validEmail = false;
                                            while (!validEmail) {
                                                System.out.print("Enter the email of the pharmacien: ");
                                                email = scanner.nextLine();
                                                if (isValidEmail(email)) {
                                                    validEmail = true;
                                                } else {
                                                    System.out.println("Invalid email format. Please enter a valid email address.");
                                                }
                                            }

                                            String phone = null;
                                            boolean validPhone = false;
                                            while (!validPhone) {
                                                System.out.print("Enter the phone number of the pharmacien: ");
                                                phone = scanner.nextLine();
                                                if (isValidPhoneNumber(phone)) {
                                                    validPhone = true;
                                                } else {
                                                    System.out.println("Invalid phone number format. Please enter a valid phone number.");
                                                }
                                            }

                                            Pharmacien pharmacien = new Pharmacien();
                                            pharmacien.setLastName(lastName);
                                            pharmacien.setFirstName(firstName);
                                            pharmacien.setEmail(email);
                                            pharmacien.setPhone(phone);
                                            pharmacieData.addPharmacien(pharmacien);
                                            break;

                                        case 2:
                                            System.out.println("Supprimer Pharmacien");
                                            System.out.print("Enter the ID of the pharmacien: ");
                                            int pharmacienIdToDelete = scanner.nextInt();
                                            scanner.nextLine();
                                            pharmacieData.deletePharmacien(pharmacienIdToDelete);
                                            break;
                                        case 3:
                                            System.out.println("Modifier Pharmacien");
                                            System.out.print("Enter the ID of the pharmacien: ");
                                            int pharmacienIdToUpdate = scanner.nextInt();
                                            scanner.nextLine();
                                            Pharmacien existingPharmacien = pharmacieData.getPharmacienById(pharmacienIdToUpdate);
                                            if (existingPharmacien != null) {
                                                System.out.print("Enter the New last name of the pharmacien: ");
                                                String NewLastName = scanner.nextLine();

                                                System.out.print("Enter the New first name of the pharmacien: ");
                                                String NewfirstName = scanner.nextLine();

                                                String Newemail = null;
                                                boolean NewvalidEmail = false;
                                                while (!NewvalidEmail) {
                                                    System.out.print("Enter the New email of the pharmacien: ");
                                                    Newemail = scanner.nextLine();
                                                    if (isValidEmail(Newemail)) {
                                                        NewvalidEmail = true;
                                                    } else {
                                                        System.out.println("Invalid email format. Please enter a valid email address.");
                                                    }
                                                }

                                                String Newphone = null;
                                                boolean NewvalidPhone = false;
                                                while (!NewvalidPhone) {
                                                    System.out.print("Enter the New phone number of the pharmacien: ");
                                                    Newphone = scanner.nextLine();
                                                    if (isValidPhoneNumber(Newphone)) {
                                                        NewvalidPhone = true;
                                                    } else {
                                                        System.out.println("Invalid phone number format. Please enter a valid phone number.");
                                                    }
                                                }
                                                existingPharmacien.setLastName(NewLastName);
                                                existingPharmacien.setFirstName(NewfirstName);
                                                existingPharmacien.setEmail(Newemail);
                                                existingPharmacien.setPhone(Newphone);
                                                pharmacieData.updatePharmacien(existingPharmacien);
                                            } else {
                                                System.out.println("No pharmacien found with ID " + pharmacienIdToUpdate + ".");
                                            }
                                            break;
                                        case 4:
                                            System.out.println("Pharmacien List");
                                            for (Pharmacien allpharmacien : pharmacieData.GetAllPharmacie()) {
                                                System.out.println(allpharmacien);
                                            }
                                            break;
                                        case 5:
                                            System.out.print("Enter the last name of the pharmacien: ");
                                            String nameSearch = scanner.nextLine();
                                            for (Pharmacien pharmacienSearched : pharmacieData.searchPharmacienByName(nameSearch)) {
                                                System.out.println(pharmacienSearched);
                                            }
                                            break;
                                        case 6:
                                            exitPharmacienMenu = true;
                                            break;

                                        default:
                                            System.out.println("Invalid choice. Please enter a valid option.");
                                            break;
                                    }
                                }
                                break;

                            case 2:
                                boolean exitMedicamentsMenu = true;
                                while (exitMedicamentsMenu) {
                                    System.out.println("*********************Gestion Medicaments*********************");
                                    System.out.println("1- Ajouter Medicament");
                                    System.out.println("2- Supprimer Medicament");
                                    System.out.println("3- Modifier Medicament");
                                    System.out.println("4- Afficher Medicament  ");
                                    System.out.println("5- Rechercher un Medicament Par prenom");
                                    System.out.println("6- Quitter le Menu Medicament");

                                    int choixMedicament = scanner.nextInt();

                                    switch (choixMedicament) {
                                        case 1:
                                            System.out.print("Enter the name of the medicament: ");
                                            String nameMedicament = scanner.nextLine();
                                            while (nameMedicament.isEmpty()) {
                                                System.out.println("Medicament name cannot be empty. Please enter a valid Medicament name.");
                                                System.out.print("Enter the Medicament name: ");
                                                nameMedicament = scanner.nextLine();
                                            }
                                            System.out.print("Enter the quantity of the medicament: ");
                                            int MedicamentQuantity;

                                            while (true) {
                                                String input = scanner.nextLine();

                                                if (input.isEmpty()) {
                                                    System.out.println("Quantity of the medicament cannot be empty. Please enter a valid quantity.");
                                                    System.out.print("Enter the quantity of the medicament: ");
                                                    continue;
                                                }

                                                try {
                                                    MedicamentQuantity = Integer.parseInt(input);
                                                    break;
                                                } catch (NumberFormatException e) {
                                                    System.out.println("Invalid quantity format. Please enter a valid integer value.");
                                                    System.out.print("Enter the quantity of the medicament: ");
                                                }
                                            }
                                            System.out.print("Enter the DLC (dd/MM/yyyy) of the medicament: ");
                                            String dlcStr = scanner.nextLine();
                                            Date dlc = null;
                                            try {
                                                dlc = new SimpleDateFormat("dd/MM/yyyy").parse(dlcStr);
                                            } catch (ParseException e) {
                                                System.out.println("Invalid date format. Please enter the DLC in dd/MM/yyyy format.");
                                                continue;
                                            }

                                            System.out.print("Enter the lot number of the medicament: ");
                                            String lot = scanner.nextLine();

                                            float price = 0.0f;
                                            boolean validPrice = false;
                                            while (!validPrice) {
                                                System.out.print("Enter medicament price: ");
                                                String priceInput = scanner.nextLine();
                                                try {
                                                    price = Float.parseFloat(priceInput);
                                                    validPrice = true;
                                                } catch (NumberFormatException e) {
                                                    System.out.println("Invalid input. Please enter a valid numeric value for the price.");
                                                }
                                            }

                                            Medicament medicament = new Medicament();
                                            medicament.setMed_name(nameMedicament);
                                            medicament.setQuantite(MedicamentQuantity);
                                            medicament.setDLC(dlc);
                                            medicament.setLot(lot);
                                            medicament.setPrix(price);

                                            medicamentData.AddMedicament(medicament);
                                            break;

                                        case 2:
                                            System.out.println("Supprimer Medicament");
                                            System.out.print("Enter the ID of the medicament: ");
                                            int medicamentIdToDelete = scanner.nextInt();
                                            scanner.nextLine();
                                            medicamentData.deleteMedicament(medicamentIdToDelete);
                                            break;

                                        case 3:
                                            System.out.println("Modifier Medicament");
                                            System.out.print("Enter the ID of the medicament: ");
                                            int medicamentIdToUpdate = scanner.nextInt();
                                            scanner.nextLine();
                                            Medicament existingMedicament = medicamentData.getMedicamentById(medicamentIdToUpdate);
                                            if (existingMedicament != null) {
                                                System.out.print("Enter the new name of the medicament: ");
                                                String newName = scanner.nextLine();

                                                System.out.print("Enter the new quantity of the medicament: ");
                                                int newQuantity = scanner.nextInt();
                                                scanner.nextLine(); // Consume the newline character

                                                System.out.print("Enter the new DLC (dd/MM/yyyy) of the medicament: ");
                                                String newDlcStr = scanner.nextLine();
                                                Date newDlc = null;
                                                try {
                                                    newDlc = new SimpleDateFormat("dd/MM/yyyy").parse(newDlcStr);
                                                } catch (ParseException e) {
                                                    System.out.println("Invalid date format. Please enter the new DLC in dd/MM/yyyy format.");
                                                    continue;
                                                }

                                                System.out.print("Enter the new lot number of the medicament: ");
                                                String newLot = scanner.nextLine();

                                                System.out.print("Enter the new price of the medicament: ");
                                                float newPrice = scanner.nextFloat();
                                                scanner.nextLine(); // Consume the newline character

                                                Medicament newMedicament = new Medicament();
                                                newMedicament.setId_med(medicamentIdToUpdate);
                                                newMedicament.setMed_name(newName);
                                                newMedicament.setQuantite(newQuantity);
                                                newMedicament.setDLC(newDlc);
                                                newMedicament.setLot(newLot);
                                                newMedicament.setPrix(newPrice);

                                                medicamentData.updateMedicament(newMedicament);
                                            } else {
                                                System.out.println("No medicament found with ID " + medicamentIdToUpdate + ".");
                                            }
                                            break;

                                        case 4:
                                            System.out.println("Afficher Medicament");
                                            List<Medicament> allMedicaments = medicamentData.GetAllMedicament();
                                            for (Medicament m : allMedicaments) {
                                                System.out.println(m);
                                            }
                                            break;

                                        case 5:
                                            System.out.print("Enter the name of the medicament: ");
                                            String searchName = scanner.nextLine();
                                            List<Medicament> searchedMedicaments = medicamentData.searchMedicamentsByName(searchName);
                                            for (Medicament m : searchedMedicaments) {
                                                System.out.println(m);
                                            }
                                            break;

                                        case 6:
                                            exitMedicamentsMenu = false;
                                            break;

                                        default:
                                            System.out.println("Invalid choice. Please enter a valid option.");
                                            break;
                                    }
                                }
                                break;
                            case 3:
                                boolean exitFactureMenu=true;
                                while (exitFactureMenu){
                                    System.out.println("********************* Facture Management *********************");
                                    System.out.println("1. Create Facture");
                                    System.out.println("2. Update Facture");
                                    System.out.println("3. Delete Facture");
                                    System.out.println("4. Search Factures");
                                    System.out.println("5. Exit");
                                    System.out.print("Enter your choice: ");
                                    int choice = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline character
                                    switch (choice) {
                                        case 1:
                                            System.out.print("Enter facture number: ");
                                            String numero = scanner.nextLine();

//                                            System.out.print("Enter facture date (dd/MM/yyyy): ");
//                                            String dateStr = scanner.nextLine();
//                                            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);

                                            System.out.print("Enter facture date (dd/MM/yyyy): ");
                                            String dateStr = scanner.nextLine();
                                            Date newDfacture = null;
                                            try {
                                                newDfacture = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
                                            } catch (ParseException e) {
                                                System.out.println("Invalid date format. Please enter the new DLC in dd/MM/yyyy format.");
                                                continue;
                                            }

                                            System.out.print("Enter facture montant: ");
                                            float montant = scanner.nextFloat();
                                            scanner.nextLine(); // Consume the newline character

                                            Facture facture = new Facture();
                                            facture.setNumero(numero);
                                            facture.setDate(newDfacture);
                                            facture.setMontant(montant);

                                            factureData.addFacture(facture);
                                            break;

                                        case 2:
                                            System.out.print("Enter the ID of the facture to update: ");
                                            int factureId = scanner.nextInt();
                                            scanner.nextLine(); // Consume the newline character

                                            Facture existingFacture = factureData.getFactureById(factureId);
                                            if (existingFacture != null) {
                                                System.out.print("Enter new facture number: ");
                                                String newNumero = scanner.nextLine();

                                                System.out.print("Enter facture date (dd/MM/yyyy): ");
                                                String updatedateStr = scanner.nextLine();
                                                Date updateDfacture = null;
                                                try {
                                                    updateDfacture = new SimpleDateFormat("dd/MM/yyyy").parse(updatedateStr);
                                                } catch (ParseException e) {
                                                    System.out.println("Invalid date format. Please enter the new DLC in dd/MM/yyyy format.");
                                                    continue;
                                                }

                                                System.out.print("Enter new facture montant: ");
                                                float newMontant = scanner.nextFloat();
                                                scanner.nextLine(); // Consume the newline character

                                                existingFacture.setNumero(newNumero);
                                                existingFacture.setDate(updateDfacture);
                                                existingFacture.setMontant(newMontant);

                                                factureData.updateFacture(existingFacture);
                                            } else {
                                                System.out.println("No facture found with ID " + factureId);
                                            }
                                            break;

                                        case 3:
                                            System.out.print("Enter the ID of the facture to delete: ");
                                            int factureIdToDelete = scanner.nextInt();
                                            scanner.nextLine(); // Consume the newline character

                                            factureData.deleteFacture(factureIdToDelete);
                                            break;

                                        case 4:
                                            System.out.print("Enter the facture number to search: ");
                                            String searchNumero = scanner.nextLine();

                                            List<Facture> searchedFactures = factureData.searchFacturesByNumero(searchNumero);
                                            if (searchedFactures.isEmpty()) {
                                                System.out.println("No factures found with the provided number.");
                                            } else {
                                                System.out.println("Search Results:");
                                                for (Facture searchedFacture : searchedFactures) {
                                                    System.out.println(searchedFacture);
                                                }
                                            }
                                            break;

                                        case 5:
                                            exitFactureMenu = false;
                                            break;

                                        default:
                                            System.out.println("Invalid choice. Please enter a valid option.");
                                            break;
                                    }




                                }

                            case 4:
                                exitProgram = true;
                                break;

                            default:
                                System.out.println("Invalid choice. Please enter a valid option.");
                                break;
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }
    private static boolean isValidEmail(String email) {
//        String perfectmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
//        boolean isValid = email.matches(perfectmail);
        return email.contains("@");
    }

    private static boolean isValidPhoneNumber(String phone) {
        String phoneRegex = "^[0-9]{8}$";
        return phone.matches(phoneRegex);
    }
}

