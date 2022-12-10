package streams;

import java.util.ArrayList;
import java.util.List;

public class Programmer {
    private Person person;
    private List<String> languages;

    public Programmer(Person person, List<String> languages) {
        this.person = person;
        this.languages = languages;
    }

    public Programmer() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "person=" + person +
                ", languages=" + languages +
                '}';
    }
}
