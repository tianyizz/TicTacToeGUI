/**
 * Tianyi Zhang hw4
 * CPSC 2150
 * C17587650
 */

package cpsc2150.hw5;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

/**
 *
 * @Invariants: 0<numRow<=MAX_SIZE
 * @Invariants: 0<numCol<=MAX_SIZE
 * @Invariants: marker=('X', 'O', 'Y', 'Z', 'A', 'K', 'E', 'J', 'N', 'H')
 * correspondence: NUM_ROWS=numRow
 * correspondence: NUM_COLS=numCol
 * correspondence: this=myBoard map<Marker,List<BoardPosition[0...numRow-1][0...numCol-1]>>
 */

public class GameBoardMem implements IGameBoard{
    Map<Character,List<BoardPosition>> myBoard;
    private int countStep;
    private int numRow;
    private int numCol;
    private int winSize;

    /**
     * @param inputRow size of the row
     * @param inputCol size of the column
     * @param inputWinSize size of the win
     *
     * @requires: inputRow<=MAX_SIZE, inputCol<=MAX_SIZE
     * @ensures <pre>
     *   new hash map created for GameBoardMem
     *   win size of GameBoardMem is inputWinSize
     *   row size of GameBoardMem is inputRow
     *   column size of GameBoardMem is inputCol
     * </pre>
     */
    GameBoardMem(int inputRow, int inputCol,int inputWinSize){

        myBoard=new HashMap<>();

        countStep=0;
        numRow=inputRow;
        numCol=inputCol;
        winSize=inputWinSize;
    }

    @Override
    public boolean checkSpace(BoardPosition pos)
    {
        //returns true if the position specified in pos is available,
        //false otherwise

        if(!(pos.getRow()>=0&&pos.getRow()<numRow)){
            return false;
        }

        if(!(pos.getColumn()>=0&&pos.getColumn()<numCol)){
            return false;
        }

        BoardPosition tempP=getPos(pos.getRow(),pos.getColumn());
        if(tempP.getPlayer()!=' '){
            return false;
        }
        else
            return true;
    }


    @Override
    public void placeMarker(BoardPosition lastPos)
    {
//places the character in marker on the position specified by
        //   marker
        if(myBoard.get(lastPos.getPlayer())==null){
            List<BoardPosition>newList=new ArrayList<>();
            myBoard.put(lastPos.getPlayer(),newList);
        }

        myBoard.get(lastPos.getPlayer()).add(lastPos);
        countStep++;
    }

    @Override
    public boolean checkForWinner(BoardPosition lastPos)
    {
        //this function will check to see if the lastPos placed resulted
        //       in a winner. It so it will return true, otherwise false.
        //Passing in the last position will help limit the possible
        //         places to check for a win condition, since you can assume that any win
        //    condition that did not include the most recent play made would have
        //    been caught earlier.

        return (checkDiagonalWin(lastPos)||
                checkHorizontalWin(lastPos)||
                checkVerticalWin(lastPos));
    }

    @Override
    public boolean checkForDraw()
    {
//this function will check to see if the game has resulted in a
        //   tie. A game is tied if there are no free board positions remaining. It
        //   will return true if the game is tied, and false otherwise.

        return (countStep==numRow*numCol);
    }

    /**
     * @param lastPos BoardPosition that contains the location and marker information
     * @return
     *  a boolean value indicates if the lastPos has resulted a win condition horizontally
     *
     * @requires
     *  called after placeMarker
     *  called after every single call to placeMarker
     * @ensures<pre>
     *  checkHorizontalWin=true iff inputWinsize characters are placed in consecutive order
     *                      horizontally (same row as indicated by the lastPos)
     * </pre>
     */
    private boolean checkHorizontalWin(BoardPosition lastPos)
    {
        //checks to see if the last marker placed resulted in 5 in a row
        //horizontally. Returns true if it does, otherwise false
        char c=lastPos.getPlayer();
        List<BoardPosition> tempList=new ArrayList<>(myBoard.get(c));

        if(tempList.size()<winSize){
            return false;
        }

        int count=1;
        int temp_record_pos=lastPos.getColumn();

        while(true){
            temp_record_pos-=1;
            if(temp_record_pos<0){
                break;
            }

            BoardPosition temp_pos=
                    new BoardPosition(lastPos.getRow(),temp_record_pos,lastPos.getPlayer());

            if(tempList.contains(temp_pos)==true){
                count++;
            }
            else{
                break;
            }

            if(count==winSize){
                return true;
            }
        }


        temp_record_pos=lastPos.getColumn();

        while(true){
            temp_record_pos+=1;
            if(!(temp_record_pos<numCol)){
                break;
            }

            BoardPosition temp_pos=
                    new BoardPosition(lastPos.getRow(),temp_record_pos,lastPos.getPlayer());

            if(tempList.contains(temp_pos)){
                count++;
            }
            else{
                break;
            }

            if(count==winSize){
                return true;
            }
        }

        return false;
    }

    /**
     * @param lastPos BoardPosition that contains the location and marker information
     * @return
     *  a boolean value indicates if the lastPos has resulted a win condition vertically
     *
     * @requires
     *  called after placeMarker
     *  called after every single call to placeMarker
     * @ensures<pre>
     *  checkVerticalWin=true iff inputWinSize characters are placed in consecutive order
     *                      vertically (same column as indicated by the lastPos)
     * </pre>
     */
    private boolean checkVerticalWin(BoardPosition lastPos)
    {
        //checks to see if the last marker placed resulted in 5 in a row
        //   vertically. Returns true if it does, otherwise false

        char c=lastPos.getPlayer();
        List<BoardPosition> tempList=new ArrayList<>(myBoard.get(c));

        if(tempList.size()<winSize){
            return false;
        }

        int count=1;
        int temp_record_pos=lastPos.getRow();

        while(true){
            temp_record_pos-=1;
            if(temp_record_pos<0){
                break;
            }

            BoardPosition temp_pos=
                    new BoardPosition(temp_record_pos,lastPos.getColumn(),lastPos.getPlayer());

            if(tempList.contains(temp_pos)==true){
                count++;
            }
            else{
                break;
            }

            if(count==winSize){
                return true;
            }
        }


        temp_record_pos=lastPos.getRow();

        while(true){
            temp_record_pos+=1;
            if(!(temp_record_pos<numRow)){
                break;
            }

            BoardPosition temp_pos=
                    new BoardPosition(temp_record_pos,lastPos.getColumn(),lastPos.getPlayer());

            if(tempList.contains(temp_pos)){
                count++;
            }
            else{
                break;
            }

            if(count==winSize){
                return true;
            }
        }

        return false;
    }

    /**
     * @param lastPos BoardPosition that contains the location and marker information
     * @return
     *  a boolean value indicates if the lastPos has resulted a win condition diagonally
     *
     * @requires
     *  called after placeMarker
     *  called after every single call to placeMarker
     * @ensures <pre>
     *  checkDiagonalWin=true iff inputWinSize characters are placed in consecutive order
     *                      diagonally (the diagonal contains the location indicated by lastPos)
     *  </pre>
     */
    private boolean checkDiagonalWin(BoardPosition lastPos)
    {
        //Checks to see if the last marker placed resulted in 5 in a row
        //diagonally. Returns true if it does, otherwise false
        //Note: there are two diagonals to check


        //need to check two diagonals according to the lastPos
        //both diagonals are checked starting from the leftmost point

        //reset the col and row to starting position 1 (first diagonal)
        char c=lastPos.getPlayer();
        List<BoardPosition> tempList=new ArrayList<>(myBoard.get(c));

        if(tempList.size()<winSize){
            return false;
        }

        int count=1;
        int temp_record_pos_col=lastPos.getColumn();
        int temp_record_pos_row=lastPos.getRow();

        while(true){
            temp_record_pos_col-=1;
            temp_record_pos_row-=1;
            if(temp_record_pos_col<0||temp_record_pos_row<0){
                break;
            }

            BoardPosition temp_pos=
                    new BoardPosition(temp_record_pos_row,temp_record_pos_col,lastPos.getPlayer());

            if(tempList.contains(temp_pos)==true){
                count++;
            }
            else{
                break;
            }

            if(count==winSize){
                return true;
            }
        }


        temp_record_pos_col=lastPos.getColumn();
        temp_record_pos_row=lastPos.getRow();

        while(true){
            temp_record_pos_col+=1;
            temp_record_pos_row+=1;
            if(!(temp_record_pos_row<numCol)||!(temp_record_pos_col<numCol)){
                break;
            }

            BoardPosition temp_pos=
                    new BoardPosition(temp_record_pos_row,temp_record_pos_col,lastPos.getPlayer());

            if(tempList.contains(temp_pos)){
                count++;
            }
            else{
                break;
            }

            if(count==winSize){
                return true;
            }
        }


        //reset the col and row to starting position 2 (second diagonal)
        //the position is different from the first one

        count=1;
        temp_record_pos_col=lastPos.getColumn();
        temp_record_pos_row=lastPos.getRow();

        while(true){
            temp_record_pos_col+=1;
            temp_record_pos_row-=1;

            if(!(temp_record_pos_col<numCol)||temp_record_pos_row<0){
                break;
            }

            BoardPosition temp_pos=
                    new BoardPosition(temp_record_pos_row,temp_record_pos_col,lastPos.getPlayer());

            if(tempList.contains(temp_pos)==true){
                count++;
            }
            else{
                break;
            }

            if(count==winSize){
                return true;
            }
        }


        temp_record_pos_col=lastPos.getColumn();
        temp_record_pos_row=lastPos.getRow();

        while(true){
            temp_record_pos_col-=1;
            temp_record_pos_row+=1;
            if((temp_record_pos_col<0)||!(temp_record_pos_row<numCol)){
                break;
            }

            BoardPosition temp_pos=
                    new BoardPosition(temp_record_pos_row,temp_record_pos_col,lastPos.getPlayer());

            if(tempList.contains(temp_pos)){
                count++;
            }
            else{
                break;
            }

            if(count==winSize){
                return true;
            }
        }

        return false;
    }

    /**
     * @param row target row
     * @param col target col
     * @return a BoardPosition object of the target location including the mark (either empty or with player marker)
     *
     * @requires:
     *     row<inputRow
     *     col<inputCol
     *
     * @ensures<pre>:
     *     getPos()=BoardPosition p with the player mark or empty <space> of the target location
     *     </pre>
     *
     */

    private BoardPosition getPos(int row, int col){

        for(Map.Entry<Character,List<BoardPosition>> entry:myBoard.entrySet()){
            if(entry.getKey()==' '){
                break;
            }

            List<BoardPosition> tempList=new ArrayList<>(entry.getValue());

            for(int i=0;i<tempList.size();i++){
                if(tempList.get(i).getColumn()==col&&
                        tempList.get(i).getRow()==row){

                    return tempList.get(i);
                }
            }
        }

        return new BoardPosition(row,col,' ');
    }

    /**
     * @param object input object that is expected to be a GameBoardMem Object
     * @return return value, a boolean indicates if two objects are same
     *
     * @requires: called by a GameBoardMem Object
     * @ensures:<pre>
     *     equals()=if the current object is same as the input according to contents
     * </pre>
     *
     */

    @Override
    public boolean equals(Object object){
        if(object==this){
            return true;
        }

        if(!(object instanceof GameBoardMem)) {
            return false;
        }

        String cur=this.toString();

        if(cur.equals(object.toString())==false){
            return false;
        }

        return true;
    }

    /**
     * @return formatted string that will print the current board
     *
     * @requires
     *  called by a GameBoard object
     *
     * @ensures <pre>
     *     a string that contains the formatted board status will be returned
     * </pre>
     */
    @Override
    public String toString()
    {
        String str="   ";

        for(int i=0;i<numCol;i++){
            if(i<10) {
                str += " ";
            }
            str+=Integer.toString(i);
            str+="|";
        }
        str+="\n";

        for(int i=0;i<numRow;i++){
            str+=Integer.toString(i);
            if(i<10){
                str+=" ";
            }
            str+="|";

            for(int u=0;u<numCol;u++){
                str+=getPos(i,u).getPlayer();
                str += " |";

            }

            str+="\n";
        }

        return str;
    }

}
