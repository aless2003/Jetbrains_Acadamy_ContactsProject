package contacts;

import java.time.LocalDateTime;
import java.util.Objects;

public class PersonalContact extends Contact {
    String surname;
    LocalDateTime birthDate;
    String gender;
    PersonalContact(String name, String surname) {
        super(name);
        this.surname = surname;
    }

    @Override
    public String possibleChanges() {
        return "name, surname, gender, number, birth";
    }

    public void changeValue(String field, String newValue) {
        field = field.toLowerCase();
        switch (field) {
            case "name":
                setName(newValue);
                break;
            case "surname":
                setSurname(newValue);
                break;
            case "gender":
                setGender(newValue);
                break;
            case "number":
                setNumber(newValue);
                break;
            case "birth":
                setBirth(LocalDateTime.parse(newValue));
                break;
            default:
                System.out.println("Error in changeValue - Personal");
                break;
        }
        setLastEdit();
        System.out.println("Saved");
    }

    @Override
    String getValue(String field) {
        switch (field) {
            case "name":
                return getName();
            case "surname":
                return getSurname();
            case "gender":
                return getGender();
            case "number":
                return getNumber();
            case "birth":
                return getBirthDate().toString();
            default:
                System.out.println("Error in getValue - Personal");
                return "";
        }
    }

    @Override
    String getFullName() {
        return name + " " + surname;
    }

    public void setSurname(String surname) {
        setLastEdit();
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, surname);
    }

    @Override
    public String getInfo() {
        String data = String.format("Name: %s\nSurname: %s\nBirth date: ", name, surname);
        data += Objects.requireNonNullElse(birthDate, "[no data]");
        data += "\nGender: ";
        data += Objects.requireNonNullElse(gender, "[no data]");
        data += "\nNumber: ";
        if (number.isEmpty()) {
            data += "[no data]";
        } else {
            data += number;
        }
        data += String.format("\nTime created: %s\nTime last edit: %s", creationDate, lastEdit);
        return data;
    }

    @Override
    public String getAppendedVars() {
        return name+surname+number+gender+birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirth(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }
}
