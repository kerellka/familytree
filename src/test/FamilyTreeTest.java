package test;

import model.Date;
import model.FamilyTree;
import model.Person;

import java.util.HashSet;
import java.util.Set;

public class FamilyTreeTest {

    private int successTests;
    private int countTests;

    public FamilyTreeTest() {
        successTests = 0;
        countTests = 0;
    }

    public boolean addDirectRelativeTest() {
        FamilyTree tree = new FamilyTree(2);
        Person vika = new Person("Viktoria", "Udina", "Igorevna", Person.FEMALE, new Date(10, 9, 2000), null);
        Person igor = new Person("Igor", "Udin", "Nikolaevich", Person.MALE, new Date(7, 9, 1963), null);
        Person sveta = new Person("Svetlana", "Udina", "Alexeevna", Person.FEMALE, new Date(13, 8, 1974), null);
        tree.addDirectRelative(vika, igor);
        tree.addDirectRelative(vika, sveta);

        Set<Person> personSetActual = tree.getPersonSet();

        Person vika2 = new Person("Viktoria", "Udina", "Igorevna", Person.FEMALE, new Date(10, 9, 2000), null);
        Person igor2 = new Person("Igor", "Udin", "Nikolaevich", Person.MALE, new Date(7, 9, 1963), null);
        Person sveta2 = new Person("Svetlana", "Udina", "Alexeevna", Person.FEMALE, new Date(13, 8, 1974), null);
        vika2.setMother(sveta2);
        vika2.setFather(igor2);
        vika2.setID(1);
        igor2.setID(2);
        sveta2.setID(3);

        Set<Person> personSetExpected = new HashSet<>();
        personSetExpected.add(vika2);
        personSetExpected.add(igor2);
        personSetExpected.add(sveta2);

        boolean result = personSetActual.equals(personSetExpected);
        successTests += result ? 1 : 0;
        countTests++;

        return result;
    }

    public boolean addNonDirectRelativeTest() {
        FamilyTree tree = new FamilyTree(2);
        Person igor = new Person("Igor", "Udin", "Nikolaevich", Person.MALE, new Date(7, 9, 1963), null);
        Person sveta = new Person("Svetlana", "Udina", "Alexeevna", Person.FEMALE, new Date(13, 8, 1974), null);
        tree.addNonDirectRelative(igor, sveta, true);

        Set<Person> personSetActual = tree.getPersonSet();

        Person igor2 = new Person("Igor", "Udin", "Nikolaevich", Person.MALE, new Date(7, 9, 1963), null);
        Person sveta2 = new Person("Svetlana", "Udina", "Alexeevna", Person.FEMALE, new Date(13, 8, 1974), null);
        igor2.setSpouse(sveta2);
        sveta2.setSpouse(igor2);
        igor2.setID(1);
        sveta2.setID(2);

        Set<Person> personSetExpected = new HashSet<>();
        personSetExpected.add(igor2);
        personSetExpected.add(sveta2);

        boolean result = personSetActual.equals(personSetExpected);
        successTests += result ? 1 : 0;
        countTests++;

        return result;
    }

    public int getSuccessTests() {
        return successTests;
    }

    public int getCountTests() {
        return countTests;
    }
}
