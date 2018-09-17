package cpsc2150.hw5;

/**
 * This class exists to declare our model, view, and controller objects, connect them, and start the game
 *
 * You do not need to make changes to this code file
 */
public final class TicTacToeGame {

    /**
     * This is the entry point for our tic tac toe game
     * @param args ignored in this program
     */
    public static void main(String[] args)
    {

        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}
