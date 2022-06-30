package clasroomSix;

import com.github.javafaker.Faker;

public class Example {
    public static void main(String[] args) {

        Employee employee1 = new Employee("Līva", "Nazarenko", 34);
        Faker faker = new Faker();
        String animalName = faker.animal().name();
        System.out.println(animalName);

        String name = faker.name().name();
        String lastName = faker.name().lastName();
        int age = faker.number().numberBetween(1, 100);
        Employee employee2 = new Employee(name, lastName, age);

        System.out.println(employee1.toString());
        System.out.println(employee2.toString());
    }
}
