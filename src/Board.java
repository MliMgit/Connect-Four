/**
 * Models a 6 by 7 {@link Board} that is going to be used in {@link Game}.
 *
 * @author Marlon May
 * @version 1.0
 */
public class Board {
    private static final int AMOUNT_OF_ROWS = 6;
    private static final int AMOUNT_OF_COLUMNS = 7;
    private static final int FIRST_COLUMN = 1;
    private static final String COLUMN_NUMERATION = "1 2 3 4 5 6 7\n";
    private static final String FINISHING_LINE = "--------------";
    private final CellState[][] board;

    /**
     * Creates a {@link Board} with the dimensions 7 by 6 and fills it's cells with an initial state.
     */
    Board() {
        this.board = new CellState[AMOUNT_OF_ROWS][AMOUNT_OF_COLUMNS];
        populateBoard();
    }

    /**
     * Assigns each Cell of a {@link Board} an empty state.
     */
    public void populateBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = CellState.O;
            }
        }
    }

    /**
     * Checks whether or not a given column is a valid column from 1 to 7.
     *
     * @param column - the given column
     * @return       - whether or not the given column is in a range from 1 to 7
     */
    public static boolean isValidColumn(final int column) {
        return column >= FIRST_COLUMN && column <= AMOUNT_OF_COLUMNS;
    }

    /**
     * Prints a board.
     *
     * @return - a readable String representation of an instance of this class.
     */
    public String printBoard() {
        StringBuilder output = new StringBuilder();
        output.append(COLUMN_NUMERATION);
        int counter = 0;
        //Appends the output by each element in a row, if the end of a row is reached a new line is appended.
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (counter == AMOUNT_OF_COLUMNS) {
                    output.append("\n");
                    counter = 0;
                }
                output.append(this.board[i][j].toString()).append("|");
                counter++;
            }
        }
        return output.append("\n" + FINISHING_LINE).toString();
    }

    /**
     * Returns the board.
     *
     * @return - the board
     */
    public CellState[][] getBoard() {
        return this.board;
    }
}
