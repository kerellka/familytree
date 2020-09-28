package test;

import io.FamilyReader;
import model.Date;
import model.FamilyTree;
import model.Person;

import java.io.IOException;

public class FamilyReaderTest {

    private int successTests;
    private int countTests;

    public FamilyReaderTest() {
        successTests = 0;
        countTests = 0;
    }

    public boolean treeLoadTestCorrectFile() {
        FamilyTree treeExpected = new FamilyTree(2);

        Person vika = new Person("Viktoria", "Udina", "Igorevna", Person.FEMALE, new Date(10, 9, 2000), null);
        Person igor = new Person("Igor", "Udin", "Nikolaevich", Person.MALE, new Date(7, 9, 1963), null);
        Person sveta = new Person("Svetlana", "Udina", "Alexeevna", Person.FEMALE, new Date(13, 8, 1974), null);
        Person kolyaF = new Person("Nikolai", "Udin", "none", Person.MALE, new Date(1, 1, 1930), new Date(1, 1, 2004));
        Person galyaF = new Person("Galina", "Udina", "Viktorovna", Person.FEMALE, new Date(8, 1, 1938), null);
        Person alexM = new Person("Alexey", "Vasileev", "none", Person.MALE, new Date(1, 2, 1950), new Date(1, 9, 2018));
        Person galyaM = new Person("Galina", "Vasileva", "Alexeevna", Person.FEMALE, new Date(16, 11, 1950), null);

        treeExpected.addDirectRelative(vika, igor);
        treeExpected.addDirectRelative(vika, sveta);
        treeExpected.addNonDirectRelative(igor, sveta, true);

        treeExpected.addDirectRelative(igor, galyaF);
        treeExpected.addDirectRelative(igor, kolyaF);
        treeExpected.addNonDirectRelative(kolyaF, galyaF, true);

        treeExpected.addDirectRelative(sveta, galyaM);
        treeExpected.addDirectRelative(sveta, alexM);
        treeExpected.addNonDirectRelative(alexM, galyaM, true);

        try {
            FamilyTree treeActual = FamilyReader.loadTree("src/files/myTree.txt",
                    "src/files//myTreePersonsInfo.txt");
            boolean result = treeExpected.equals(treeActual);

            successTests = result ? 1 : 0;
            countTests++;
            return result;
        } catch (Exception e) {
            countTests++;
            return false;
        }
    }

    public boolean treeLoadTestIncorrectFile() {
        try {
            FamilyTree tree = FamilyReader.loadTree("src/files/myTreeIncorrect.txt",
                    "src/files/PersonsInfoIncorrect.txt");
            countTests++;
            return false;
        } catch (Exception e) {
            successTests++;
            countTests++;
            return true;
        }
    }

    public int getSuccessTests() {
        return successTests;
    }

    public int getCountTests() {
        return countTests;
    }
}
