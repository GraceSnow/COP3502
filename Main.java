import java.util.*;

public class Main {
    // Define methods
    public static int menuDisplayAndSelection(Scanner myScanner) {
        /*
        Displays menu and asks for user choice.
            returns - menuSelection
         */
        int menuSelection;

        // Print menu
        System.out.println("1. List Pokemon");
        System.out.println("2. Add Pokemon");
        System.out.println("3. Check a Pokemon’s Stats");
        System.out.println("4. Evolve Pokemon");
        System.out.println("5. Sort Pokemon");
        System.out.println("6. Exit");
        System.out.println();

        // Get player input for menu
        System.out.print("What would you like to do? ");

        try {
            menuSelection = myScanner.nextInt();

            // if user doesn't enter 1, 2, 3, 4, 5, and 6 throw an exception
            if ((menuSelection != 1) && (menuSelection != 2) && (menuSelection != 3) && (menuSelection != 4)
                    && (menuSelection != 5) && (menuSelection != 6)) {
                throw new InputMismatchException();
            }
        }
        catch (InputMismatchException except) {
            System.out.println();
            System.out.println("This is not a valid choice. Try again.");
            menuSelection = 0;
        }
        finally {
            myScanner.nextLine();
        }
        return menuSelection;
    }

    public static void printPokemonList(Pokedex myPokedex) {
        for(int i = 0; i < myPokedex.listPokemon().length; i++) {
            if(myPokedex.listPokemon()[i] != null) {
                System.out.println((i + 1) + ". " + myPokedex.listPokemon()[i]);
            }
            else {
                break;
            }
        }
    }

    public static void pokemonStats(Scanner myScanner, Pokedex pokedex) {
        int[] statsList;

        System.out.println();
        System.out.print("Please enter the Pokemon of interest: ");
        String pokemonSpecies = myScanner.nextLine();

        statsList = pokedex.checkStats(pokemonSpecies);

        if(statsList[0] != 0 && statsList[1] != 0 && statsList[2] != 0) {
            System.out.println();
            System.out.println("The stats for " + pokemonSpecies + " are: ");
            System.out.println("Attack: " + statsList[0]);
            System.out.println("Defense: " + statsList[1]);
            System.out.println("Speed: " + statsList[2]);
        }
    }

    public static void addNewPokemon(Scanner myScanner, Pokedex pokedex) {
        System.out.println();
        System.out.print("Please enter the Pokemon's species: ");
        String pokemonSpecies = myScanner.nextLine();
        pokedex.addPokemon(pokemonSpecies);
    }

    public static void pokemonEvolve(Scanner myScanner, Pokedex pokedex) {
        System.out.println();
        System.out.print("Please enter the pokemon of Interest: ");
        String pokemonSpecies = myScanner.nextLine();
        boolean evolved = pokedex.evolvePokemon(pokemonSpecies);

        if(evolved) {
            System.out.println(pokemonSpecies + " has evolved!");
        }
        else {
            System.out.println("Missing");
        }
    }

    public static void main(String args[]) {
        // Initialize variables
        int menuSelection = 0;
        int maxPokemon;     // Number of Pokemon array can hold
        Pokemon myPokemon;
        Pokedex myPokedex;
        String[] listOfPokemon;
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Welcome to your new PokeDex!");
        System.out.print("How many Pokemon are in your region: ");

        maxPokemon = myScanner.nextInt();
        System.out.println();

        myPokedex = new Pokedex(maxPokemon);
        listOfPokemon = new String[maxPokemon];

        System.out.println("Your new Pokedex can hold " + maxPokemon + " Pokemon.");
        System.out.println("Let's start using it!");

        // while loop and print menu
        while(menuSelection != 6) {
            System.out.println();
            menuSelection = menuDisplayAndSelection(myScanner);

            switch (menuSelection) {
                case 1: // List Pokemon
                    printPokemonList(myPokedex);
                    break;

                case 2: // Add Pokemon
                    addNewPokemon(myScanner, myPokedex);
                    break;

                case 3: // Check a Pokemon's Stats
                    pokemonStats(myScanner, myPokedex);
                    break;

                case 4: // Evolve Pokemon
                    pokemonEvolve(myScanner, myPokedex);
                    break;

                case 5: // Sort Pokemon
                    myPokedex.sortPokedex();
                    break;

                case 6: // Exit
                    menuSelection = 6;
                    break;

                default:
                    break;
            }
        }
    }
}