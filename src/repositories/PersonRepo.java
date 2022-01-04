package repositories;

import entities.Person;

import java.util.HashMap;

public class PersonRepo {

    HashMap<String, Person> personHashMap;

    public PersonRepo() {
        this.personHashMap = new HashMap<>();
    }

    public void savePerson(Person person){
        personHashMap.put(person.getId(), person);
    }

    public HashMap<String, Person> getPersonHashMap() {
        return personHashMap;
    }

    public Person findPersonById(String id){
        return  personHashMap.get(id);
    }

}
