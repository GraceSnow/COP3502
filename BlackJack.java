import java.util.*;
import java.lang.Math;

public class BlackJack {

    public static void main(String args[]) {

        int card;
        int playerHand = 0, dealerHand;
        int playerWins = 0, dealerWins = 0, ties = 0;
        int menuSelection = 0;
        int gameRound = 0;
        int y = 0;

        Random randNum = new Random();
        //Scanner myScanner = new Scanner(System.in);

        while (menuSelection != 4) {

            // Start game
            System.out.println("START GAME #" + (gameRound + 1));
            System.out.println();

            while (y == 0 || menuSelection != 4) {

                // Generate random number and assign it to a card
                card = randNum.nextInt(13) + 1;     // 1 to 13

                // Determine what card was dealt and add its value to the player's hand
                playerHand += dealCards(card);

                // Print player's hand
                System.out.println("Your hand is: " + playerHand);
                System.out.println();

                // If player's hand is greater than 21, player loses and a new game starts
                if (playerHand > 21) {
                    System.out.println("You exceeded 21! You lose :(");
                    dealerWins += 1;
                    playerHand = 0;
                    y = 1; // Exit while loop
                }
                // If player's hand is 21, player wins and a new game starts
                else if (playerHand == 21) {
                    System.out.println("BLACKJACK! You win!");
                    playerWins += 1;
                    playerHand = 0; // Reset hand for new game
                    y = 1; // Exit while loop
                } else {
                    // Print menu and ask for user choice
                    menuSelection = menuDisplayAndSelection(menuSelection);

                    // Determine which option the player chose
                    switch (menuSelection) {

                        case 1:
                            // Rerun inner loop
                            break;

                        case 2:
                            dealerHand = randNum.nextInt(11) + 16; // 16 to 26
                            System.out.println("Dealer's hand: " + dealerHand); // Print dealer and player hands
                            System.out.println("Your hand is: " + playerHand);
                            System.out.println();
                            // Determine the winner
                            if (dealerHand > 21) {
                                System.out.println("You win!");
                                playerWins += 1;
                                playerHand = 0; // Reset hands for new game
                                y = 1; // Exit while loop
                            } else if (playerHand == dealerHand) {
                                System.out.println("It's a tie! No one wins!");
                                ties += 1;
                                playerHand = 0;
                                y = 1; // Exit while loop
                            } else if (playerHand > dealerHand) {
                                System.out.println("You win!");
                                playerWins += 1;
                                playerHand = 0;
                                y = 1; // Exit while loop
                            } else {
                                System.out.println("Dealer wins!");
                                dealerWins += 1;
                                playerHand = 0;
                                y = 1; // Exit while loop
                            }
                            System.out.println();
                            break;

                        case 3:
                            // Display statistics
                            gameStatistics(playerWins, dealerWins, ties, gameRound);

                            //** Display a working menu after displaying the game stats
                            while (menuSelection == 3) {
                                // Print menu and ask for user choice
                                menuSelection = menuDisplayAndSelection(menuSelection);

                                // Determine which option the player chose
                                switch (menuSelection) {

                                    case 1:
                                        // Player picked 1. Get another card.
                                        // Rerun inner loop
                                        break;

                                    case 2:
                                        // Player picked 2. Hold hand

                                        dealerHand = randNum.nextInt(11) + 16; // 16 to 26

                                        System.out.println("Dealer's hand: " + dealerHand); // Print dealer and player hands
                                        System.out.println("Your hand is: " + playerHand);
                                        System.out.println();
                                        // Determine the winner
                                        if (dealerHand > 21) {
                                            System.out.println("You win!");
                                            playerWins += 1;
                                            playerHand = 0; // Reset hands for new game
                                            y = 1; // Exit while loop
                                        } else if (dealerHand == playerHand) {
                                            System.out.println("It's a tie! No one wins!");
                                            ties += 1;
                                            playerHand = 0;
                                            y = 1; // Exit while loop
                                        } else if (dealerHand < playerHand) {
                                            System.out.println("You win!");
                                            playerWins += 1;
                                            playerHand = 0;
                                            y = 1; // Exit while loop
                                        } else {
                                            System.out.println("Dealer wins!");
                                            dealerWins += 1;
                                            playerHand = 0;
                                            y = 1; // Exit while loop
                                        }
                                        System.out.println();
                                        break;

                                    case 3:
                                        // Display statistics
                                        gameStatistics(playerWins, dealerWins, ties, gameRound);
                                        break;

                                    case 4:
                                        y = 1;
                                        menuSelection = 4;

                                    default:
                                        while ((menuSelection != 1) && (menuSelection != 2) && (menuSelection != 3) && (menuSelection != 4)) {
                                            // Print menu and ask for user choice
                                            menuSelection = menuDisplayAndSelection(menuSelection);
                                        }
                                        break;
                                }
                            }   //** End while loop
                            break;

                        case 4:
                            y = 1;
                            menuSelection = 4;

                        default:
                            while ((menuSelection != 1) && (menuSelection != 2) && (menuSelection != 3) && (menuSelection != 4)) {
                                // Print menu and ask for user choice
                                menuSelection = menuDisplayAndSelection(menuSelection);
                            }
                            break;

                    }
                }   //** End else statement
            }
            gameRound += 1;
            y = 0; // Resets y to 0
            System.out.println();
        }

    }

    private static int dealCards(int card) {
        /*
        Determines what card was dealt and adds its value to player's hand
            Input - card
            returns - playerHand
        */
        int playerHand = 0;

        // Determine what card was dealt and add its value to the player's hand
        switch (card) {

            case 1:
                System.out.println("Your card is an ACE!");
                playerHand += card;
                break;

            case 11:
                System.out.println("Your card is a JACK!");
                playerHand += 10;
                break;

            case 12:
                System.out.println("Your card is a QUEEN!");
                playerHand += 10;
                break;

            case 13:
                System.out.println("Your card is a KING!");
                playerHand += 10;
                break;

            default:
                System.out.println("Your card is a " + card + "!");
                playerHand += card;
                break;
        }
        return playerHand;
    }

    private static int menuDisplayAndSelection(int menuSelection) {
        /*
        Displays menu and asks for user choice.
            returns - menuSelection
         */

        Scanner myScanner = new Scanner(System.in);

        // Print menu
        System.out.println("1. Get another card");
        System.out.println("2. Hold hand");
        System.out.println("3. Print game statistics");
        System.out.println("4. Exit");
        System.out.println();

        // Get player input for menu
        System.out.print("Choose an option: ");
        try {
            menuSelection = myScanner.nextInt();

            // if user doesn't enter 1, 2, 3, or 4, throw an exception
            if ((menuSelection != 1) && (menuSelection != 2) && (menuSelection != 3) && (menuSelection != 4)) {
                throw new InputMismatchException();
            }

        } catch (InputMismatchException except) {
            System.out.println();
            System.out.println("Invalid input!");
            System.out.println("Please enter an integer value between 1 and 4.");
        }
        finally {
            myScanner.nextLine();
        }
        System.out.println();

        return menuSelection;
    }

    private static void gameStatistics(int playerWins, int dealerWins, int ties, int gameRound) {
        // Display statistics
        System.out.println("Number of Player wins: " + playerWins);
        System.out.println("Number of Dealer wins: " + dealerWins);
        System.out.println("Number of tie games: " + ties);
        System.out.println("Total # of games played is: " + gameRound);
        System.out.println("Percentage of Player wins: " + Math.ceil(((double) playerWins / gameRound) * 100) + " %");
        System.out.println();
    }
}