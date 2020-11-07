package persitence;

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

    private Document document;
    private Element school;
    private Element student;
    private Element studentName;
    private Element subjectName;
    private Element subjectGrade;
    private File file;

    public GenerateDOM() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            file = new File("students.xml");

            if (!file.exists()) {
                document = builder.newDocument();
            } else {
                document = builder.parse("students.xml");
            }
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

    public Element searchNode() {
        NodeList studentList = document.getElementsByTagName("stucom");
        Node node = studentList.item(0);
        return (Element) node;
    }

    public void student(String name, String dni) {
        student = document.createElement("student");
        studentName = document.createElement("name");
        student.appendChild(studentName);
        studentName.setTextContent(name);
        student.setAttribute("dni", dni);
        school.appendChild(student);
    }

    public void grades(String dni, String subject, int grade) {
        subjectName = document.createElement("subject");
        student.appendChild(subjectName);
        subjectName.setAttribute("name", subject);
        subjectGrade = document.createElement("grade");
        subjectGrade.setTextContent(String.valueOf(grade));
        subjectName.appendChild(subjectGrade);
    }

    public void generateXML() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(); //Pasa de memoria a disco
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

    public void readFile() {
        document.getDocumentElement().normalize();
        NodeList studentList = document.getElementsByTagName("student");

        for (int i = 0; i < studentList.getLength(); i++) {
            Node node = studentList.item(i);
            System.out.print("<" + node.getNodeName());

            if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                NamedNodeMap map = node.getAttributes();
                for (int j = 0; j < map.getLength(); j++) {
                    System.out.print(" " + map.item(j).getNodeName() + "='" + map.item(j).getNodeValue().toString() + "'");
                }

                System.out.print(">\n");

                NodeList nodeList = node.getChildNodes();

                for (int h = 0; h < nodeList.getLength(); h++) {
                    Node childNode = nodeList.item(h);

                    if (childNode != null && childNode.getNodeType() == Node.ELEMENT_NODE) {


                        if (childNode.getNodeName().equals("subject")) {
                            NamedNodeMap namedNodeMap = childNode.getAttributes();
                            System.out.print("	<" + childNode.getNodeName());
                            for (int x = 0; x < namedNodeMap.getLength(); x++) {
                                System.out.print(" " + namedNodeMap.item(x).getNodeName() + "='" + namedNodeMap.item(x).getNodeValue().toString() + "'");
                            }

                            System.out.println(">");

                            NodeList grades = childNode.getChildNodes();
                            Node childGrade = grades.item(1);

                            if (childGrade != null && childGrade.getNodeType() == Node.ELEMENT_NODE) {
                                System.out.print("	    <" + childGrade.getNodeName() + ">");
                                System.out.print(childGrade.getTextContent());
                                System.out.print("</" + childGrade.getNodeName() + ">\n");
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

