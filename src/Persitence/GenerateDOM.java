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
    private File file;

    // Create consturctor for generate new DOM
    public GenerateDOM() {
        try {
            file = new File("students.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            if (!file.exists()) {
                document = builder.newDocument();
            } else {
                document = builder.parse("students.xml");
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
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
}