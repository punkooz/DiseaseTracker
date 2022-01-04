package entities;

import java.util.HashSet;

public class Person {

    String id;
    Address address;
    String primaryDiseaseName;
    HashSet<String> listOfAllDiseases;

    public Person(String id, Address address, String primaryDiseaseName) {
        this.id = id;
        this.address = address;
        this.listOfAllDiseases = new HashSet<>();
        this.primaryDiseaseName = primaryDiseaseName;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPrimaryDiseaseName() {
        return primaryDiseaseName;
    }

    public void setPrimaryDiseaseName(String primaryDiseaseName) {
        this.primaryDiseaseName = primaryDiseaseName;
    }

    public HashSet<String> getListOfAllDiseases() {
        return listOfAllDiseases;
    }

    public void setListOfAllDiseases(HashSet<String> listOfAllDiseases) {
        this.listOfAllDiseases = listOfAllDiseases;
    }
}
