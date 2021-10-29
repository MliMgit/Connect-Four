/**
 * Models a {@link Game} out of {@link Player} and {@link Board}.
 *
 * @author Mlim
 * @version 1.0
 */
public class Game {
    private static final int TOKENS_NEEDED_TO_WIN = 4;
    private final Player playerOne;
    private final Player playerTwo;
    private final Board gameBoard;

    /**
     * Creates a {@link Game} out of two {@link Player} and a {@link Board}.
     *
     * @param playerOneName - the first players name
     * @param playerTwoName - the second players name
     */
    public Game(String playerOneName, String playerTwoName) {
        this.playerOne = new Player(playerOneName);
        this.playerTwo = new Player(playerTwoName);
        this.gameBoard = new Board();
    }

    /**
     * Determines the starting player by checking a random number is higher or lower than 0.5.
     *
     * @return - the starting player
     */
    public Player determineStartingPlayer() {
        final Player startingPlayer;
        final double number = (Math.random());
        startingPlayer = number < 0.5 ? playerOne : playerTwo;
        return startingPlayer;
    }

    /**
     * Places a game token in the first empty slot of a given column.
     *
     * @param column         - the given column
     * @param currentPlayer  - the current player
     */
    public void placeGameToken(final int column, final Player currentPlayer) {
        final CellState[][] board = this.gameBoard.getBoard();
        final int row = getFreeSlot(column);
        board[row][column - 1] = currentPlayer.getColour();
    }

    /**
     * Checks whether or not a given column has an empty slot.
     *
     * @param column - the given column
     * @return       - whether or not the given column has an empty slot
     */
    public boolean hasFreeSlot(final int column) {
        final CellState[][] board = this.gameBoard.getBoard();
        return board[0][column - 1] == CellState.O;
    }

    /**
     * Returns the first empty slot of a given column.
     *
     * @param column - the given column
     * @return       - the first empty slot of the given column
     */
    public int getFreeSlot(final int column) {
        final CellState[][] board = this.gameBoard.getBoard();
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][column - 1] == CellState.O) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Switches the given currentPlayer.
     *
     * @param currentPlayer - the given currentPlayer
     * @return              - playerTwo if playerOne is currently playing, playerOne if playerTwo is currently playing
     */
    public Player switchCurrentPlayer(final Player currentPlayer) {
        if (currentPlayer.getName().equals(this.playerOne.getName())) {
            return this.playerTwo;
        }
        return this.playerOne;
    }

    /**
     * Checks if the given currentPlayer has won the game by using the given row and the given column.
     *
     * @param row           - the given row
     * @param column        - the given column
     * @param currentPlayer - the given currentPlayer
     * @return              - whether or not the currentPlayer has won horizontally, vertically, diagonally or
     *                        whether or not the game ended in a draw.
     */
    public String checkWinner(final int row, final int column, final Player currentPlayer) {
        if (checkHorizontal(row, currentPlayer)) {
            return currentPlayer.getName() + " won horizontally.";
        }
        if (checkVertical(column, currentPlayer)) {
            return currentPlayer.getName() + " won vertically.";
        }
        if (checkDiagonal()) {
            return currentPlayer.getName() + " won diagonally.";
        }
        if (checkDraw()) {
            return "Its a Draw!";
        }
        return "";
    }


    /**
     * HelpMethod that checks whether or not the currentPlayer won by having four successiveTokens in a given row.
     *
     * @param row           - the given row
     * @param currentPlayer - the current player
     * @return              - whether or not the current player has four successiveTokens in the given row.
     */
    private boolean checkHorizontal(final int row, final Player currentPlayer) {
        final CellState[][] board = this.gameBoard.getBoard();
        int successiveTokens = 0;
        //We iterate over the given row/column
        for (int i = 0; i < board[0].length; i++) {
            //If the colour of a cell equals the current players colour we increase the amount of successiveTokens
            if (board[row][i] == currentPlayer.getColour()) {
                successiveTokens++;
                //If the player has enough successiveTokens to win we exit the loop
                if (successiveTokens == TOKENS_NEEDED_TO_WIN) {
                    break;
                }
                //If the player doesn't have enough successive tokens we set their amount to 0
            } else {
                successiveTokens = 0;
            }
        }
        return successiveTokens >= TOKENS_NEEDED_TO_WIN;
    }

    /**
     * HelpMethod that checks whether or not the currentPlayer won by having four successiveTokens in a given column.
     *
     * Does so by counting the successiveTokens of the currentPlayer the exact same way as the checkHorizontal method.
     *
     * @param column        - the given column
     * @param currentPlayer - the current player
     * @return              - whether or not the current player has four successiveTokens in the given column.
     */
    private boolean checkVertical(int column, Player currentPlayer) {
        final CellState[][] board = this.gameBoard.getBoard();
        int successiveTokens = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][column - 1] == currentPlayer.getColour()) {
                successiveTokens++;
                if (successiveTokens == TOKENS_NEEDED_TO_WIN) {
                    break;
                }
            } else {
                successiveTokens = 0;
            }
        }
        return successiveTokens >= TOKENS_NEEDED_TO_WIN;
    }


    /**
     * HelpMethod that checks whether or not a player has won by having four successiveTokens exist on the gameBoard.
     *
     * @return - whether or not a player has won
     */
    private boolean checkDiagonal() {
        final CellState[][] board = this.gameBoard.getBoard();
        int rows = board.length;
        int columns = board[0].length;
        //We iterate over the gameBoard
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                CellState currentColour = board[i][j];
                //If a cell is empty we skip it
                if (board[i][j] == CellState.O) {
                    continue;
                }
                //If we would be out of bounds while checking in the next two steps we skip the current iteration
                if (i + 1 >= rows
                        || i + 2 >= rows
                        || i + 3 >= rows
                        || j + 1 >= columns
                        || j + 2 >= columns
                        || j + 3 >= columns
                        || j - 1 < 1) {
                    continue;
                }
                //If the current cell as well as the next three diagonal ascending cells are equal the player has won
                if (j + 3 < columns
                        && currentColour == board[i + 1][j + 1]
                        && currentColour == board[i + 2][j + 2]
                        && currentColour == board[i + 3][j + 3]) {
                    return true;
                }
                //If the current cell as well as the next three diagonal descending cells are equal the player has won
                if (j - 3 >= 0
                        && currentColour == board[i + 1][j - 1]
                        && currentColour == board[i + 2][j - 2]
                        && currentColour == board[i + 3][j - 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * HelpMethod that checks whether or not a game ended in a draw.
     *
     * @return - whether or not a game ended in a draw
     */
    private boolean checkDraw() {
        return this.playerOne.getGameTokens() == 0 && this.playerTwo.getGameTokens() == 0;
    }

    /**
     * Overrides the toString method.
     *
     * @return - a readable String representation of an instance of this class.
     */
    @Override
    public String toString() {
        return this.gameBoard.printBoard();
    }
}
