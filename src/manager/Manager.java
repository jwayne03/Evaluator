package manager;

import Persitence.GenerateDOM;
import utils.Printer;
import worker.Worker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Manager implements Runnable {

    private static Manager manager;
    private BufferedReader read;
    private GenerateDOM generateDOM;

    private Manager() {
        read = new BufferedReader(new InputStreamReader(System.in));
        generateDOM = new GenerateDOM();
    }

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager;
    }

    @Override
    public void run() {
        menu();
    }

    public void menu() {
        generateDOM.generateDocument();

        boolean exit = false;
        while (!exit) {

            Printer.printMenu();

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
                default:
                    System.out.println("You need to introduce an option");
                    break;
            }
        }
    }

    public void createStudent() {
        String name = Worker.askString("Introduce the name of the student:");
        String dni = Worker.askString("Introuce the DNI of the student:");
        generateDOM.student(name, dni);
    }

    public void introduceSubjectIntoGrade() {
        String dni = Worker.askString("Introduce de DNI of the student to set the grade:");
        String subject = Worker.askString("Introduce the name of the subject");
        int grade = Worker.askInt("Introduce the grade of the student");
        generateDOM.grades(dni, subject, grade);
    }

    public void showInfo() {
        generateDOM.readFile();
    }

    public void saveData() {
        generateDOM.generateXML();
    }
}

