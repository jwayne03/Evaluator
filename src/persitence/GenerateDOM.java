package persitence;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author John Wayne Carreon
 */
public class GenerateDOM {

    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;
    private Element school;
    private Element student;
    private Element studentName;
    private File file;

    public GenerateDOM() {
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            file = new File("students.xml");

            if (!file.exists()) document = builder.newDocument();
            else document = builder.parse("students.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateDocument() {
        if (file.exists()) {
            school = searchNode();
        } else {
            school = document.createElement("stucom");
            document.appendChild(school);
        }
    }

    public void generateXML() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Source source = new DOMSource(document);
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            Result result = new StreamResult(pw);
            transformer.transform(source, result);
            pw.close();
            fw.close();
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element searchNode() {
        NodeList studentList = document.getElementsByTagName("stucom");
        Node node = studentList.item(0);
        return (Element) node;
    }

    public void student(String name, String dni) {
        student = document.createElement("student");
        studentName = document.createElement("name");
        student = findDni(dni);
        if (student == null) {
            student = document.createElement("student");
        }
        student.appendChild(studentName);
        studentName.setTextContent(name);
        student.setAttribute("dni", dni);
        school.appendChild(student);
    }

    private Element findDni(String dni) {
        document.getDocumentElement().normalize();
        Element studentNode = null;
        NodeList studentNodeList = document.getElementsByTagName("student");

        return findDniNode(dni, studentNode, studentNodeList);
    }

    private Element findDniNode(String dni, Element studentNode, @NotNull NodeList studentNodeList) {
        for (int i = 0; i < studentNodeList.getLength(); i++) {
            Node student = studentNodeList.item(i);
            if (student.getNodeType() == Node.ELEMENT_NODE) {
                NamedNodeMap namedNodeMap = student.getAttributes();
                for (int j = 0; j < namedNodeMap.getLength(); j++) {
                    if (namedNodeMap.item(j).getNodeValue().equalsIgnoreCase(dni)) {
                        studentNode = (Element) student;
                    }
                }
            }
        }
        return studentNode;
    }

    public void grades(String dni, String subject, double grades) {
        Element studentNode = findDni(dni);
        Element subjectNode = findSubject(dni, subject);

        if (studentNode != null) {
            if (subjectNode == null) {
                createGrade(subject, grades, studentNode);
            } else {
                System.out.println("The dni you have introduced doesn't exits");
            }
        }
    }

    private void createGrade(String subject, double grades, Element studentNode) {
        Element subjectNode;
        subjectNode = document.createElement("subject");
        subjectNode.setAttribute("name", subject);
        studentNode.appendChild(subjectNode);

        Element grade = document.createElement("grade");
        grade.appendChild(document.createTextNode(String.valueOf(grades)));
        subjectNode.appendChild(grade);
        document.normalize();
    }

    private Element findSubject(String dni, String subject) {
        Element subjectNode = null;
        Element studentNode = findDni(dni);

        return findSubjectNode(subject, subjectNode, studentNode);
    }

    private Element findSubjectNode(String subject, Element subjectNode, Element studentNode) {
        if (studentNode != null) {
            NodeList childNodeStudent = studentNode.getChildNodes();
            if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                for (int i = 0; i < childNodeStudent.getLength(); i++) {
                    Node childNode = childNodeStudent.item(i);
                    if (childNode.getNodeName().equalsIgnoreCase("subject")) {
                        NamedNodeMap namedNodeMap = childNode.getAttributes();
                        for (int j = 0; j < namedNodeMap.getLength(); j++) {
                            if (namedNodeMap.item(j).getNodeValue().equalsIgnoreCase(subject)) {
                                subjectNode = (Element) childNode;
                            }
                        }
                    }
                }
            }
        }
        return subjectNode;
    }

    public void readFile() {
        document.getDocumentElement().normalize();
        NodeList studentList = document.getElementsByTagName("student");
        for (int i = 0; i < studentList.getLength(); i++) {
            Node node = studentList.item(i);
            System.out.print("<" + node.getNodeName());
            if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                NamedNodeMap map = node.getAttributes();
                for (int j = 0; j < map.getLength(); j++)
                    System.out.print(" " + map.item(j).getNodeName()
                            + "='" + map.item(j).getNodeValue() + "'");

                System.out.print(">\n");

                NodeList nodeList = node.getChildNodes();
                for (int h = 0; h < nodeList.getLength(); h++) {
                    Node childNode = nodeList.item(h);
                    if (childNode != null && childNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (childNode.getNodeName().equals("subject")) {
                            NamedNodeMap namedNodeMap = childNode.getAttributes();
                            System.out.print("	<" + childNode.getNodeName());
                            for (int x = 0; x < namedNodeMap.getLength(); x++)
                                System.out.print(" " + namedNodeMap.item(x)
                                        .getNodeName() + "='" + namedNodeMap.item(x)
                                        .getNodeValue() + "'");
                            System.out.println(">");

                            NodeList grades = childNode.getChildNodes();
                            for (int j = 0; j < grades.getLength(); j++) {
                                Node childGrade = grades.item(j);
                                if (childGrade != null && childGrade.getNodeType() == Node.ELEMENT_NODE) {
                                    System.out.print("	    <" + childGrade.getNodeName() + ">");
                                    System.out.print(childGrade.getTextContent());
                                    System.out.print("</" + childGrade.getNodeName() + ">\n");
                                }
                            }
                            System.out.println("    </" + childNode.getNodeName() + ">");
                        } else {
                            System.out.print("	<" + childNode.getNodeName() + ">");
                            System.out.print(childNode.getTextContent());
                            System.out.println("</" + childNode.getNodeName() + ">");
                        }
                    }
                }
            }
            System.out.print("</" + node.getNodeName() + ">\n");
        }
    }
}