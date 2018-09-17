package cpsc2150.hw5;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 *
 * This is where you will write code
 *
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and one of the IGameBoard implementations from Homework 4
 * You can choose which IGameBoard implementation to use
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class besides the package name
 *
 * The code you will write will need to be in the ProcessButtonClick method.
 * This method is called by the screen whenever a player clicks on a button to try to place a marker
 */
public class TicTacToeController{
    //our current game that is being played
    private IGameBoard curGame;
    //to track who's turn it is

    //The screen that provides our view
    private TicTacToeView screen;



    public static final int MAX_PLAYERS = 10;
    //the player characters. PLayer 1 is always x, player 2 is o, etc
    private char[] players = {'X', 'O', 'Y', 'Z', 'A', 'K', 'E', 'J', 'N', 'H'};
    //use this to keep track of who is currently trying to place a marker
    private int curPlayerIdx;
    //it may not always be 10 players, so numPlayers will keep track of how many are playing
    private int numPlayers;
    //it helps to know when a game has ended.
    private boolean gameOver;


    /**
     * Our constructor makes sure our controller knows about the model and the view
     * @param model the board implementation
     * @param view the screen that is shown
     * @param np the number of players
     * @ensures the controller will respond to actions on the view using the model.
     */
    TicTacToeController(IGameBoard model, TicTacToeView view, int np){
        this.curGame = model;
        this.screen = view;
        numPlayers = np;
        curPlayerIdx = 0;
        gameOver = false;
    }

    /**
     * This method is called whenever a user clicks one of the buttons on our tic tac toe screen. Just because a user
     * clicked on a button does not mean that button does not currently have a token on it. Use your model (IGameBoard)
     * to handle all the logic of what happens to the game when user clicks a button.
     *
     * If a win is detected, tell the user who won, and to click any button to start a new game. Same for a tie game
     *
     * Use the gameOver data field to track if the game ended.
     * @param row the row of the activated button
     * @param col the column of the activated button
     * @ensures The button pressed will show the right token and check if a player has won or there is a tie game
     *          or the method will have the screen display an error message
     */
    public void processButtonClick(int row, int col) {

        if(gameOver)
        {
            //After a game ends, gameOver should be set to true so the next button click results in a new game beginning
            //newGame creates the new game for us
            gameOver=false;
            newGame();
        }
        else {
            //what happens if the user clicked a button and the game is not over?
            int tempPlayer=this.curPlayerIdx%this.numPlayers;

            //reset the gameOver flag
            gameOver=false;
            String str="It is "+players[(this.curPlayerIdx+1)%this.numPlayers]+"\'s turn. ";
            screen.setMessage(str);

            BoardPosition newPos = new BoardPosition(row, col, players[tempPlayer]);

            if (!curGame.checkSpace(newPos)) {
                screen.setMessage("The spot is not available");
            }
            else{
                screen.setMarker(row,col,players[tempPlayer]);
                curGame.placeMarker(newPos);
                if(curGame.checkForWinner(newPos)) {
                    screen.setMessage(players[tempPlayer] + " has won the game! Press any button to reset");
                    gameOver = true;
                }
                else if(curGame.checkForDraw()){
                    screen.setMessage("Game ends in a tie! Press any button to reset");
                    gameOver = false;
                }
                curPlayerIdx++;
            }
        }
    }

    /**
     * This function will close the current game and start a new one by reopening the set up screen
     * No changes should be made to this method
     */
    private void newGame()
    {
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}
