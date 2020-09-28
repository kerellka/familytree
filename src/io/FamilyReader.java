package io;

import model.Date;
import model.FamilyTree;
import model.Person;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FamilyReader {

    public static FamilyTree loadTree(String filenameTree, String filenamePersonInfo) throws IOException {
        List<Person> personList = loadPersons(filenamePersonInfo);

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        FamilyTree tree;
        Scanner input = new Scanner(new FileInputStream(filenameTree));
        String header = input.next();
        header = header + input.next();
        if (!header.equals("maxGen=")) {
            throw new RuntimeException("Header must follow that form : \"maxGen = int\"");
        }
        tree = new FamilyTree(input.nextInt());
        input.next();
        while (input.hasNext()) {
            StringTokenizer sTok = new StringTokenizer(input.nextLine(), alphabet + alphabet.toUpperCase() + " ");
            if (sTok.countTokens() != 3) {
                throw new RuntimeException("Incorrect syntax");
            }
            if (sTok.hasMoreTokens()) {
                int firstId = Integer.parseInt(sTok.nextToken()) - 1;
                String relative = sTok.nextToken();
                int secondId = Integer.parseInt(sTok.nextToken()) - 1;

                if (relative.equals("->")) {
                    tree.addDirectRelative(personList.get(firstId), personList.get(secondId));
                } else if (relative.equals("-")) {
                    tree.addNonDirectRelative(personList.get(firstId), personList.get(secondId), true);
                } else if (relative.equals("<->")) {
                    tree.addNonDirectRelative(personList.get(firstId), personList.get(secondId), false);
                }
            }
        }

        return tree;
    }

    private static List<Person> loadPersons(String filename) throws IOException {
        Scanner input = new Scanner(new FileInputStream(filename));
        List<Person> personList = new ArrayList<>();

        while (input.hasNext()) {
            input.next();
            int id = input.nextInt();
            Date birth = null;
            Date death = null;

            StringTokenizer sTok = new StringTokenizer(input.nextLine(), " .,={}");
            if (!(sTok.countTokens() != 6 || sTok.countTokens() != 10 || sTok.countTokens() != 8)) {
                throw new RuntimeException("Incorrect syntax");
            }
            if (sTok.hasMoreTokens()) {
                String name = sTok.nextToken();
                String surname = sTok.nextToken();
                String patronymic = sTok.nextToken();
                boolean sex = sTok.nextToken().equals("Male");
                String day = sTok.nextToken();
                if (!day.equals("null")) {
                    birth = new Date(Integer.parseInt(day), Integer.parseInt(sTok.nextToken()), Integer.parseInt(sTok.nextToken()));
                }
                day = sTok.nextToken();
                if (!day.equals("null")) {
                    death = new Date(Integer.parseInt(day), Integer.parseInt(sTok.nextToken()), Integer.parseInt(sTok.nextToken()));
                }

                Person person = new Person(name, surname, patronymic, sex, birth, death);
                person.setID(id);
                personList.add(person);
            }
        }
        input.close();

        return personList;
    }

}
