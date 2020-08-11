package contacts;

public class OrganizationContact extends Contact {
    String address;

    OrganizationContact(String name, String address) {
        super(name);
        this.address = address;
    }

    public void setAddress(String address) {
        setLastEdit();
        this.address = address;
    }

    @Override
    public String possibleChanges() {
        return "name, address, number";
    }

    @Override
    void changeValue(String field, String newValue) {
        switch (field) {
            case "name":
                name = newValue;
                break;
            case "address":
                address = newValue;
                break;
            case "number":
                setNumber(newValue);
                break;
            default:
                System.out.println("Error in changeValue - Organization");
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
            case "address":
                return getAddress();
            case "number":
                return getNumber();
            default:
                System.out.println("Error in getValue - Organization");
                return "";
        }
    }

    @Override
    String getFullName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }

    @Override
    public String getInfo() {
        return String.format("Organization name: %s\nAddress: %s\nNumber: %s\nTime created: %s\nTime last edit: %s", name, address, number, creationDate, lastEdit);
    }

    @Override
    public String getAppendedVars() {
        return address+name+number;
    }

    public String getAddress() {
        return address;
    }
}
