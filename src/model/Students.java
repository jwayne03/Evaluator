package model;

/**
 * @author John Wayne Carreon
 */

public class Students {

    private String name;
    private String dni;
    private String subject;
    private String grades;

    public Students(String name, String dni, String subject, String grades) {
        this.name = name;
        this.dni = dni;
        this.subject = subject;
        this.grades = grades;
    }

    public Students() { }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getGrades() { return grades; }
    public void setGrades(String grades) { this.grades = grades; }

    @Override
    public String toString() {
        return "Students: " +
                " Name: " + name +
                " DNI: " + dni +
                " Subject: " + subject +
                " Grade: " + grades;
    }
}
