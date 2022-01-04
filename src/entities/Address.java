package entities;

public class Address {

    String countryName;
    String state;
    String city;

    public Address(String countryName, String state, String city) {
        this.countryName = countryName;
        this.state = state;
        this.city = city;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }
}
