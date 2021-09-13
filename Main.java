import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));

            long count = persons.stream() // оператор счетчик
                    .filter(x -> x.getAge() < 18)  //Фильтр для людей младше 18 лет
                    .count(); //оператор сложения
                    System.out.println("Количество несовершеннолетних  " + count );

            List<String> familiesList = persons.stream()
                    .filter(p -> p.getAge() > 17)
                    .filter(p -> p.getAge() < 28)
                    .map(Person::getFamily) //преобразования данных из Person в String
                    // (так как нужны только фамилии) используйте метод map()
                    .collect(Collectors.toList()); //терминальный метод для получения списка
            System.out.println("Фамилии призывников  " + familiesList);

            List<Person> womanList = persons.stream()
                    .filter(value -> value.getSex() == Sex.WOMAN) //фильтр enum  пол м или ж
                    .filter(value -> value.getEducation() == Education.HIGHER) // фильтр по образованию
                    .filter(value -> value.getAge() > 17)
                    .filter(value -> value.getAge() < 61)
                    .sorted(Comparator.comparing(Person::getFamily)) //сортировка фамилий согласно верхним условиям
                    .collect(Collectors.toList()); //терминальный метод для получения списка
            System.out.println("Работоспособные щенщины с высшим образованием  " + womanList);


            List<Person> manList = persons.stream()
                    .filter(value -> value.getSex() == Sex.MAN) //фильтр enum  пол м или ж
                    .filter(value -> value.getEducation() == Education.HIGHER)
                    .filter(value -> value.getAge() > 17)
                    .filter(value -> value.getAge() < 66)
                    .sorted(Comparator.comparing(Person::getFamily)) //сортировка фамилий согласно верхним условиям
                    .collect(Collectors.toList()); //терминальный метод для получения списка
            System.out.println("Работоспособные мужчины с высшим образованием  " + manList);

        }
    }

}