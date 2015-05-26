/*
 * Author: Ian Wood
 * All content here is owned by Ian Wood and may only be used with his permission.
 * You can contact him at ianwood123@yahoo.com with any questions. 
 */
package javaproject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class ShipCreator{
    int control;
    Ship spaceship;
    Image piece;
    Canvas selection;
    Canvas bottomCanvas;
    Canvas topCanvas; 
    int typeSelect;
    int level;
    double angle;
    boolean flipped = false;
    boolean verticalFlip = false;
    ArrayList<Image> weapons = new ArrayList();
    ArrayList<Image> engines = new ArrayList();
    ArrayList<Image> cockpits = new ArrayList();
    ArrayList<Image> effects = new ArrayList();
    ArrayList<Image> bodies = new ArrayList();
    ArrayList<Image> presets = new ArrayList();
    
    static Scene scene;
    
    public ShipCreator() throws IOException {
        control = 0;
        typeSelect = 0;
        spaceship = new Ship();
        angle = 0;
        level = 1;
        
        String[] types = new String[]{"Weapons","Engines","Cockpits","Bodies","Presets"};
        String[] weaponFileNames = new File("src/images/weapons/").list();
        String[] engineFileNames = new File("src/images/engines/").list();
        String[] cockpitFileNames = new File("src/images/cockpits/").list();
        String[] effectsFileNames = new File("src/images/effects/").list();
        String[] bodyFileNames = new File("src/images/body/").list();
        String[] presetNames = new File("src/images/presets").list();
        //get names of files
        
        for (String weaponFileName : weaponFileNames) {
        weapons.add(new Image("images/weapons/"+weaponFileName));
        }
        for (String engineFileName : engineFileNames) {
        engines.add(new Image("images/engines/"+engineFileName));
        }
        for (String cockpitFileName : cockpitFileNames){
        cockpits.add(new Image("images/cockpits/"+cockpitFileName));
        }
        for(String effectsFileName : effectsFileNames){
        effects.add(new Image("images/effects/"+effectsFileName));
        }
        for (String bodyFileName : bodyFileNames){
        bodies.add(new Image("images/body/"+bodyFileName));
        }
        for (String presetFileName : presetNames){
        presets.add(new Image("images/presets/"+presetFileName));
        }
        //populate array of images into memory. this gives a working set of images that can be classed for drawing later

        
        Button partType = new Button(types[typeSelect]);
        partType.setOnAction((ActionEvent e) -> {
            if(typeSelect==4){
            typeSelect = 0;
            }
            else{
            typeSelect++;
            }
            partType.setText(types[typeSelect]);
            incrementPart();        
        });
        
        Button goBackBtn = new Button("Back");
        goBackBtn.setOnAction((ActionEvent e) -> {
            JavaProject.gameStage.setScene(Screens.mainMenu.scene);
        });
        
        Button nextPart = new Button("Next Part");
        nextPart.setOnAction((ActionEvent e) -> {
            incrementPart();
        });
        
        Button prevPart = new Button("Previous Part");
        prevPart.setOnAction((ActionEvent e) -> {
            decrementPart();
        });
        
        Button flipPartVert = new Button("Clear Part");
        flipPartVert.setOnAction((ActionEvent e) -> {
            clearLastPiece();
            updateSelection();
        });
        
        Button flipPart = new Button("Flip Part");
        flipPart.setOnAction((ActionEvent e) -> {
            flipped = !flipped;
            updateSelection();
        });
        
        Button saveImage = new Button("Save Spaceship");
        saveImage.setOnAction((ActionEvent e) -> {
            try {
                saveImage(bottomCanvas);
            } catch (IOException ex) {
            }
        });
       
        GridPane grid = new GridPane();
        grid.gridLinesVisibleProperty().set(true);
        
        bottomCanvas = new Canvas(250,250);
        GraphicsContext poc = bottomCanvas.getGraphicsContext2D();
            poc.setFill(Color.TRANSPARENT);
            poc.fillRect(0,0,250,250);
        
        topCanvas = new Canvas(250,250);
        GraphicsContext ptc = topCanvas.getGraphicsContext2D();
            ptc.setFill(Color.TRANSPARENT);
            ptc.fillRect(0,0,250,250);
        topCanvas.setOnMouseDragged((MouseEvent event)->{
            ptc.clearRect(0, 0, 250, 250);
            drawCompleteImage(ptc,piece,angle,event.getX(),event.getY(),flipped);
            ptc.setStroke(Color.BLACK);
            ptc.strokeRect(25, 25, 200, 200);
        });
        topCanvas.setOnMouseReleased((MouseEvent event)->{
            ptc.clearRect(0, 0, 250, 250);
            spaceship.parts.addPart(new Part(event.getX(), event.getY(), level, flipped, angle, piece));
            updateSelection();
        });

        
        piece = weapons.get(0);
        selection = new Canvas(300,300);
        GraphicsContext g = selection.getGraphicsContext2D();
        g.setFill(Color.TRANSPARENT);
        g.fillRect(0, 0, 1000, 1000);
        g.drawImage(piece, 50, 50);

        
        ColumnConstraints col = new ColumnConstraints();
        RowConstraints row = new RowConstraints();
        
        col.setHalignment(HPos.CENTER);
        row.setValignment(VPos.CENTER);
        
        grid.getColumnConstraints().add(col);
        grid.getRowConstraints().add(row);
        
        
        grid.setHgap(30);
        grid.add(bottomCanvas,0, 1);
        grid.add(topCanvas,0,1);
        grid.add(selection,2,1);
        grid.add(nextPart, 1, 1);
        grid.add(prevPart, 3, 1);
        grid.add(flipPart, 2, 2);
        grid.add(flipPartVert, 3, 3);
        grid.add(saveImage, 0, 3);
        grid.add(partType,2,0);
        grid.add(goBackBtn,0,0);


        scene = new Scene(grid, SettingsStore.screenWidth, SettingsStore.screenHeight);
        scene.setOnScroll((event)->{
            if(event.getDeltaY() > 0){
            angle++;
            }
            else if (event.getDeltaY() < 0){
            angle--;
            }
            updateSelection();
        });
        
        JavaProject.gameStage.setScene(scene);
    }

    private ArrayList<Image> verifyArrayRead(){
        if (typeSelect == 0){
            return this.weapons;
        }
        else if (typeSelect == 1){
            return this.engines;
        }
        else if(typeSelect == 2){
            return this.cockpits;
        }
        else if (typeSelect == 3){
            return this.bodies;
        }
        else if (typeSelect == 4){
            return this.presets;
        }
        else {
            typeSelect =0;
            return this.weapons;
        }
        
    }
    
    private void decrementPart() {
        ArrayList<Image> img = verifyArrayRead();
        control--;
        if (control < 0){
        control = img.size()-1;
        }
        piece = img.get(control);
        updateSelection();
        
        
    }
    
    private void incrementPart() {
        ArrayList<Image> img = verifyArrayRead();
        control++;
        if (control >= img.size()){
        control = 0;
        }
        piece = img.get(control);
        updateSelection();
        
        
    }

    private void updateSelection() {
        GraphicsContext g = selection.getGraphicsContext2D();
        g.clearRect(0, 0, 1000, 1000);
        g.setFill(Color.TRANSPARENT);
        g.fillRect(0, 0, 1000, 1000);
        drawCompleteImage(g,piece,angle,50,50,flipped);
        bottomCanvas.getGraphicsContext2D().clearRect(0, 0, 250, 250);
        spaceship.parts.getPartsCollection().forEach((parts) -> {
                
                drawCompleteImage(bottomCanvas.getGraphicsContext2D(), parts.img, (int)parts.angle, parts.x, parts.y, parts.flipped);
            });
    }

    private void drawCompleteImage(GraphicsContext g, Image img, double angle, double x, double y, boolean flipped){
        int flip;
        if (flipped){flip = -1;}
        else{flip = 1;}
        g.save();
        Scale scl = new Scale(1, flip, img.getWidth() / 2, y);
        Rotate r = new Rotate(angle, x, y);
        g.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        g.transform(scl.getMxx(), scl.getMyx(), scl.getMxy(), scl.getMyy(), scl.getTx(), scl.getTy());
        g.drawImage(img, x - img.getWidth() / 2, y - img.getHeight() / 2);
        g.restore();
    }

    private void saveImage(Canvas c) throws IOException {
        File file = new File("spaceship.png");
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        
        
        params.setViewport(new Rectangle2D (c.getLayoutX()+25, c.getLayoutY()+25, 200, 200));
        WritableImage wi = new WritableImage((int)200,(int)200);
        ImageIO.write(SwingFXUtils.fromFXImage(c.snapshot(params, wi), null), "png", file);
    }

    private void clearLastPiece(){
        GraphicsContext g = bottomCanvas.getGraphicsContext2D();
        g.clearRect(0, 0, 1000, 1000);
        if(!spaceship.parts.isEmpty()){
        spaceship.parts.removePart(spaceship.parts.getPartsCollection().get(spaceship.parts.getPartsCollection().size()-1));
        
    }
    
    }
    
    private void clearShip() {
        GraphicsContext g = bottomCanvas.getGraphicsContext2D();
        g.clearRect(0, 0, 1000, 1000);
        spaceship.parts.clearParts();
    }
}
