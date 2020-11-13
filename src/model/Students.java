package model;

import java.util.ArrayList;

/**
 * @author John Wayne Carreon
 */

public class Students {

    private String name;
    private String dni;
    private ArrayList<Subjects> subjects;

    public Students(String name, String dni) {
        this.name = name;
        this.dni = dni;
        subjects = new ArrayList<>();
    }

    public Students() {
        subjects = new ArrayList<>();
    }

    public ArrayList<Subjects> getSubjects() { return subjects; }
    public void setSubjects(Subjects setSubjects) { this.subjects.add(setSubjects); }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    @Override
    public String toString() {
        return "Students: " +
                " Name: " + name +
                " DNI: " + dni;
    }
}
