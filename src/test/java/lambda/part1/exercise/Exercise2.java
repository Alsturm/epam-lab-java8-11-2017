package lambda.part1.exercise;

import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import lambda.data.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Exercise2 {

    @Test
    public void sortPersonsByAgeUsingArraysSortExpressionLambda() {
        Person[] persons = getPersons();

        // TODO использовать Arrays.sort + expression-lambda
        //noinspection ComparatorCombinators
        Arrays.sort(persons, (person1, person2) -> Integer.compare(person1.getAge(), person2.getAge()));

        assertArrayEquals(new Person[]{
            new Person("Иван", "Мельников", 20),
            new Person("Николай", "Зимов", 30),
            new Person("Алексей", "Доренко", 40),
            new Person("Артем", "Зимов", 45)
        }, persons);
    }

    @Test
    public void sortPersonsByLastNameThenFirstNameUsingArraysSortExpressionLambda() {
        Person[] persons = getPersons();

        // TODO использовать Arrays.sort + statement-lambda
        Arrays.sort(persons, (p1, p2) -> {
            int lastNameCompare = p1.getLastName().compareTo(p2.getLastName());
            if (lastNameCompare == 0) return p1.getFirstName().compareTo(p2.getFirstName());
            return lastNameCompare;
        });

        assertArrayEquals(new Person[]{
            new Person("Алексей", "Доренко", 40),
            new Person("Артем", "Зимов", 45),
            new Person("Николай", "Зимов", 30),
            new Person("Иван", "Мельников", 20)
        }, persons);
    }

    @Test
    public void findFirstWithAge30UsingGuavaPredicateLambda() {
        List<Person> persons = Arrays.asList(getPersons());

        // TODO использовать FluentIterable
        Person person = null;
        //noinspection Guava
        Optional<Person> oPerson = FluentIterable.from(persons).firstMatch(p -> 30 == (p != null ? p.getAge() : 0));
        person = oPerson.isPresent() ? oPerson.get() : null;

        assertEquals(new Person("Николай", "Зимов", 30), person);
    }

    private Person[] getPersons() {
        return new Person[]{
            new Person("Иван", "Мельников", 20),
            new Person("Алексей", "Доренко", 40),
            new Person("Николай", "Зимов", 30),
            new Person("Артем", "Зимов", 45)
        };
    }
}
