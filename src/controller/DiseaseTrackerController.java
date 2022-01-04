package controller;

import entities.*;
import repositories.DiseaseRepo;
import repositories.PersonRepo;

import java.util.*;

public class DiseaseTrackerController {

    DiseaseRepo diseaseRepo = new DiseaseRepo();
    PersonRepo personRepo = new PersonRepo();


    public void registerDisease(String diseaseName, String viral, String transmissible, String air) {

        Disease disease = new Disease(diseaseName);

        diseaseRepo.saveDisease(disease);

    }

    public void report(String diseaseName, String p1, String countryName, String state, String city) {

        Person passedPersonFunction = personRepo.findPersonById(p1);

        if(passedPersonFunction == null) {

            Address address = new Address(countryName, state, city);

            Person person = new Person(p1, address, diseaseName);

            HashSet<String> listOfAllDiseases = person.getListOfAllDiseases();

            if (listOfAllDiseases == null)
                listOfAllDiseases = new HashSet<>();

            listOfAllDiseases.add(diseaseName);

            person.setListOfAllDiseases(listOfAllDiseases);

            personRepo.savePerson(person);

        }else{

            HashSet<String> listOfAllDiseases = passedPersonFunction.getListOfAllDiseases();

            if (listOfAllDiseases == null)
                listOfAllDiseases = new HashSet<>();

            listOfAllDiseases.add(diseaseName);

            passedPersonFunction.setListOfAllDiseases(listOfAllDiseases);

            personRepo.savePerson(passedPersonFunction);

        }

        passedPersonFunction = personRepo.findPersonById(p1); //getting the updated / created person

        Disease disease = diseaseRepo.getDiseaseByName(diseaseName);

        if (disease == null){
            System.out.println(diseaseName + " was never created");
            return;
        }


        List<Person> affectedPersons = disease.getAffectedPersons();

        if (affectedPersons == null)
            affectedPersons = new ArrayList<>();

        //adding affected persons
        affectedPersons.add(passedPersonFunction);

        disease.setAffectedPersons(affectedPersons);

        //counter for disease
        Counter diseaseCounter = disease.getOverAllCounter();
        diseaseCounter.setTotal(diseaseCounter.getTotal() + 1);
        diseaseCounter.setActive(diseaseCounter.getActive() + 1);

        //updating country
        HashMap<String, Country> countryHashMap = disease.getCountryHashMap();

        if (countryHashMap == null || !countryHashMap.containsKey(countryName)){
            if (countryHashMap == null)
                countryHashMap = new HashMap<>();

            Country country = new Country(countryName);
            Counter counter = country.getCounter();
            counter.setTotal(counter.getTotal() + 1);
            counter.setActive(counter.getActive() + 1);

            country.setCounter(counter);

            countryHashMap.put(countryName, country);
        }else{
            if (countryHashMap.containsKey(countryName)){
                Country country = countryHashMap.get(countryName);
                Counter counter = country.getCounter();
                counter.setTotal(counter.getTotal() + 1);
                counter.setActive(counter.getActive() + 1);

                country.setCounter(counter);

                countryHashMap.put(countryName, country);
            }
        }

        disease.setCountryHashMap(countryHashMap);
        disease.setOverAllCounter(diseaseCounter);

        diseaseRepo.saveDisease(disease);
    }

    public void showWorldSummary() {

        int wTotal = 0;
        int wCured = 0;
        int wFatal = 0;
        int wActive = 0;

        for (Map.Entry<String, Disease> entry : diseaseRepo.getDiseaseHashMap().entrySet()){
            Disease disease = entry.getValue();

            wTotal += disease.getOverAllCounter().getTotal();
            wCured += disease.getOverAllCounter().getCured();
            wActive += disease.getOverAllCounter().getActive();
            wFatal += disease.getOverAllCounter().getFatality();
        }

        System.out.println();

        System.out.println("Overall: ");
        System.out.println("1. Total : "  +wTotal);
        System.out.println("2. Cured : "  +wCured);
        System.out.println("3. Fatality : "  +wFatal);
        System.out.println("4. Active : "  +wActive);

        System.out.println();

    }

    public void ShowWorldSummaryDiseasesBreakup() {

        System.out.println();

        for (Map.Entry<String, Disease> entry : diseaseRepo.getDiseaseHashMap().entrySet()){
            Disease disease = entry.getValue();

            int wTotal = disease.getOverAllCounter().getTotal();
            int wCured = disease.getOverAllCounter().getCured();
            int wActive = disease.getOverAllCounter().getActive();
            int wFatal = disease.getOverAllCounter().getFatality();

            System.out.println("a. " + entry.getKey() + " : " );
            System.out.println("1. Total : "  +wTotal);
            System.out.println("2. Cured : "  +wCured);
            System.out.println("3. Fatality : "  +wFatal);
            System.out.println("4. Active : "  +wActive);

        }

        System.out.println();

    }

    public void ShowCountryBreakup(String diseaseName) {

        Disease disease = diseaseRepo.getDiseaseByName(diseaseName);

        //TODO: error handling
        if (disease == null){
            System.out.println("Country does not exists in Repo");
            return;
        }

        HashMap<String, Country> countryHashMap = disease.getCountryHashMap();

        System.out.println();

        for (Map.Entry<String, Country> entry : countryHashMap.entrySet()){
            Country country = entry.getValue();

            int wTotal = country.getCounter().getTotal();
            int wCured = country.getCounter().getCured();
            int wActive = country.getCounter().getActive();
            int wFatal = country.getCounter().getFatality();

            System.out.println("a. " +entry.getKey() + " : " );
            System.out.println("1. Total : "  +wTotal);
            System.out.println("2. Cured : "  +wCured);
            System.out.println("3. Fatality : "  +wFatal);
            System.out.println("4. Active : "  +wActive);

            System.out.println();

        }

        System.out.println();

    }

    public void cured(String diseaseName, String p8) {

        try{
            Person person = personRepo.findPersonById(p8);


            if (person == null){
                throw new IllegalArgumentException(p8 +" does not exist");
            }


        Disease disease = diseaseRepo.getDiseaseByName(diseaseName);

        if (disease == null){
            throw new IllegalArgumentException(diseaseName +" does not exist");
        }

        HashMap<String, Country> countryHashMap = disease.getCountryHashMap();
        List<Person> affectedPersons = disease.getAffectedPersons();

        if (!affectedPersons.contains(person)){
            throw new IllegalArgumentException(p8 + " was not affected with " + diseaseName);
        }

        //counter for disease
        Counter diseaseCounter = disease.getOverAllCounter();
        diseaseCounter.setActive(diseaseCounter.getActive() - 1);
        diseaseCounter.setCured(diseaseCounter.getCured() + 1);

        //updating country
        String countryName = person.getAddress().getCountryName();

        if (countryHashMap == null || !countryHashMap.containsKey(countryName)){
            throw new IllegalArgumentException(countryName + " was never affected with " + diseaseName);
        }


                Country country = countryHashMap.get(countryName);
                Counter counter = country.getCounter();
                counter.setActive(counter.getActive() - 1);
                counter.setCured(counter.getCured() + 1);
                country.setCounter(counter);

                countryHashMap.put(countryName, country);



        disease.setCountryHashMap(countryHashMap);

        disease.setOverAllCounter(diseaseCounter);

        diseaseRepo.saveDisease(disease);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void registerFatility(String diseaseName, String p8) {

        Person person = personRepo.findPersonById(p8);

        if (person == null){
            System.out.println(p8 + " does not exist");
            return;
        }

        Disease disease = diseaseRepo.getDiseaseByName(diseaseName);

        if (disease == null){
            System.out.println(diseaseName + " does not exist");
            return;
        }

        HashMap<String, Country> countryHashMap = disease.getCountryHashMap();
        List<Person> affectedPersons = disease.getAffectedPersons();

        if (!affectedPersons.contains(person)){
            System.out.println(p8 + " was not affected with " + diseaseName);
            return;
        }

        //counter for disease
        Counter diseaseCounter = disease.getOverAllCounter();
        diseaseCounter.setActive(diseaseCounter.getActive() - 1);
        diseaseCounter.setFatality(diseaseCounter.getFatality() + 1);

        //updating country
        String countryName = person.getAddress().getCountryName();

        if (countryHashMap == null || !countryHashMap.containsKey(countryName)){
            System.out.println(countryName + " was never affected with " + diseaseName);
        }


        Country country = countryHashMap.get(countryName);
        Counter counter = country.getCounter();
        counter.setActive(counter.getActive() - 1);
        counter.setFatality(counter.getFatality() + 1);
        country.setCounter(counter);

        countryHashMap.put(countryName, country);



        disease.setCountryHashMap(countryHashMap);

        disease.setOverAllCounter(diseaseCounter);

        diseaseRepo.saveDisease(disease);

    }
}
