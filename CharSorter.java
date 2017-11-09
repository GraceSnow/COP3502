import java.util.*;
import java.lang.*;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import static java.lang.Character.isWhitespace;

public class CharSorter {
    // Define methods/functions
    private static String getUserString() {
        /*
        Prints a welcome message and asks user to input a string
         Puts the string into an array
           Input - userString
           Output - welcome and input message
           returns - userString
        */
        String userString;
        Scanner myScanner = new Scanner(System.in);  // Initialize scanner

        System.out.println("Welcome to Character Sorter Program");
        System.out.println("Please input a string to be sorted");
        userString = myScanner.nextLine();  // Get string from user

        return userString;
    }

    private static char[] stringToArray(String userString) {
        /*
        Puts string into an array
            Parameters - userString
            Inputs - stringLength
            Output - userStringArray
            Returns - userStringArray
        */

        // Initialize variables and array
        int stringLength = userString.length();
        char[] userStringArray = new char[stringLength];

        for (int x = 0; x < stringLength; x++) {
            // Puts the characters in a string into an array
            userStringArray[x] = userString.charAt(x);
        }

        return userStringArray;
    }

/*    private static int charCount(char[] userStringArray, int x) {
        // Counts the number of times each character occurs     Note: Made to be used in a loop
        //  Input - userStringArray, x
        //  Output - tempCount
        //  Return - tempCount
        int tempCount = 0;

        for (int y = 0; userStringArray[x] == userStringArray[y]; y++) {
            // if element 1 equals element 2, increase frequencyCount
            tempCount++;
        }

        return tempCount;
    }*/

    private static char[] alphabeticalSort(char[] userStringArray) {
        // The goal of this method is to sort all of the characters
        // in alphabetical order.
        /*  Parameters - userString
            Inputs - stringLength, userString
            Output - userString
            Return - userString
        */

        // Initialize variables and array
        int arrayLength = userStringArray.length;

        for (int x = 0; x < arrayLength; x++) {
            for (int y = 0; y < arrayLength; y++) {
                if(userStringArray[x] < userStringArray[y]) {
                    // If element 1 > element 2, switch them    Note: A is less than B in ASCII
                    char temp = userStringArray[x];
                    userStringArray[x] = userStringArray[y];
                    userStringArray[y] = temp;
                }
            }
        }
        return userStringArray;
    }

    private static char[] frequencySort(char[] userStringArray) {
        // The goal of this method is to sort all of the characters
        // from highest frequency to lowest.
        /*  Input - userString
            Output - userString
            Return - userString
        */
        int arrayLength = userStringArray.length;
        int tempCount = 0;
        int[] frequencyArray = new int[arrayLength];
        userStringArray = alphabeticalSort(userStringArray); // sort array so same letters are together

        for (int x = 0; x < arrayLength; x++) {
            for (char anUserStringArray : userStringArray) {
                if (userStringArray[x] == anUserStringArray) {
                    // if element 1 equals element 2, increase tempCount
                    tempCount++;
                }
            }
            for (int i = x; i < (x + tempCount); i++) {
                // put temp count into an array with index matching the index of the corresponding chars in userStringArray
                frequencyArray[i] = tempCount;
            }
            x += tempCount - 1; // increment x by temp count so it doesn't recount frequency
            tempCount = 0; // reset temp count
        }

        for (int a = 0; a < arrayLength; a++) {
            // Sorts array by frequency
            for (int b = 0; b < arrayLength; b++) {
                // check character frequencies
                if (frequencyArray[a] > frequencyArray[b]) {
                    //if element 1 > element 2, switch character elements that correspond to the frequency
                    char temp = userStringArray[a];
                    userStringArray[a] = userStringArray[b];
                    userStringArray[b] = temp;

                    // switch frequency elements so they continue to correspond to the character elements
                    int tempo = frequencyArray[a];
                    frequencyArray[a] = frequencyArray[b];
                    frequencyArray[b] = tempo;
                }
            }
        }
        for (int i = 0; i < arrayLength; i++) {
            for (int j = 0; j < arrayLength; j++) {
                // sorts by ASCII value, if a character's frequency equals another character's frequency
                if (frequencyArray[i] == frequencyArray[j]) {
                    do {
                        char temp = userStringArray[i];
                        userStringArray[i] = userStringArray[j];
                        userStringArray[j] = temp;

                        int tempo = frequencyArray[i];
                        frequencyArray[i] = frequencyArray[j];
                        frequencyArray[j] = tempo;
                    } while (userStringArray[i] < userStringArray[j]);
                }
            }
        }

        return userStringArray;
    }

    private static void charTypes(char[] userStringArray) {
        // The goal of this method is to sort the data into four categories:
        // Textual characters, Numeric characters, WhiteSpace characters,
        // and Symbolic characters
        /*  Input - userStringArray
            Output - numTextChars , numNumericChars, numWhiteSpaceChars, numSymbolicChars
            Return - none
         */

        int numTextChars = 0, numNumericChars = 0, numWhiteSpaceChars = 0, numSymbolicChars = 0;

        for (char anUserStringArray : userStringArray) {
            if (isDigit(anUserStringArray)) {
                numNumericChars++;
            } else if (isLetter(anUserStringArray)) {
                numTextChars++;
            } else if (isWhitespace(anUserStringArray)) {
                numWhiteSpaceChars++;
            } else {
                numSymbolicChars++;
            }
        }

        System.out.println("Textual Character count: " + numTextChars);
        System.out.println("Numerical Character count: " + numNumericChars);
        System.out.println("WhiteSpace Character count: " + numWhiteSpaceChars);
        System.out.println("Symbolic Character count: " + numSymbolicChars);
    }

    private static int menuDisplayAndSelection() {
        /*
        Displays menu and asks for user choice.
            returns - menuSelection
         */
        Scanner myScanner = new Scanner(System.in);

        // Print menu
        System.out.println("Please select the option you would like to see");
        System.out.println();
        System.out.println("1. Display character frequencies alphabetically");
        System.out.println("2. Display sorted frequencies");
        System.out.println("3. Show types of character frequencies");
        System.out.println("4. Exit");
        System.out.println();

        // Get player input for menu
        System.out.print("Choose an option: ");
        int menuSelection;
        try {
            menuSelection = myScanner.nextInt();

            // if user doesn't enter 1, 2, 3, or 4, throw an exception
            if ((menuSelection != 1) && (menuSelection != 2) && (menuSelection != 3) && (menuSelection != 4)) {
                throw new InputMismatchException();
            }

        } catch (InputMismatchException except) {
            System.out.println();
            System.out.println("Error, bad input, please enter a number 1-4");
            menuSelection = 0;
        }
        finally {
            myScanner.nextLine();
        }

        return menuSelection;
    }

    private static void displayFrequency(char[] sortedArray) {
        // Displays the frequencies of an array
        //  Input - sortedArray
        //  Output - tempCount, sortedArray character

        int arrayLength = sortedArray.length;
        int tempCount;

        for (int x = 0; x < arrayLength; x++) {

            tempCount = 0;
            for (char aSortedArray : sortedArray) {
                if (sortedArray[x] == aSortedArray) {
                    // if element 1 equals element 2, increase tempCount
                    tempCount++;
                }
            }
            System.out.println(sortedArray[x] + " freq: " + tempCount);
            x += tempCount - 1;
        }
    }

    public static void main(String args[]) {
        int menuSelection = 0;

        // Display welcome message and get a string from the user
        String userString = getUserString();
        // Convert the string into an array
        char[] userStringArray = stringToArray(userString);
        char[] frequencyArray;
        char[] alphabetArray;

        while (menuSelection != 4) {
            System.out.println();
            menuSelection = menuDisplayAndSelection();

            switch (menuSelection) {
                case 1:
                    alphabetArray = alphabeticalSort(userStringArray);
                    // prints output
                    displayFrequency(alphabetArray);
                    break;

                case 2:
                    frequencyArray = frequencySort(userStringArray);
                    // print output
                    displayFrequency(frequencyArray);
                    break;

                case 3:
                    // Print character types
                    charTypes(userStringArray);
                    break;

                case 4:
                    menuSelection = 4;
                    System.out.println();
                    System.out.println("Character Sorter Exited Successfully");
                    break;

                default:
                    break;
            }
        }
    }
}