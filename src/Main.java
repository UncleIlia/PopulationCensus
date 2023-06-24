import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long countOfConscript = persons.stream()
                .filter(Person -> Person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + countOfConscript);

        List<String> listOfUnderage = persons.stream()
                .filter(Person -> Person.getAge() > 18 && Person.getAge() < 27)
                .filter(P -> P.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников: " + listOfUnderage);

        List<String> listOfPeopleWithHigherEducation = persons.stream()
                .filter(P -> (P.getAge() > 18 && P.getAge() < 60 && P.getSex() == Sex.WOMAN) ||
                        (P.getAge() > 18 && P.getAge() < 65 && P.getSex() == Sex.MAN))
                .filter(P -> P.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список фамилий работоспособных людей с высшим образованием: " + listOfPeopleWithHigherEducation);
    }
}
