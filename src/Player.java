/**
 * Models a {@link Player} that is going to be used in {@link Game}.
 *
 * @author Mlim
 * @version 1.0
 */
public class Player {
    private static final int MAX_AMOUNT_OF_GAME_TOKENS = 21;
    private static int createdPlayers = 0;
    private final CellState colour;
    private final String name;
    private int gameTokens;

    /**
     * Creates a {@link Player} out of a given name.
     *
     * @param name - the given name
     */
    public Player(final String name) {
        createdPlayers++;
        this.gameTokens = MAX_AMOUNT_OF_GAME_TOKENS;
        this.name = name;
        this.colour = createdPlayers == 1 ? CellState.R : CellState.Y;
    }

    /**
     * Reduces the amount of a players gameTokens by 1.
     */
    public void reduceGameTokens() {
        this.gameTokens -= 1;
    }

    /**
     * Returns a players amount of gameTokens.
     *
     * @return - the players amount of gameTokens
     */
    public int getGameTokens() {
        return this.gameTokens;
    }

    /**
     * Returns a players colour.
     *
     * @return - the players colour
     */
    public CellState getColour() {
        return this.colour;
    }

    /**
     * Returns a players name.
     *
     * @return - the players name
     */
    public String getName() {
        return this.name;
    }
}
