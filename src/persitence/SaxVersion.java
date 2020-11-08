package persitence;

import model.Students;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxVersion extends DefaultHandler {

    private List<Students> students = new ArrayList<>();
    private StringBuilder stringBuilder = new StringBuilder();
    private Students student;

    public List<Students> getStudents() {
        return students;
    }

}
