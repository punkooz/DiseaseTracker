package entities;

public class Country {

    String countryName;
    Counter counter;

    public Country(String countryName) {
        this.countryName = countryName;
        this.counter = new Counter(0, 0, 0, 0);
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }
}
