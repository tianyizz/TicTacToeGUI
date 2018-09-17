/**
 * Tianyi Zhang hw4
 * CPSC 2150
 * C17587650
 */

package cpsc2150.hw5;


    /**
     * IGameBoard represents a 2-dimensional gameboard that has characters
     * on it as markers ('X', 'O', 'Y', 'Z', 'A', 'K', 'E', 'J', 'N', 'H'). No space on the board can have multiple
     * players, and there can be a clear winner. Board is NUM_ROWS x NUM_COLS in size
     **Initialization ensures: the Board does not have any markers on it
     * Defines: NUM_ROWS: Z* Defines: NUM_COLS: Z
     * Constraints: 0< NUM_ROWS <= MAX_SIZE
     *0< NUM_COLS <= MAX_SIZE
     */
    public interface IGameBoard {
        int MAX_SIZE = 100;
        /**
         * @param pos the inputted board position by the user
         * @return false if the space is out of bounds or used. True otherwise.
         * @requires [pos to be a fully initialized BoardPosition Object.]
         * @ensures <pre>
         *     [the return value will be false if the space is unusable and true otherwise.]
         * </pre>
         */
        boolean checkSpace(BoardPosition pos);
        /**
         * @param lastPos the place a player wants to place their token.
         * @requires [marker be a fully initialized BoardPosition Object.]
         * @ensures <pre>
         *     [the user's token will be placed at the spot they requested.]
         * </pre>
         */
        void placeMarker(BoardPosition lastPos);
        /**
         * @param lastPos the last token a player placed
         * @return true if there is a winner
         * @requires [lastPos be a fully initialized BoardPosition Object.]
         * @ensures <pre>
         *     Will return true if there is a winner and false otherwise.
         * </pre>
         */
        boolean checkForWinner(BoardPosition lastPos);

        /**
         *
         * @return a value indicates if the current game has ended with a draw
         * @requires called by an IGameBoard type object
         *
         * @ensures <pre>
         *     will return true if the game had ended as a draw
         * </pre>
         */
        boolean checkForDraw();

    }


