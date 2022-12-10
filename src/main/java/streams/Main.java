package streams;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    //Mając listę osób i korzystając ze streamów:
    //a. uzyskaj listę mężczyzn
    //b. uzyskaj listę dorosłych kobiet (filter)
    //c. uzyskaj Optional<Person> z dorosłym Jackiem findAny/findfirst
    //d. uzyskaj listę wszystkich nazwisk osób, które są w przedziale wiekowym: 15-19
    //(filter)
    //e. * uzyskaj sumę wieku wszystkich osób (sum)
    //f. * uzyskaj średnią wieku wszystkich mężczyzn (average)
    //g. ** znajdź najstarszą osobę w liście (findFirst)
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
        List<Person> people = new ArrayList<Person>(Arrays.asList(person1, person2, person3,
                person4, person5, person6, person7, person8, person9));
//a
        List<Person> result = people.stream().filter(Person::isMale).toList();
        List<Person> result1 = people.stream().filter(person->person.isMale()).collect(Collectors.toList());
        System.out.println(result1);
        List<Person> result2 = people.stream().filter(new Predicate<Person>() {
                    @Override
                    public boolean test(Person person) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
//b
        List<Person> result_age = people.stream().filter(person -> !person.isMale()&& person.getAge()>=18)
                .toList();
        System.out.println(result_age);
//c
        Optional<Person> wynikOptional = people.stream()
                .filter(person -> person.getAge()>=18 && person.getFirstName().equalsIgnoreCase("jacek"))
                .findFirst();
        if (wynikOptional.isPresent()){
            Person personC = wynikOptional.get();
            System.out.println(personC);
        }
//d
        List<String> result_15_19 = people.stream().filter(person ->  person.getAge()<=19&& person.getAge()>=15)
                .map(person -> person.getLastName()).toList();
        System.out.println(result_15_19);
//e
        double ageSum = people.stream().mapToDouble(person -> person.getAge())
                .sum();
        System.out.println(ageSum);

        OptionalDouble ageAverage = people.stream().filter(person -> person.isMale())
                .mapToDouble(person -> person.getAge()).average();
        if(ageAverage.isPresent()){
            System.out.println("Wynik f: "+ ageAverage.getAsDouble());
        }

//g
       Optional<Person> maxAgePerson=  people.stream()
                .max(Comparator.comparingDouble(Person::getAge));
       if(maxAgePerson.isPresent()){
           Person person = maxAgePerson.get();
           System.out.println("Wynik g: " + person);
       }
    }
}
