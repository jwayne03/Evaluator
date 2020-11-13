package manager;

import model.Students;
import model.Subjects;
import org.xml.sax.SAXException;
import persitence.GenerateDOM;
import persitence.SaxVersion;
import worker.Worker;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author John Wayne Carreon
 */

public class Manager implements Runnable {

    private static Manager manager;
    private final GenerateDOM generateDOM;
    private final SaxVersion saxVersion;
    private Students student;
    private Subjects subject;

    private Manager() {
        saxVersion = new SaxVersion();
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
                case 1 -> createStudent();
                case 2 -> introduceSubjectIntoGrade();
                case 3 -> showInfo();
                case 4 -> saveData();
                case 5 -> System.out.println(giveGradesAverage());
                case 6 -> System.out.println(giveStudentGradeAverage());
                case 0 -> {
                    System.out.println("You have decided to finish, goodbye!");
                    exit = true;
                }
                default -> System.out.println("You need to introduce an option");
            }
        }
    }

    private void createStudent() {
        String name = Worker.askString("Introduce the name of the student:");
        String dni = Worker.askString("Introuce the DNI of the student:");
        generateDOM.student(name, dni);
        student = new Students(name, dni);
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

    private double giveGradesAverage() {
        double average = 0;
        int count = 0;

        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            File file = new File("students.xml");
            SaxVersion saxVersion = new SaxVersion();
            saxParser.parse(file, saxVersion);

            ArrayList<Students> stu = sax();

            String dni = Worker.askString("Introduce the DNI of the student do you want to find the average :");
            String subject = Worker.askString("Introduce the subject do you want to find: ");

            for (Students students : stu) {
                if (dni.equalsIgnoreCase(students.getDni())) {
                    for (Subjects subjects : students.getSubjects()) {
                        if (subject.equalsIgnoreCase(subjects.getSubject())) {
                            for (Double avg : subjects.getGrades()) {
                                average += avg;
                                count++;
                            }
                        }
                    }
                }
            }
            System.out.println();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return average / count;
    }

    private double giveStudentGradeAverage() {
        double average = 0;
        int count = 0;

        try {
            ArrayList<Students> stu = sax();
            String dni = Worker.askString("Introduce the dni of the student: ");

            for (Students students : stu) {
                if (dni.equalsIgnoreCase(students.getDni())) {
                    for (Subjects subjects : students.getSubjects()) {
                        for (Double avg : subjects.getGrades()) {
                            average += avg;
                            count++;
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return average / count;
    }

    private ArrayList<Students> sax() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        File file = new File("students.xml");
        SaxVersion saxVersion = new SaxVersion();
        saxParser.parse(file, saxVersion);
        ArrayList<Students> saxStudents = saxVersion.getStudents();
        return saxStudents;
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
}

