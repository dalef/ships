package javaproject;

import java.io.IOException;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.image.*;

class SettingsStore
{
    static int screenWidth = 800;
    static int screenHeight = 600;
    static int graphicsQuality = 3;
    static int volume = 2;
    static boolean fullscreen = false;
}

class Screens
{
    static MainMenu mainMenu = new MainMenu();
    static SettingsMenu settingsMenu = new SettingsMenu();
    static HighscoresMenu highscoresMenu = new HighscoresMenu();
    static GraphicsMenu graphicsMenu = new GraphicsMenu();
    static SoundMenu soundMenu = new SoundMenu();
}

class Menu
{
    public Scene scene;
    
    public void setBackground(Pane p, String image)
    {
        BackgroundImage myBI= new BackgroundImage(new Image(image,SettingsStore.screenWidth,SettingsStore.screenHeight,false,false),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        p.setBackground(new Background(myBI));
    }
    
    public void setScene(Menu m)
    {
        JavaProject.gameStage.setScene(m.scene);
    }
}

class SettingsMenu extends Menu
{
    public SettingsMenu()
    {
        VBox menu = new VBox();
        menu.setSpacing(50);
        
        setBackground(menu, "images/full2.jpg");
        
        Button graphicsBtn = new Button("GRAPHICS");
        Button audioBtn = new Button("AUDIO");
        Button backBtn = new Button("BACK");
        
        graphicsBtn.setMaxWidth(100);
        graphicsBtn.setMaxHeight(50);
        
        audioBtn.setMaxWidth(100);
        audioBtn.setMaxHeight(50);
        
        backBtn.setMaxWidth(100);
        backBtn.setMaxHeight(50);
        
        backBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                setScene(Screens.mainMenu);
            }
        });
        
        audioBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                setScene(Screens.soundMenu);
            }
        });
        
        graphicsBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                setScene(Screens.graphicsMenu);
            }
        });
        
        menu.setPadding(new Insets(100, 0, 0, 350));
        menu.getChildren().add(graphicsBtn);
        menu.getChildren().add(audioBtn);
        menu.getChildren().add(backBtn);
        
        scene = new Scene(menu, SettingsStore.screenHeight, SettingsStore.screenWidth);
    }
}

class GraphicsMenu extends Menu
{
    public GraphicsMenu()
    {
        VBox menu = new VBox();
        menu.setSpacing(50);
        
        setBackground(menu, "images/full2.jpg");
        
        Button backBtn = new Button("Back");
        Button applyBtn = new Button("Apply");
        
        ToggleGroup group = new ToggleGroup();
        RadioButton hiGraphics = new RadioButton("High Graphics");
        RadioButton medGraphics = new RadioButton("Medium Graphics");
        RadioButton lowGraphics = new RadioButton("Low Graphics");
        
        hiGraphics.setSelected(true);
        
        hiGraphics.setToggleGroup(group);
        medGraphics.setToggleGroup(group);
        lowGraphics.setToggleGroup(group);
        
        hiGraphics.setMaxWidth(200);
        hiGraphics.setMaxHeight(50);
        
        medGraphics.setMaxWidth(200);
        medGraphics.setMaxHeight(50);
        
        lowGraphics.setMaxWidth(200);
        lowGraphics.setMaxHeight(50);
        
        backBtn.setPrefWidth(80);
        backBtn.setMaxWidth(80);
        backBtn.setPrefHeight(20);
        backBtn.setMaxHeight(20);
        
        applyBtn.setPrefWidth(80);
        applyBtn.setMaxWidth(80);
        applyBtn.setPrefHeight(20);
        applyBtn.setMaxHeight(20);
        
        menu.setPadding(new Insets(100, 0, 0, 350));
        menu.getChildren().addAll(hiGraphics, medGraphics, lowGraphics);
        menu.getChildren().add(backBtn);
        
        applyBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                JavaProject.gameStage.setWidth(SettingsStore.screenWidth);
                JavaProject.gameStage.setHeight(SettingsStore.screenHeight);
            }
        });
        
        backBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                setScene(Screens.settingsMenu);
            }
        });
        
        hiGraphics.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                SettingsStore.graphicsQuality = 3;
            }
        });
        
        medGraphics.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                SettingsStore.graphicsQuality = 2;
            }
        });
        
        lowGraphics.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                SettingsStore.graphicsQuality = 1;
            }
        });
        
        scene = new Scene(menu, SettingsStore.screenHeight, SettingsStore.screenWidth);
    }
}

class SoundMenu extends Menu
{
    public SoundMenu()
    {
        VBox menu = new VBox();
        menu.setSpacing(50);
        
        setBackground(menu, "images/full2.jpg");
        
        Button backBtn = new Button("Back");
        
        ToggleGroup group = new ToggleGroup();
        RadioButton hiVolume = new RadioButton("High Volume");
        RadioButton medVolume = new RadioButton("Medium Volume");
        RadioButton lowVolume = new RadioButton("Low Volume");
        
        medVolume.setSelected(true);
        
        hiVolume.setToggleGroup(group);
        medVolume.setToggleGroup(group);
        lowVolume.setToggleGroup(group);
        
        hiVolume.setMaxWidth(200);
        hiVolume.setMaxHeight(50);
        
        medVolume.setMaxWidth(200);
        medVolume.setMaxHeight(50);
        
        lowVolume.setMaxWidth(200);
        lowVolume.setMaxHeight(50);
        
        backBtn.setPrefWidth(80);
        backBtn.setMaxWidth(80);
        backBtn.setPrefHeight(20);
        backBtn.setMaxHeight(20);
        
        menu.setPadding(new Insets(100, 0, 0, 350));
        menu.getChildren().addAll(hiVolume, medVolume, lowVolume);
        menu.getChildren().add(backBtn);
        
        backBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                setScene(Screens.settingsMenu);
            }
        });
        
        hiVolume.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                SettingsStore.volume = 3;
            }
        });
        
        medVolume.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                SettingsStore.volume = 2;
            }
        });
        
        lowVolume.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                SettingsStore.volume = 1;
            }
        });
        
        scene = new Scene(menu, SettingsStore.screenHeight, SettingsStore.screenWidth);
    }
}

class HighscoresMenu extends Menu
{
    public HighscoresMenu()
    {
        VBox menu = new VBox();
        menu.setSpacing(50);
        
        setBackground(menu, "images/full2.jpg");
        
        Button backBtn = new Button();
        Label scores = new Label("Highscores go here!!!");
        scores.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 22));
        scores.setTranslateX(-60);
        scores.setTranslateY(-45);
        
        backBtn.setText("BACK");
        
        backBtn.setMaxWidth(100);
        backBtn.setMaxHeight(50);
        
        backBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                setScene(Screens.mainMenu);
            }
        });
        
        menu.setPadding(new Insets(100, 0, 0, 350));
        menu.getChildren().add(backBtn);
        menu.getChildren().add(scores);
        
        scene = new Scene(menu, SettingsStore.screenHeight, SettingsStore.screenWidth);
    }
}

class MainMenu extends Menu
{
    public MainMenu()
    {
        VBox menu = new VBox();
        menu.setSpacing(50);
        
        setBackground(menu, "images/full2.jpg");

        Button playBtn = new Button("PLAY");
        Button shipBtn = new Button("CREATE A SHIP");
        Button arenaBtn = new Button("ADD ARENA");
        Button highscoreBtn = new Button("HIGHSCORES");
        Button settingsBtn = new Button("SETTINGS");
        Button exitBtn = new Button("EXIT");
        
        playBtn.setMaxWidth(150);
        playBtn.setMaxHeight(50);
        
        shipBtn.setMaxWidth(150);
        shipBtn.setMaxHeight(50);
        
        arenaBtn.setMaxWidth(150);
        arenaBtn.setMaxHeight(50);
        
        highscoreBtn.setMaxWidth(150);
        highscoreBtn.setMaxHeight(50);
        
        settingsBtn.setMaxWidth(150);
        settingsBtn.setMaxHeight(50);
        
        exitBtn.setMaxWidth(150);
        exitBtn.setMaxHeight(50);
        
        menu.setPadding(new Insets(100, 0, 0, 350));
        menu.getChildren().add(playBtn);
        menu.getChildren().add(shipBtn);
        menu.getChildren().add(arenaBtn);
        menu.getChildren().add(highscoreBtn);
        menu.getChildren().add(settingsBtn);
        menu.getChildren().add(exitBtn);
        
        playBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                Game game = new Game();
                JavaProject.gameStage.setScene(Game.scene);
            }
        });
        
        shipBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                try
                 {
                    ShipCreator editor = new ShipCreator();
                 }
                catch (IOException ex) {}
                JavaProject.gameStage.setScene(ShipCreator.scene);
            }
        });
        
        highscoreBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                setScene(Screens.highscoresMenu);
            }
        });
        
        settingsBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                setScene(Screens.settingsMenu);
            }
        });
        
        exitBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                System.exit(0);
            }
        });
        
        scene = new Scene(menu, SettingsStore.screenHeight, SettingsStore.screenWidth);
    }
}

public class JavaProject extends Application
{
    static Stage gameStage;
    
    public void start(Stage stage)
    {
        gameStage = stage;
        gameStage.setTitle("StrateShips!");
        gameStage.setWidth(SettingsStore.screenWidth);
        gameStage.setHeight(SettingsStore.screenHeight);
        gameStage.setResizable(false);
        gameStage.setFullScreen(SettingsStore.fullscreen);
        gameStage.setScene(Screens.mainMenu.scene);
        gameStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}