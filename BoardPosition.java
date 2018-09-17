/**
 * Tianyi Zhang hw4
 * CPSC 2150
 * C17587650
 */

package cpsc2150.hw5;

/**
 * @invariant:
 *      rowPos,colPos>=0
 *      and they are integers
 */

public class BoardPosition {

    private int rowPos;
    private int colPos;
    private char player;

    /**
     * @param inputRow row position of the player's move
     * @param inputCol  column position of the player's move
     * @param inputP marker of the specific player
     * @requires
     *  inputRow,inputCol=[0,7]
     *
     * @ensures<pre>
     *  player=inputP
     *  rowPos=inputRow
     *  colPos=inputCol
     * </pre>
     */

    BoardPosition(int inputRow, int inputCol, char inputP){
        rowPos=inputRow;
        colPos=inputCol;
        player=inputP;
    }

    /**
     * @return row value of the marker
     * @requires
     *  needs to be called by a BoardPosition object
     * @ensures
     *  return integer values of row
     *  getRow=rowPos
     */
    public int getRow(){
        return rowPos;
    }

    /**
     * @return column value of the marker
     * @requires
     *  needs to be called by a BoardPosition object
     * @ensures
     *  return integer values of column
     *  getColumn=colPos
     */
    public int getColumn(){
        return colPos;
    }

    /**
     * @return character of the marker
     * @requires
     *  needs to be called by a BoardPosition object
     * @ensures
     *  return the character symbol of the move
     *  getPlayer=player
     */
    public char getPlayer(){
        return player;
    }

    /**
     * @param B the object that is being compared to
     * @return a boolean value indicating if the two objects are equal
     * @requires
     *  called by a BoardPosition object
     *  input a BoardPosition object
     * @ensures
     *  return a boolean value
     *  equals=false iff caller(row, col)!=B(row, col)
     *  equals=true iff caller(row, col)==B(row,col)
     *
     */

    @Override
    public boolean equals(Object B)
    {
        if(B==this){
            return true;
        }

        if(!(B instanceof BoardPosition)){
            return false;
        }

        BoardPosition b=(BoardPosition) B;

        if (this.rowPos==b.getRow()&&this.colPos==b.getColumn()
                &&this.player==b.getPlayer()){
            return true;
        }

        return false;
    }

    /**
     * @return a string that is printing the information stored in
     *          current object
     * @requires
     *  called by a BoardPosition object
     * @ensures<pre>
     *  returning a formatted string contains the desired information
     *  str=Player <mark> at position <row>, <col>
     * </pre>
     */

    @Override
    public String toString()
    {
        String str = String.format("Player %c at position %d,%d",this.player,this.rowPos
                ,this.colPos);
        return str;
    }
}
