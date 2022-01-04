package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Disease {

    String diseaseName;
    Counter overAllCounter;
    HashMap<String, Country> countryHashMap;
    List<Person> affectedPersons;

    public Disease(String diseaseName) {
        this.diseaseName = diseaseName;
        this.overAllCounter = new Counter(0, 0, 0, 0);
        this.countryHashMap = new HashMap<>();
        this.affectedPersons = new ArrayList<>();
    }

    public List<Person> getAffectedPersons() {
        return affectedPersons;
    }

    public void setAffectedPersons(List<Person> affectedPersons) {
        this.affectedPersons = affectedPersons;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public Counter getOverAllCounter() {
        return overAllCounter;
    }

    public void setOverAllCounter(Counter overAllCounter) {
        this.overAllCounter = overAllCounter;
    }

    public HashMap<String, Country> getCountryHashMap() {
        return countryHashMap;
    }

    public void setCountryHashMap(HashMap<String, Country> countryHashMap) {
        this.countryHashMap = countryHashMap;
    }
}
