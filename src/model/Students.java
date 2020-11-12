package model;

/**
 * @author John Wayne Carreon
 */

public class Students {

    private String name;
    private String dni;


    public Students(String name, String dni) {
        this.name = name;
        this.dni = dni;

    }

    public Students() { }
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
