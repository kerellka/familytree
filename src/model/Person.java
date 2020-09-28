package model;

import java.util.*;

public class Person {

    private final String name;
    private final String surname;
    private final String patronymic;
    private final boolean sex;
    private final Date birth;
    private final Date death;

    private Person father;
    private Person mother;
    private Person spouse;

    private final List<Person> sisAndBro;
    private final List<Person> childList;

    private int id;

    public static final boolean MALE = true;
    public static final boolean FEMALE = false;

    public Person(String name, String surname, String patronymic, boolean sex, Date birth, Date death) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.sex = sex;
        this.birth = birth;
        this.death = death;
        this.father = null;
        this.mother = null;
        this.spouse = null;
        this.id = -1;
        childList = new LinkedList<>();
        sisAndBro = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public boolean getSex() {
        return sex;
    }

    public Date getBirth() {
        return birth;
    }

    public Date getDeath() {
        return death;
    }

    public Person getFather() {
        return father;
    }

    public Person getMother() {
        return mother;
    }

    public Person getSpouse() {
        return spouse;
    }

    public int getID() {
        return id;
    }

    public List<Person> getSisAndBro () {
        return sisAndBro;
    }

    public List<Person> getChildList() {
        return childList;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void addChild(Person child) {
        childList.add(child);
    }

    public void addSisBro(Person sisBro) { sisAndBro.add(sisBro); }

    public boolean hasBirth() {
        return birth != null;
    }

    public boolean hasDeath() {
        return death != null;
    }

    public boolean hasFather() {
        return father != null;
    }

    public boolean hasMother() {
        return mother != null;
    }

    public boolean hasSpouse() {
        return spouse != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name: ");
        sb.append(name);
        sb.append("\nsurname: ");
        sb.append(surname);
        sb.append("\npatronymic: ");
        sb.append(patronymic);
        sb.append("\nsex: ");
        if (sex) {
            sb.append("Male");
        } else {
            sb.append("Female");
        }
        sb.append("\nlifetime: ");
        sb.append(birth);
        sb.append(" - ");
        if (death == null) {
            sb.append("xxx");
        } else {
            sb.append(death);
        }
        sb.append("\nspouse: ");
        if (spouse == null) {
            sb.append("none");
        } else {
            sb.append(spouse.getName());
        }
        sb.append("\nchildren: ");
        if (childList.isEmpty()) {
            sb.append("none\n");
        } else {
            sb.append("\n");
            for (Person child : childList) {
                sb.append(child.getName());
                sb.append(" ");
                sb.append(child.getSurname());
                sb.append(" ");
                sb.append(child.getBirth());
                sb.append(" - ");
                if (child.getDeath() == null) {
                    sb.append("xxx ");
                } else {
                    sb.append(child.getDeath());
                }
                if (child.getSex()) {
                    sb.append("Male");
                } else {
                    sb.append("Female");
                }
                sb.append("\n");
            }
        }


        return new String(sb);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        Person person = (Person) obj;

        boolean isNotDifferentDates = true;
        boolean isBirth;
        boolean isDeath;

        if (hasBirth() != person.hasBirth()) {
            isNotDifferentDates = false;
        } else if (hasDeath() != person.hasDeath()) {
            isNotDifferentDates = false;
        }

        if (birth != null && person.birth != null) {
            isBirth = birth.equals(person.birth);
        } else {
            isBirth = true;
        }
        if (death != null && person.death != null) {
            isDeath = death.equals(person.death);
        } else {
            isDeath = true;
        }

        return id == person.id &&
                sex == person.sex &&
                name.equals(person.name) &&
                surname.equals(person.surname) &&
                patronymic.equals(person.patronymic) &&
                isNotDifferentDates &&
                isBirth &&
                isDeath;
    }

    @Override
    public int hashCode() {
        int hash = name.hashCode() + surname.hashCode() + patronymic.hashCode();
        int hashBirth = 0;
        int hashDeath = 0;
        if (birth != null) {
            hashBirth = birth.hashCode();
        }
        if (death != null) {
            hashDeath = death.hashCode();
        }
        return hash + hashBirth + hashDeath;
    }
}
