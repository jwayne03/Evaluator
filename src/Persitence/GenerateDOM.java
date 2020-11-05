package Persitence;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

public class GenerateDOM {

    private Document document;

    // Create consturctor for generate new DOM
    public GenerateDOM() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            generateXML(builder);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generate new document
    // TODO: set atributes with user information
    public void generateDocument() {
        Element school = document.createElement("stucom");
        document.appendChild(school);

        Element student = document.createElement("wayne");
        school.appendChild(student);

        Element subject = document.createElement("java");
        student.appendChild(subject);
    }

    // Generate new document XML
    public void generateXML(DocumentBuilder builder) throws IOException, SAXException {
        File file = new File("students.xml");
        if (!file.exists()) document = builder.newDocument();
        else builder.parse("students.xml");
    }
}