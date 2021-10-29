import java.util.Scanner;

/**
 * Executes the program and contains its entryPoint.
 *
 * @author Mlim
 * @version 1.0
 */
public final class Main {
    /**
     * A message requesting input of a column.
     */
    public static final String GET_COLUMN = "Please enter the number of the column you want to place your token in.";
    /**
     * A message requesting another input if the specified column is out of bounds.
     */
    public static final String INVALID_COLUMN = "The specified column is out of bounds. Please enter another column.";

    /**
     * A message requesting another input when the given column is already full.
     */
    public static final String FULL_COLUMN = "The specified column is already full. Please enter another number.";

    /**
     * The constructor is private since this is an utility class.
     */
    private Main() {
    }

    /**
     * Entrypoint of the program. Uses the given command line arguments to execute the program.
     *
     * @param args - the given command line arguments
     */
    public static void main(String [] args) {
        int turnsPlayed = 1;
        boolean tokenPlaced = false;
        boolean winnerDetermined = false;
        Player currentPlayer;
        final Scanner reader = new Scanner(System.in);
        System.out.println("Please enter the name of the first player.");
        String playerOne = reader.next();
        System.out.println("Please enter the name of the second player.");
        String playerTwo = reader.next();
        Game game = new Game(playerOne, playerTwo);
        currentPlayer = game.determineStartingPlayer();
        System.out.println(currentPlayer.getName() + " starts. Your colour is " + currentPlayer.getColour() + ".");

        //We execute as long as no winner is determined
        while (!winnerDetermined) {
            System.out.println(game);
            System.out.println(currentPlayer.getName() + "'s turn. " + GET_COLUMN);
            System.out.println("Please choose from 1 to 7.");
            //Its the current players turn as long as no token was placed
            while (!tokenPlaced) {
                int column = reader.nextInt();
                int lastPlacedRow;
                //Checks if a valid column is entered
                if (Board.isValidColumn(column)) {
                    //If the column is already full we continue
                    if (!game.hasFreeSlot(column)) {
                        System.out.println(FULL_COLUMN);
                        continue;
                    }
                    //We save the row in which the last token was placed and reduce the players amount of gameTokens
                    lastPlacedRow = game.getFreeSlot(column);
                    game.placeGameToken(column, currentPlayer);
                    currentPlayer.reduceGameTokens();
                    //We set tokenPlaced to true so that the turn ends
                    tokenPlaced = true;
                    //If a player has won the game we exit the while loop and a message is printed
                    if (!game.checkWinner(lastPlacedRow, column, currentPlayer).equals("")) {
                        System.out.println(game);
                        System.out.println(game.checkWinner(lastPlacedRow, column, currentPlayer));
                        winnerDetermined = true;
                        break;
                    }
                    //If no valid column is entered a message gets printed
                } else {
                    System.out.println(INVALID_COLUMN);
                }
            }
            //We reset tokenPlaced, switch the current player and increment the turnsPlayed
            tokenPlaced = false;
            currentPlayer = game.switchCurrentPlayer(currentPlayer);
            turnsPlayed++;
        }
    }
}
