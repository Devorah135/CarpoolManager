package code;

public class Address {

    private String street;
    private String city;
    private String state;
    private String zip;

    //Constructors
    public Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    //Getters
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Address: ");
        builder.append(street);
        builder.append(", ");
        builder.append(city);
        builder.append(", ");
        builder.append(state);
        builder.append(", ");
        builder.append(zip);
        return builder.toString();
    }
}

