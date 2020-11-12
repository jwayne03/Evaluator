package model;

import java.util.ArrayList;

public class Subjects {

    private String subject;
    private ArrayList<Double> grades;

    public Subjects(String subject) {
        this.subject = subject;
        grades = new ArrayList<>();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public void setGrades(double grades) {
        this.grades.add(grades);
    }

    //    public double getAverage() {
//        ArrayList<Students> students = new ArrayList<>();
//        ArrayList<Double> grades = new ArrayList<Double>();
//        int count = 0;
//        double average = 0;
//
//        for (Students i: students) {
//            average += i.getGrades();
//            grades.add(average);
//            count++;
//        }
//        return average / count;
//    }
}
