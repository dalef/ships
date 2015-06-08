package ships;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import ships.GameBoard.Block;

class Player
{
    static private Integer id_counter = 0;
    public Integer id;
    public Integer health;
    public Integer ammo;
    public Integer x,y;
    public String name;
    
    private GameActions[] actions;
    
    public void setActionOne(GameActions x){
        actions[0] = x;
    }
    
    public void setActionTwo(GameActions x){
        actions[1] = x;
    }
    
    public void setActionThree(GameActions x){
        actions[2] = x;
    }
    
    public void clearActions(){
        actions[0] = actions[1] = actions[2] = new EmptyAction();
    }
    
    public Player()
    {
        id = id_counter;
        id_counter++;
        x = (SettingsStore.screenHeight / 2) + (10 * id);
        y = (SettingsStore.screenWidth / 2);
        health = 100;
        ammo = 100;
        name = "";
        actions = new GameActions[2];
    }
    
    abstract class GameActions{}
    class MoveAction extends GameActions{}
    class ShootAction extends GameActions{}
    class EmptyAction extends GameActions{}
    
    
}

public class Game
{
    private final GameLogic gl;
    static Scene scene;
    private final Player player[];
    GameBoard gb;
    

    
    public Game()
    {
        player = new Player[7];
        gl = new GameLogic(player);
//        gl.start();
        gb = new GameBoard(7,7);
        planningScreen();
    }
    
        private void planningScreen()
    {
        StackPane bottomPanel = new StackPane();
        HBox bottomHB = new HBox();
        Canvas bottomBackground = new Canvas(SettingsStore.screenWidth, 100);
        GraphicsContext g = bottomBackground.getGraphicsContext2D();
        LinearGradient lg = new LinearGradient(0, 0, 0, 1, true,
               CycleMethod.REFLECT,
               new Stop(0, Color.CORNFLOWERBLUE),
               new Stop(1, Color.BLACK));
        g.setFill(lg);
        g.fillRect(0, 0, bottomBackground.getWidth(), bottomBackground.getHeight());
        
        StackPane rightPanel = new StackPane();
        VBox rightVB = new VBox();
        Canvas rightBackground = new Canvas(SettingsStore.screenWidth/10, SettingsStore.screenHeight - SettingsStore.screenHeight/7);
        g = rightBackground.getGraphicsContext2D();
        lg = new LinearGradient(0, 0, 1, 1, true,
               CycleMethod.REFLECT,
               new Stop(0, Color.CORNFLOWERBLUE),
               new Stop(1, Color.DARKSLATEBLUE));
        g.setFill(lg);
        g.fillRect(0, 0, rightBackground.getWidth(), rightBackground.getHeight());
        
        StackPane leftPanel = new StackPane();
        VBox leftVB = new VBox();
        Canvas leftBackground = new Canvas(SettingsStore.screenWidth/10, SettingsStore.screenHeight - SettingsStore.screenHeight/7);
        g = leftBackground.getGraphicsContext2D();
        lg = new LinearGradient(0, 0, 1, 1, true,
               CycleMethod.REFLECT,
               new Stop(0, Color.CORNFLOWERBLUE),
               new Stop(1, Color.DARKSLATEBLUE));
        g.setFill(lg);
        g.fillRect(0, 0, leftBackground.getWidth(), leftBackground.getHeight());
        
        StackPane gamePanel = new StackPane();
        Canvas gameBackground = new Canvas(SettingsStore.screenWidth - SettingsStore.screenWidth/5, SettingsStore.screenHeight - SettingsStore.screenHeight/7);
        g = gameBackground.getGraphicsContext2D();
        RadialGradient rg = new RadialGradient(0, 0, gameBackground.getWidth() / 2, gameBackground.getHeight() / 2, 100, false,
               CycleMethod.REFLECT,
               new Stop(0, Color.BLACK),
               new Stop(1, Color.DARKBLUE));
        g.setFill(rg);
        g.fillRect(0, 0, gameBackground.getWidth(), gameBackground.getHeight());
        
        Canvas gameForeground = new Canvas(SettingsStore.screenWidth - SettingsStore.screenWidth/5, SettingsStore.screenHeight - SettingsStore.screenHeight/7);
        g = gameForeground.getGraphicsContext2D();
        g.strokeRect(0, 0, gameForeground.getWidth(), gameForeground.getHeight());
       
        
        Button clear = new Button("Clear Moves");
        clear.setPrefHeight(100);
        clear.setPrefWidth(100);
        clear.setOnAction((e)->{
            gl.getCurrentPlayer().clearActions();
        });
        //change action buttons below
        Button move1 = new Button();
        move1.setPrefHeight(100);
        move1.setPrefWidth(100);
        move1.setOnAction((e)->{
            gl.secondCounter--;
//            gl.getCurrentPlayer().setActionOne(null);
        });
        
        Button move2 = new Button();
        move2.setPrefHeight(100);
        move2.setPrefWidth(100);
        move2.setOnAction((e)->{
            gl.getCurrentPlayer().setActionTwo(null);
        });
        
        Button move3 = new Button();
        move3.setPrefHeight(100);
        move3.setPrefWidth(100);
        move3.setOnAction((e)->{
            gl.getCurrentPlayer().setActionThree(null);
        });
        
        Button done = new Button("Done");
        done.setPrefHeight(100);
        done.setPrefWidth(100);
        
        Button back = new Button("Exit");
        back.setPrefHeight(50);
        back.setPrefWidth(80);
        
        Button help = new Button("Help");
        help.setPrefHeight(50);
        help.setPrefWidth(80);
        
        Label playerName = new Label("Player 1");
        playerName.setFont(new Font("Times New Roman", 22));
        playerName.setTextFill(Color.DARKBLUE);

        
        Label healthLbl = new Label("Health");
        healthLbl.setFont(new Font("Times New Roman", 22));
        healthLbl.setTextFill(Color.BLACK);

        
        Rectangle healthBar = new Rectangle(5, 70, 80, 10);
        Rectangle nhealthBar = new Rectangle(5, 70, 90, 10);
        healthBar.setFill(Color.GREEN);
        nhealthBar.setFill(Color.DARKRED);
        
        Label ammoLbl = new Label("Ammo");
        ammoLbl.setFont(new Font("Times New Roman", 22));
        ammoLbl.setTextFill(Color.BLACK);

        Rectangle ammoBar = new Rectangle(5, 110, 60, 10);
        Rectangle nammoBar = new Rectangle(5, 110, 90, 10);
        ammoBar.setFill(Color.RED);
        nammoBar.setFill(Color.BLACK);
        
        StackPane ammoPane = new StackPane();
        ammoPane.getChildren().addAll(nammoBar,ammoBar);
        
        leftVB.getChildren().addAll(playerName, healthLbl, nhealthBar, healthBar, ammoLbl, ammoPane);
        leftPanel.getChildren().addAll(leftBackground,leftVB);

        rightVB.getChildren().addAll(back, help);
        rightPanel.getChildren().addAll(rightBackground,rightVB);

        bottomHB.getChildren().addAll(clear, move1, move2, move3, done);
        bottomPanel.getChildren().addAll(bottomBackground,bottomHB);
        
        gamePanel.getChildren().addAll(gameBackground,gameForeground);

        BorderPane bp = new BorderPane();
        bp.setBottom(bottomPanel);
        bp.setRight(rightPanel);
        bp.setLeft(leftPanel);
        bp.setCenter(gamePanel);
        bp.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        
        scene = new Scene(bp, SettingsStore.screenHeight, SettingsStore.screenWidth);
    }
    
}
