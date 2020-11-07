package main;

import manager.Manager;

/**
 * @author John Wayne Carreon
 */
public class Main {
    public static void main(String[] args) {
        Manager manager = Manager.getInstance();
        manager.run();
    }
}
