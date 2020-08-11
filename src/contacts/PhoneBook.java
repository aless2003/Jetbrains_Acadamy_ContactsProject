package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class PhoneBook implements Serializable {
    private static final long serialVersionUID = 1L;
    static ArrayList<Contact> contacts = new ArrayList<>();
    private File saveFile = null;
    public void addEntry(Contact newContact) {
        contacts.add(newContact);
    }
    public void setSaveFile(File saveFile) {
        this.saveFile = saveFile;
    }
    PhoneBook() {}
    PhoneBook(File saveFile) {
        setSaveFile(saveFile);
    }

    public void printAllContacts() {
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i).toString());
        }
    }

    public int getEntryCount() {
        return contacts.size();
    }

    public void search() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        LinkedList<Contact> results;
        results = searchPhonebook(scanner);

        do {
            System.out.print("[search] Enter action ([number], back, again): ");
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("again")) {
                results = searchPhonebook(scanner);
            } else if (userInput.matches("\\d+")) {
                int index = Integer.parseInt(userInput);
                Contact record = results.get(index - 1);
                System.out.println(record.getInfo());
                do {
                    System.out.println();
                    userInput = recordMenu(scanner, record);
                } while (!userInput.equalsIgnoreCase("menu"));
            }
        } while (!userInput.equalsIgnoreCase("back") && !userInput.equals("menu"));
    }

    private String recordMenu(Scanner scanner, Contact record) {
        String userInput;
        System.out.print("[record] Enter action (edit, delete, menu): ");
        userInput = scanner.nextLine();
        switch (userInput) {
            case "edit":
                System.out.print("Select a field (" + record.possibleChanges() + "): ");
                String field = scanner.nextLine();
                System.out.println("Enter " + field + ": ");
                userInput = scanner.nextLine();
                record.changeValue(field, userInput);
                save();
                System.out.println(record.getInfo());
                break;
            case "delete":
                contacts.remove(record);
                break;
            default:
                break;
        }
        return userInput;
    }


    public static LinkedList<Contact> getSearchResults(String query) {
        LinkedList<Contact> results = new LinkedList<>();
        for (Contact x : contacts) {
            if (x.getAppendedVars().toLowerCase().contains(query.toLowerCase())) {
                results.add(x);
            }
        }
        return results;
    }


    private static LinkedList<Contact> searchPhonebook(Scanner scanner) {
        String userInput;
        LinkedList<Contact> results;
        System.out.print("Enter search query: ");
        userInput = scanner.nextLine();
        results = getSearchResults(userInput);
        System.out.println("Found " + results.size() + " results:");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i).getFullName());
        }
        System.out.println();
        return results;
    }

    public void list() {
        Scanner scanner = new Scanner(System.in);
        printAllContacts();
        System.out.println();
        String userInput;
        System.out.println("[list] Enter action ([number], back): ");
        userInput = scanner.nextLine();
        try {
            int index = Integer.parseInt(userInput);
            Contact record = contacts.get(index - 1);
            System.out.println(record.getInfo());
            do {
                System.out.println();
                userInput = recordMenu(scanner, record);
            } while (!userInput.equals("menu"));
        } catch (Exception ignored) {

        }
    }

    public void save() {
        if (saveFile != null) {
            try {
                FileOutputStream fos = new FileOutputStream(saveFile);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                ObjectOutputStream oos =  new ObjectOutputStream(bos);
                oos.writeObject(this);
                oos.close();
            } catch (IOException ignored) {
            }
        }
    }
}
