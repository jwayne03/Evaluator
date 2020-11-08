package manager;

import model.Students;
import org.xml.sax.SAXException;
import persitence.GenerateDOM;
import persitence.SaxVersion;
import worker.Worker;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author John Wayne Carreon
 */

public class Manager implements Runnable {

    private static Manager manager;
    private final GenerateDOM generateDOM;

    private Manager() {
        generateDOM = new GenerateDOM();
    }

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager;
    }

    @Override
    public void run() {
        generateDOM.generateDocument();

        boolean exit = false;
        while (!exit) {

            printMenu();

            int option = Worker.askInt("Introduce an option:");

            switch (option) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    introduceSubjectIntoGrade();
                    break;
                case 3:
                    showInfo();
                    break;
                case 4:
                    saveData();
                    break;
                case 5:
                    giveGradesAverage();
                    break;
                case 6:
                    giveStudentGradeAverage();
                    break;
                case 0:
                    System.out.println("You have decided to finish, goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("You need to introduce an option");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("-----------------------------------\n"
                + "1 - Insert student\n"
                + "2 - Introduce a grade in a subject\n"
                + "3 - Show info\n"
                + "4 - Save data\n"
                + "5 - Give average of subject\n"
                + "6 - Give student grade average\n"
                + "0 - Exit\n"
                + "-----------------------------------\n");
    }

    private void createStudent() {
        String name = Worker.askString("Introduce the name of the student:");
        String dni = Worker.askString("Introuce the DNI of the student:");
        generateDOM.student(name, dni);
    }

    private void introduceSubjectIntoGrade() {
        String dni = Worker.askString("Introduce de DNI of the student to set the grade:");
        String subject = Worker.askString("Introduce the name of the subject");
        double grade = Worker.askDouble("Introduce the grade of the student");
        generateDOM.grades(dni, subject, grade);
    }

    private void showInfo() {
        generateDOM.readFile();
    }

    private void saveData() {
        generateDOM.generateXML();
    }

    private void giveGradesAverage() {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            File file = new File("students.xml");
            SaxVersion saxVersion = new SaxVersion();
            saxParser.parse(file, saxVersion);
            List<Students> students = saxVersion.getGradeAverage();

            for (Students student : students) System.out.println(student.toString());

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private void giveStudentGradeAverage() {

    }
}

