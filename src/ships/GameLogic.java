/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 * @author woodi
 */
public class GameLogic extends Thread{
    
    Timeline t;
    int secondCounter;
    int turnLimit;
    boolean gameOver;
    Player[] players;
    Player currentPlayer;
    boolean  endOfPlayerTurn;
    
    
    GameLogic(Player[] player){
        endOfPlayerTurn = false;
        turnLimit = 25;
        players = player;
        gameOver = false;
        secondCounter = 60;
        t = new Timeline(new KeyFrame(Duration.ZERO),
                 new KeyFrame(Duration.seconds(1),  (e)->{decrementSecondCounter();}));
        t.setCycleCount(Timeline.INDEFINITE); 
    }
    
    @Override
    public void run(){
         t.play();

        
        try {
            beginGameCycle();
        } catch (InterruptedException ex) {
            Logger.getLogger(GameLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Player getCurrentPlayer(){
    
        return currentPlayer;
    }
    
    private void decrementSecondCounter() {

        secondCounter--;
        if(secondCounter < 0){
            endOfPlayerTurn = true;
            System.out.println("endOfPlayerTurn is " + endOfPlayerTurn);
        }
        System.out.println(secondCounter);
        System.out.println(this.toString());
    }
    
    private void playerTurn(Player p) throws InterruptedException{
        currentPlayer = p;
        secondCounter = 60;
        endOfPlayerTurn = false;
        System.out.println(this.toString());
//        System.out.println("Exited Player Turn");
    }
    
    
    public void beginGameCycle() throws InterruptedException{
        for(int i = turnLimit; i > 0; i--){
            for(Player p : players){
//                System.out.println("Herpa derpa.");
            
                playerTurn(p);
                
            }
            resolutionPhase(players);
        }
    }

    private void resolutionPhase(Player[] players) {

    System.out.println("endOfPlayerTurn is " + endOfPlayerTurn);
    }
}
