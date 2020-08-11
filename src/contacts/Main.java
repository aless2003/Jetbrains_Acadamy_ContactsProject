package contacts;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook;
        try {
            File database = new File(args[0]);
            if (!database.exists()) {
                System.out.println("File not found");
            }
            System.out.println(database.getName());
            FileInputStream fis = new FileInputStream(database);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            phoneBook = (PhoneBook) ois.readObject();
            phoneBook.setSaveFile(database);
        } catch (FileNotFoundException fileNotFoundException) {
            File database = new File(args[0]);
            phoneBook = new PhoneBook(database);
        } catch (Exception e) {
            phoneBook = new PhoneBook();
        }
        String UserInput;
        do {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            UserInput = scanner.nextLine();

            switch (UserInput) {
                case "add":
                    Contact newContact = null;
                    System.out.println("Enter the type (person, organization): ");
                    UserInput = scanner.nextLine();
                    if (UserInput.equalsIgnoreCase("Person")) {
                        System.out.println("Enter the name: ");
                        String name = scanner.nextLine();

                        System.out.println("Enter the surname: ");
                        String surname = scanner.nextLine();

                        System.out.println("Enter the number: ");
                        String number = "";
                        while (number.isEmpty()) {
                            number = scanner.nextLine();
                        }
                        newContact = new PersonalContact(name, surname);
                        newContact.setNumber(number);
                    } else if (UserInput.equalsIgnoreCase("Organization")) {
                        System.out.println("Enter the organization name: ");
                        String oName = scanner.nextLine();

                        System.out.println("Enter the address: ");
                        String address = scanner.nextLine();

                        System.out.println("Enter the number: ");
                        String number = scanner.nextLine();

                        newContact = new OrganizationContact(oName, address);
                        newContact.setNumber(number);
                    }

                    if (newContact != null) {
                        phoneBook.addEntry(newContact);
                        System.out.println("The record added.");
                    }


                    break;
                case "count":
                    System.out.println("The Phone Book has " + phoneBook.getEntryCount() + " records.");
                    break;
                case "list":
                    phoneBook.list();
                    break;
                case "search":
                    phoneBook.search();
                    break;
                default:
                    break;
            }
            System.out.println();
        } while (!UserInput.equals("exit"));
    }
}
