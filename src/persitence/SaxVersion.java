package persitence;

import model.Students;
import model.Subjects;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SaxVersion extends DefaultHandler {

    private final ArrayList<Students> students = new ArrayList<>();
    private final StringBuilder stringBuilder = new StringBuilder();
    private Students student;
    private Subjects subject;

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
                subject.setSubject(attributes.getValue("name"));
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
            case "grade" -> subject.setGrades(Double.parseDouble(stringBuilder.toString()));
        }
    }
    public ArrayList<Students> getStudents(){
        return students;
    }
}
