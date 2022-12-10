package streams;

import java.util.*;
import java.util.stream.Collectors;

public class Main2 {
    //Mając listę programistów i korzystając ze streamów:
    //a. uzyskaj listę programistów, którzy są mężczyznami
    //b. uzyskaj listę niepełnoletnich programistów (obydwóch płci), którzy piszą w Cobolu
    //c. uzyskaj listę programistów, którzy znają więcej, niż jeden język programowania
    //d. uzyskaj listę programistek, które piszą w Javie i Cpp
    //e. uzyskaj listę męskich imion
    //f. uzyskaj set wszystkich języków opanowanych przez programistów
    //g. uzyskaj listę nazwisk programistów, którzy znają więcej, niż dwa języki
    //h. * sprawdź, czy istnieje chociaż jedna osoba, która nie zna żadnego języka
    //i. * uzyskaj ilość wszystkich języków opanowanych przez programistki
    //j. **Używając streamów znajdź długość najdłuższej linii w wybranym przez ciebie pliku.
    public static void main(String[] args) {
        Person person1 = new Person("Jacek","Kowalski",18,true);
        Person person2 = new Person("Jacek","Górski",15,true);
        Person person3 = new Person("Andżelika","Dżoli",25,false);
        Person person4 = new Person("Wanda","Ibanda",12,false);
        Person person5 = new Person("Marek","Marecki",17,true);
        Person person6 = new Person("Johny","Brawo",25,true);
        Person person7 = new Person("Stary","Pan",80,true);
        Person person8 = new Person("Newbie","Noob",12,true);
        Person person9 = new Person("Newbies","Sister",19,false);
        List<String> languages1 = Arrays.asList("Java;Cobol;Cpp;Lisp".split(";"));
        List<String> languages2 = Arrays.asList("Java;Lisp".split(";"));
        List<String> languages3 = Arrays.asList("Java;Cobol;Cpp;Lisp;C#".split(";"));
        List<String> languages4 = Arrays.asList("C#;C;Cpp".split(";"));
        List<String> languages5 = Arrays.asList("Java;Assembler;Scala;Cobol".split(";"));
        List<String> languages6 = Arrays.asList("Java;Scala".split(";"));
        List<String> languages7 = Arrays.asList("C#;C".split(";"));
        List<String> languages8 = Collections.emptyList();
        List<String> languages9 = Arrays.asList("Java");
        Programmer programmer1 = new Programmer(person1,languages1);
        Programmer programmer2 = new Programmer(person2,languages2);
        Programmer programmer3 = new Programmer(person3,languages3);
        Programmer programmer4 = new Programmer(person4,languages4);
        Programmer programmer5 = new Programmer(person5,languages5);
        Programmer programmer6 = new Programmer(person6,languages6);
        Programmer programmer7 = new Programmer(person7,languages7);
        Programmer programmer8 = new Programmer(person8,languages8);
        Programmer programmer9 = new Programmer(person9,languages9);
        List<Programmer> programmers = Arrays.asList(
                programmer1,
                programmer2,
                programmer3,
                programmer4,
                programmer5,
                programmer6,
                programmer7,
                programmer8,
                programmer9);
        System.out.println(programmers);
        System.out.println();
        //a
        List<Programmer> resultAA = programmers.stream().filter(programmer -> programmer.getPerson().isMale()).toList();
        System.out.println("Wynik AA: "+resultAA);
        List<Person> resultA = programmers.stream().map(person -> person.getPerson()).filter(person -> person.isMale()).toList();
        System.out.println("Wynik A: "+resultA);
        System.out.println();
        //b
        List<Programmer> resultBB = programmers.stream().filter(programmer ->programmer.getLanguages().contains("Cobol"))
                .filter(programmer -> programmer.getPerson().getAge()<18).toList();
        System.out.println("Wynik BB: "+resultBB);
        List<Person> resultB = programmers.stream().filter(programmer -> programmer.getLanguages().contains("Cobol"))
                .map(person->person.getPerson())
                .filter(person -> person.getAge()<18).toList();
        System.out.println("Wynik B: "+resultB);
        System.out.println();
        //c
        List<Programmer> resultC = programmers.stream().filter(programmer -> programmer.getLanguages().size()>1).toList();
        System.out.println("Wynik C: "+resultC);
        System.out.println();
        //d
        List<Programmer> resultDD = programmers.stream().filter(programmer -> programmer.getLanguages().contains("Java") && programmer.getLanguages().contains("Cpp"))
                .filter(programmer -> !programmer.getPerson().isMale()).toList();
        System.out.println("Wynik DD: "+resultDD);
        List<Person> resultD = programmers.stream().filter(programmer -> programmer.getLanguages().contains("Java") && programmer.getLanguages().contains("Cpp"))
                .map(person->person.getPerson()).filter(person -> !person.isMale()).toList();
        System.out.println("Wynik D: "+resultD);
        System.out.println();
        //e
        List<String> resultE = programmers.stream().map(person->person.getPerson()).filter(person -> person.isMale())
                .map(person -> person.getFirstName()).toList();
        System.out.println("Wynik E: "+resultE);
        System.out.println();
        //f
       Set<String> resultF = programmers.stream().map(programmer -> programmer.getLanguages())
               .flatMap(languages->languages.stream()).collect(Collectors.toSet());
        System.out.println("Wynik F: "+resultF);
        System.out.println();
        //g
        List<String> resultG = programmers.stream().filter(programmer -> programmer.getLanguages().stream().count()>2)
                .map(person->person.getPerson()).map(person -> person.getLastName()).toList();
        System.out.println("Wynik G: "+resultG);
        System.out.println();
        //h
       Optional<Programmer> resultH = programmers.stream().filter(programmer -> programmer.getLanguages().isEmpty()).findAny();
       List<Programmer> resultHlista = programmers.stream().filter(programmer -> programmer.getLanguages().isEmpty()).toList();
        System.out.println(resultHlista);
        if(resultH.isPresent()){
            System.out.println("Wynik h: jest conajmniej 1 taka osoba");
        } else {
            System.out.println("Nie ma takiej osoby");
        }
        System.out.println();
        //i
        Long resultI = programmers.stream().filter(programmer -> !programmer.getPerson().isMale()).mapToLong(programmer->programmer.getLanguages().stream().distinct().count()).sum();
        Integer resultII = programmers.stream().filter(programmer -> !programmer.getPerson().isMale()).map(programmer->programmer.getLanguages())
                .flatMap(languages ->languages.stream()).collect(Collectors.toSet()).size();
        System.out.println("Wynik I (Łączna liczba): "+resultI);
        System.out.println();
        System.out.println("Wynik I (Unikatowe jezyki): "+resultII);
        System.out.println();
    }
}
