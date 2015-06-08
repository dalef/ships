/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

/**
 *
 * @author woodi
 */
public class GameBoard {
    
    public Block[][] board;
    private int xVal, yVal;
    GameBoard(){
        board = new Block[25][25];
        xVal = yVal = 25;
    }
    
    GameBoard(int n, int m){
        if(n <= 4) {xVal = 5;}
        else {xVal = n;}
        if (m <= 4){yVal=5;}
        else{yVal = m;}
        board = new Block[xVal][yVal];
    }


    public int getxVal() {
        return xVal;
    }

    public void setxVal(int xVal) {
        this.xVal = xVal;
    }

    public int getyVal() {
        return yVal;
    }

    public void setyVal(int yVal) {
        this.yVal = yVal;
    }
    
    class Block{
    
    }
    
    
}
