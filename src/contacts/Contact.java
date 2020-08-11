package contacts;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Contact {
    String number = "";
    String name;
    LocalDateTime creationDate;
    LocalDateTime lastEdit;
    abstract String possibleChanges();
    abstract void changeValue(String field, String newValue);
    abstract String getValue(String field);
    abstract String getFullName();
    Contact(String name) {
        this.name = name;
        creationDate = LocalDateTime.now();
        lastEdit = creationDate;
    }

    public void setName(String name) {
        setLastEdit();
        this.name = name;
    }

    public void setNumber(String number) {
        setLastEdit();
        if (isValidNumber(number)) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
            this.number = "";
        }
    }

    void setLastEdit() {
        lastEdit = LocalDateTime.now();
    }



    public boolean hasNumber() {
        return number.equals("");
    }

    private static boolean isValidNumber(String number) {
        //The ugliest Regex you will ever find :)
        Pattern pattern = Pattern.compile("(([a-zA-Z0-9]{2,}| |-| )+$)|(\\+?(\\([a-zA-Z0-9]+\\)(-| |[a-zA-Z0-9]| )*$)|((\\+?[a-zA-Z0-9]| |-)+\\([a-zA-Z0-9]+\\)([a-zA-Z0-9]| |-| )*)$)|(\\+((([a-zA-Z0-9]|-)+$)|(([a-zA-Z0-9]| )+))$)|(([a-zA-Z0-9]|-)+$)|([a-zA-Z0-9]+$)|(([a-zA-Z0-9]{2,}| )+$)");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public String getNumber() {
        return number;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String numberOut;
        if (number.equals("")) {
            numberOut = "[no number]";
        } else {
            numberOut = number;
        }
        return String.format("%s, %s", name, numberOut);
    }

    public abstract String getInfo();

    public abstract String getAppendedVars();
}
