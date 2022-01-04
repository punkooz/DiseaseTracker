package repositories;

import entities.Disease;

import java.util.HashMap;

public class DiseaseRepo {

    HashMap<String, Disease> diseaseHashMap;

    public DiseaseRepo() {
        this.diseaseHashMap = new HashMap<>();
    }

    public void saveDisease(Disease disease){
        diseaseHashMap.put(disease.getDiseaseName(), disease);
    }

    public Disease getDiseaseByName(String name){
       return diseaseHashMap.get(name);
    }

    public HashMap<String, Disease> getDiseaseHashMap() {
        return diseaseHashMap;
    }
}
