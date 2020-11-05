package manager;

import Persitence.GenerateDOM;

public class Manager implements Runnable {

    @Override
    public void run() {
        generateDOM();
    }

    public void generateDOM() {
        GenerateDOM generateDOM = new GenerateDOM();
        generateDOM.generateDocument();
        generateDOM.generateXML();
    }
}

