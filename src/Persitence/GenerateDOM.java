package Persitence;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

public class GenerateDOM {

    private Document document;

    // Create consturctor for generate new DOM
    public GenerateDOM() {
        try {
            document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    // Generate new document
    // TODO: set atributes with user information
    public void generateDocument() {
        Element students = document.createElement("students");
        document.appendChild(students);

        Element student = document.createElement("student");
        students.appendChild(student);
//        student.setAttribute("code", "1");

        Element name = document.createElement("name");
        name.appendChild(document.createTextNode("Keyboard"));
        student.appendChild(name);
    }

    // Generate new document XML
    public void generateXML() {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer();

            Source source = new DOMSource(document);
            File file = new File("students.xml");
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            Result result = new StreamResult(printWriter);

            transformer.transform(source, result);
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}