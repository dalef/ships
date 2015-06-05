package ships;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

class Player
{
    static private Integer id_counter = 0;
    public Integer id;
    public Integer health;
    public Integer ammo;
    public Integer x,y;
    public String name;
    
    public Player()
    {
        id = id_counter;
        id_counter++;
        x = (SettingsStore.screenHeight / 2) + (10 * id);
        y = (SettingsStore.screenWidth / 2);
        health = 100;
        ammo = 100;
        name = "";
    }
}

public class Game
{
    static Scene scene;
    private final Player player[];
    
    private void planningScreen(Player p)
    {
        
        Canvas bottomPanel = new Canvas(SettingsStore.screenWidth, 100);
        GraphicsContext g = bottomPanel.getGraphicsContext2D();
        LinearGradient lg = new LinearGradient(0, 0, 0, 1, true,
               CycleMethod.REFLECT,
               new Stop(0, Color.CORNFLOWERBLUE),
               new Stop(1, Color.BLACK));
        g.setFill(lg);
        g.fillRect(0, 0, bottomPanel.getWidth(), bottomPanel.getHeight());
//        bottomPanel.setTranslateX(0);
//        bottomPanel.setTranslateY(470);
        
        Canvas rightPanel = new Canvas(100, SettingsStore.screenHeight - 100);
        g = rightPanel.getGraphicsContext2D();
        lg = new LinearGradient(0, 0, 1, 1, true,
               CycleMethod.REFLECT,
               new Stop(0, Color.CORNFLOWERBLUE),
               new Stop(1, Color.DARKSLATEBLUE));
        g.setFill(lg);
        g.fillRect(0, 0, rightPanel.getWidth(), rightPanel.getHeight());
//        rightPanel.setTranslateX(693);
//        rightPanel.setTranslateY(0);
        
        
        Canvas leftPanel = new Canvas(100, SettingsStore.screenHeight - 100);
        g = leftPanel.getGraphicsContext2D();
        lg = new LinearGradient(0, 0, 1, 1, true,
               CycleMethod.REFLECT,
               new Stop(0, Color.CORNFLOWERBLUE),
               new Stop(1, Color.DARKSLATEBLUE));
        g.setFill(lg);
        g.fillRect(0, 0, leftPanel.getWidth(), leftPanel.getHeight());
//        leftPanel.setTranslateX(0);
//        leftPanel.setTranslateY(0);
        
        Canvas gamePanel = new Canvas(SettingsStore.screenWidth - 207, SettingsStore.screenHeight - 130);
        g = gamePanel.getGraphicsContext2D();
        RadialGradient rg = new RadialGradient(0, 0, gamePanel.getWidth() / 2, gamePanel.getHeight() / 2, 100, false,
               CycleMethod.REFLECT,
               new Stop(0, Color.BLACK),
               new Stop(1, Color.DARKBLUE));
        g.setFill(rg);
        g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
//        gamePanel.setTranslateX(100);
//        gamePanel.setTranslateY(0);
        
        Button clear = new Button("Clear Moves");
        clear.setPrefHeight(100);
        clear.setPrefWidth(100);
        clear.setTranslateX(0);
        clear.setTranslateY(470);
        
        Button move1 = new Button();
        move1.setPrefHeight(100);
        move1.setPrefWidth(100);
        move1.setTranslateX(250);
        move1.setTranslateY(470);
        
        Button move2 = new Button();
        move2.setPrefHeight(100);
        move2.setPrefWidth(100);
        move2.setTranslateX(350);
        move2.setTranslateY(470);
        
        Button move3 = new Button();
        move3.setPrefHeight(100);
        move3.setPrefWidth(100);
        move3.setTranslateX(450);
        move3.setTranslateY(470);
        
        Button done = new Button("Done");
        done.setPrefHeight(100);
        done.setPrefWidth(100);
        done.setTranslateX(693);
        done.setTranslateY(470);
        
        Button back = new Button("Exit");
        back.setPrefHeight(50);
        back.setPrefWidth(80);
        back.setTranslateX(700);
        back.setTranslateY(20);
        
        Button help = new Button("Help");
        help.setPrefHeight(50);
        help.setPrefWidth(80);
        help.setTranslateX(700);
        help.setTranslateY(90);
        
        Label playerName = new Label("Player 1");
        playerName.setFont(new Font("Times New Roman", 22));
        playerName.setTextFill(Color.DARKBLUE);
        playerName.setTranslateX(10);
        playerName.setTranslateY(10);
        
        Label healthLbl = new Label("Health");
        healthLbl.setFont(new Font("Times New Roman", 22));
        healthLbl.setTextFill(Color.BLACK);
        healthLbl.setTranslateX(10);
        healthLbl.setTranslateY(40);
        
        Rectangle healthBar = new Rectangle(5, 70, 80, 10);
        Rectangle nhealthBar = new Rectangle(5, 70, 90, 10);
        healthBar.setFill(Color.GREEN);
        nhealthBar.setFill(Color.DARKRED);
        
        Label ammoLbl = new Label("Ammo");
        ammoLbl.setFont(new Font("Times New Roman", 22));
        ammoLbl.setTextFill(Color.BLACK);
        ammoLbl.setTranslateX(10);
        ammoLbl.setTranslateY(80);
        
        Rectangle ammoBar = new Rectangle(5, 110, 60, 10);
        Rectangle nammoBar = new Rectangle(5, 110, 90, 10);
        ammoBar.setFill(Color.RED);
        nammoBar.setFill(Color.BLACK);
        
        BorderPane bp = new BorderPane();

        bp.setBottom(bottomPanel);
        bp.setRight(rightPanel);
        bp.setLeft(leftPanel);
        bp.setCenter(gamePanel);
        
        
        
        bp.getChildren().add(clear);
        bp.getChildren().add(move1);
        bp.getChildren().add(move2);
        bp.getChildren().add(move3);
        bp.getChildren().add(done);
        bp.getChildren().add(back);
        bp.getChildren().add(help);
        bp.getChildren().add(playerName);
        bp.getChildren().add(healthLbl);
        bp.getChildren().add(nhealthBar);
        bp.getChildren().add(healthBar);
        bp.getChildren().add(ammoLbl);
        bp.getChildren().add(nammoBar);
        bp.getChildren().add(ammoBar);
        
        scene = new Scene(bp, SettingsStore.screenHeight, SettingsStore.screenWidth);
    }
    
    public Game()
    {
        player = new Player[2];
        for(Player p : player)
        {
            planningScreen(p);
        }
    }
}
