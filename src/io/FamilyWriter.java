package io;

import model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class FamilyWriter {
    public static void saveTree(FamilyTree tree, String filename) throws IOException {
        Writer output = new FileWriter(filename);
        ArrayDeque<Person> q = new ArrayDeque<>();
        LinkedList<Person> g = (LinkedList<Person>) tree.getTree();
        q.add(g.getFirst());
        List<Person> used = new LinkedList<>();
        used.add(g.getFirst());


        output.write("maxGen = " + tree.getMaxGenerationNum());
        output.write("\n");
        while (!q.isEmpty()) {
            Person v = q.peek();
            q.poll();

            if (v.hasMother() && !used.contains(v.getMother())) {
                used.add(v.getMother());
                q.add(v.getMother());

                output.write(v.getName() + " " + v.getID());
                output.write(" -> ");
                output.write(v.getMother().getName() + " " + v.getMother().getID());
                output.write("\n");
            }
            if (v.hasFather() && !used.contains(v.getFather())) {
                used.add(v.getFather());
                q.add(v.getFather());

                output.write(v.getName() + " " + v.getID());
                output.write(" -> ");
                output.write(v.getFather().getName() + " " + v.getFather().getID());
                output.write("\n");
            }

            if (v.hasMother() && v.hasFather()){
                output.write(v.getMother().getName() + " " + v.getMother().getID());
                output.write(" - ");
                output.write(v.getFather().getName() + " " + v.getFather().getID());
                output.write("\n");
            }
        }
        output.close();
        String personFilename = filename.replace(".txt", "PersonsInfo.txt");
        savePersons(tree, personFilename);
    }

    private static void savePersons(FamilyTree tree, String filename) throws IOException {
        Writer input = new FileWriter(filename);

        List<Person> personList = tree.getTree();

        for (Person cur : personList) {
            input.write(cur.getName() + " " + cur.getID());
            input.write(" = { ");
            input.write(cur.getName() + ", ");
            input.write(cur.getSurname() + ", ");
            input.write(cur.getPatronymic() + ", ");
            input.write(cur.getSex() ? "Male, " : "Female, ");
            input.write(cur.getBirth() + ", ");
            input.write(cur.getDeath() + " }");
            input.write('\n');
        }
        input.close();
    }

}
