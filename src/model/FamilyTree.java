package model;

import java.util.*;


public class FamilyTree {

    private final List<Person> tree;
    private final Set<Person> personSet;
    private final int maxGenerationNum;

    public FamilyTree(int maxGenerationNum) {
        tree = new LinkedList<>();
        personSet = new HashSet<>();
        this.maxGenerationNum = maxGenerationNum;
    }

    public void addNonDirectRelative(Person name1, Person name2, boolean isSpouse) {
        if (!hasPerson(name1)) {
            addPerson(name1);
        }
        if (!hasPerson(name2)) {
            addPerson(name2);
        }
        if (hasNonDirectRelative(name1, name2)) {
            return;
        }

        if (isSpouse) {
            name1.setSpouse(name2);
            name2.setSpouse(name1);
        } else {
            name1.addSisBro(name2);
            name2.addSisBro(name1);
        }
    }

    public void addDirectRelative(Person child, Person parent) {
        if (!hasPerson(child)) {
            addPerson(child);
        }
        if (!hasPerson(parent)) {
            addPerson(parent);
        }
        if (hasDirectRelative(child, parent)) {
            return;
        }

        parent.addChild(child);

        if (parent.getSex() == Person.MALE) {
            child.setFather(parent);
        } else {
            child.setMother(parent);
        }
    }

    public int getMaxGenerationNum() {
        return maxGenerationNum;
    }

    public Set<Person> getPersonSet() {
        return personSet;
    }

    public List<Person> getTree() {
        return tree;
    }

    private void addPerson(Person name) {
        tree.add(name);
        personSet.add(name);
        if (name.getID() == -1) {
            name.setID(tree.size());
        }
    }

    private boolean hasPerson(Person person) {
        return tree.contains(person);
    }

    private boolean hasDirectRelative(Person person1, Person person2) {

        if (person1.getFather() == person2 || person1.getMother() == person2) {
            return true;
        }

        if (person2.getFather() == person1 || person2.getMother() == person1) {
            return true;
        }

        return false;
    }

    private boolean hasNonDirectRelative(Person person1, Person person2) {

        if (person1.getSpouse() == person2) {
            return true;
        }
        if (person1.getSisAndBro().contains(person2)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return tree.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FamilyTree)) return false;
        FamilyTree that = (FamilyTree) obj;

        return maxGenerationNum == that.maxGenerationNum &&
                personSet.equals(that.personSet);
    }
}
