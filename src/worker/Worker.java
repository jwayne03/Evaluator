package worker;

import java.io.*;
import java.util.ArrayList;

/**
 * @author John Wayne Carreon
 */

/**
 * Worker class is composed of several functions with error control
 * to allow having a flow of the program with everything controlled
 * so that at no time we get an error
 */
public class Worker {

    /**
     * @param message
     * @ this function is for ask to introduce a String and controls
     * if the string is correctly introduced, and controls if
     * you introduce a Integer type or null parameter.
     */
    public static String askString(String message) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        do {
            try {
                System.out.println(message);
                answer = read.readLine();
                if (answer.equals("")) {
                    System.out.println("You must write something");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (answer.equals(""));
        return answer;
    }

    /**
     * @param message this function is for ask to introduce a String and convert
     *                to lower case and controls if the string is correctly introduced,
     *                and controls if you introduce a Integer type or null parameter.
     */
    public static String askStringToLowerCase(String message) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        do {
            try {
                System.out.println(message);
                answer = read.readLine().toLowerCase();
                if (answer.equals("")) System.out.println("You must write something");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } while (answer.equals(""));
        return answer;
    }

    /**
     * @param message this function is for ask to introduce a String and convert
     *                to upper case and controls if the string is correctly introduced,
     *                and controls if you introduce a Integer type or null parameter.
     */
    public static String askStringToUpperCase(String message) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        do {
            try {
                System.out.println(message);
                answer = read.readLine().toUpperCase();
                if (answer.equals("")) System.out.println("You must write something");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } while (answer.equals(""));
        return answer;
    }

    /**
     * @param message
     * @param a
     * @param b
     * @return askString function check if the string is introduced correctly
     * and have two parameters a , b and returns
     */
    public static String askString(String message, String a, String b) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        do {
            answer = askString(message);
            if (!answer.equalsIgnoreCase(a) && !answer.equalsIgnoreCase(b)) {
                System.out.println("ERROR! Write " + a + " or " + b + " please");
            }
        } while (!answer.equalsIgnoreCase(a) && !answer.equalsIgnoreCase(b));
        return answer;
    }

    /**
     * @param message
     * @param wordsAccepted
     * @return askString function have parameter wordsAccepted, is an ArrayList<>
     * and check if the data introduce is the same that contains in the ArrayList<>
     */
    public static String askString(String message, ArrayList<String> wordsAccepted) {
        String answer;
        boolean isWordOk;
        do {
            for (String word : wordsAccepted) {
                System.out.println(word);
            }
            answer = Worker.askString(message);
            isWordOk = wordIsOk(answer, wordsAccepted);
            if (!isWordOk) {
                System.out.println("Wrong answer!");
            }
        } while (!isWordOk);
        return answer;
    }

    /**
     * @param word
     * @param wordsAccepted
     * @return check if the word introduced equals the String inside of the ArrayList
     */
    private static boolean wordIsOk(String word, ArrayList<String> wordsAccepted) {
        for (String w : wordsAccepted) {
            if (w.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param message
     * @return askInt check if the input is an Integer and controls error,
     * if the data introduce is null or a String
     */
    public static int askInt(String message) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        boolean error;
        do {
            try {
                System.out.println(message);
                num = Integer.parseInt(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error input / output.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please, write integer number.");
                error = true;
            }
        } while (error);
        return num;
    }

    /**
     * @param message
     * @param min
     * @param max
     * @return askInt, this function ask an int and only accepts between min and max
     */
    public static int askInt(String message, int min, int max) {
        int num;
        do {
            num = askInt(message);
            if (num < min || num > max) {
                System.out.println("Unavailable option. Choose between STARTER(1), MAIN(2), DESSERT(3)");
            }
        } while (num < min || num > max);
        return num;
    }

    /**
     * @param message
     * @return this function accept double and int and control if the user
     * introduce a number and not a String
     */
    public static double askDouble(String message) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double num = 0;
        boolean error;
        do {
            try {
                System.out.println(message);
                num = Double.parseDouble(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error input / output.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please, write double number.");
                error = true;
            }
        } while (error);
        return num;
    }

    /**
     * @param message
     * @param min
     * @param max
     * @return function to askDouble between min and max
     */
    public static double askDouble(String message, int min, int max) {
        double num;
        do {
            num = askDouble(message);
            if (num < min || num > max) {
                System.out.println("Error, the number must be between " + min + " and " + max);
            }
        } while (num < min || num > max);
        return num;
    }
}