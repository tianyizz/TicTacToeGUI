package cpsc2150.hw5;

/**
 * Created by tianyiz on 4/5/2018.
 *
 * This is the controller for Game Setup Screen It validates input
 **/
public class GameSetupController {
    private GameSetupScreen view;
    private int max_size = 20;

    /**
     *
     * @param v the view to use with this controller. This controller does not require a model
     */
    public GameSetupController(GameSetupScreen v)
    {
        view = v;
    }

    /**
     * this method will only be called by the view when someone clicks the submit button after inputting data to the
     * input boxes
     * @param rows the number in the rows input box
     * @param cols the number in the cols input box
     * @param players the number in the players input box
     * @param numWin the number in the number to win input box
     * @requires all inputs have been validated to ensure they are integers
     * @ensures either a new tic tac toes game will be created or an error message will be displayed about
     * unacceptable game parameters
     */
    public void processButtonClick(int rows, int cols, int players, int numWin )
    {
        String errorMsg = "";
        if(rows < 1 || rows > max_size)
        {
            errorMsg += "Rows must be between 1 and " + max_size;
        }

        if(cols < 1 || cols > IGameBoard.MAX_SIZE)
        {
            errorMsg += "Columns must be between 1 and " + max_size;
        }

        if (numWin > rows)
        {
            errorMsg += "Can't have more to win than the number of rows";
        }
        if (numWin > rows)
         {
            errorMsg += "Can't have more to win than the number of Columns";
         }

        if(numWin < 2)
        {
            errorMsg += "Number to win must be at least 2";
        }

        if(!errorMsg.equals(""))
        {
            view.displayError(errorMsg);

        }
        else
        {
            view.closeScreen();

            IGameBoard model = new GameBoardMem(rows, cols, numWin);
            TicTacToeView tview = new TicTacToeView(rows, cols);
            TicTacToeController tcontroller = new TicTacToeController(model, tview, players);

            tview.registerObserver(tcontroller);
        }
    }
}
