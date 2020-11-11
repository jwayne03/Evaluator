package persitence;

import model.Students;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxVersion extends DefaultHandler {

    private final List<Students> students = new ArrayList<>();
    private final StringBuilder stringBuilder = new StringBuilder();
    private Students student;

    public List<Students> getGradeAverage(String dni, String subject, String filter) {
        if (filter.equalsIgnoreCase("subject average")) {
            getSubjectAverageOfStudent(dni, subject);
        }

        if (filter.equalsIgnoreCase("student average")) {
            getStudentAverage(dni,subject);
        }
        return students;
    }

    public List<Students> getStudentAverage(String dni, String name) {
        for (int i = 0; i < students.size(); i++) {
            if (dni.equalsIgnoreCase(student.getDni())) {
                if (name.equalsIgnoreCase(student.getName())) {
                    System.out.println("The total average of the student is: " + student.getGrades());
                } else {
                    System.err.println("The name of the student doesn't exist");
                }
            } else {
                System.err.println("The dni of the student doesn't exist");
            }
        }
        return students;
    }

    private void getSubjectAverageOfStudent(String dni, String subject) {
        for (int i = 0; i < students.size(); i++) {
            if (dni.equalsIgnoreCase(student.getDni())) {
                if (subject.equalsIgnoreCase(student.getSubject())) {
                    System.out.println("The average of the student in: " + subject + " is " + student.getGrades());
                } else {
                    System.err.println("This subject doesn't exists");
                }
            } else {
                System.err.println("Student not found or not registered.");
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        stringBuilder.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "student":
                student = new Students();
                students.add(student);
                student.setDni(attributes.getValue("dni"));
            case "subject":
                student.setSubject(attributes.getValue("name"));
            case "name":
            case "grade":
                stringBuilder.delete(0, stringBuilder.length());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "name" -> student.setName(stringBuilder.toString());
            case "grade" -> student.setGrades(stringBuilder.toString());
        }
    }
}
