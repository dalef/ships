/*
 * Author: Ian Wood
 * All content here is owned by Ian Wood and may only be used with his permission.
 * You can contact him at ianwood123@yahoo.com with any questions. 
 */

package javaproject;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Ship {
    PartsCollection parts;
    PartsCollection effects;
    
    Ship(){
        parts = new PartsCollection();
        effects = new PartsCollection();
    }
}

class PartsCollection{
    private ArrayList<Part> parts;
    int zIndex;
    
    PartsCollection(){
        parts = new ArrayList();
        zIndex = 0;
    }
    
    public void addPart(Part e){
        e.setLevel(zIndex);
        parts.add(e);
        incrementZIndex();
    }
    
    public void removePart(Part e){
        if(parts.contains(e)){
            parts.remove(e);
        }
    }
    
    private void setPartZIndex(Part e){
        e.setLevel(zIndex);
    
    }
    
    private void incrementZIndex(){
        zIndex++;
    }
    
    public void clearParts(){
        parts.clear();
        zIndex = 0;
    }

    public ArrayList<Part> getPartsCollection(){
        return this.parts;
    }
    
    public boolean isEmpty(){
        return this.parts.isEmpty();
    }
}

class Part{
        int x, y;
        boolean flipped;
        double angle;
        int level;
        Image img;
        
        Part(double x, double y, int lvl, boolean flip, double angl, Image img){
            this.x = (int) x;
            this.y = (int) y;
            this.flipped = flip;
            this.angle = angl;
            this.level = lvl;
            this.img = img;
        }
        
        void setLevel(int lvl){
            if(lvl >= 0){
            this.level = lvl;
            }
            else{
                this.level = 0;
            }
        }
        
    }    


//this code cannot be reached
//collection sorting.
//                    Collections.sort(spaceship.parts, (Ship.Part t, Ship.Part t1) -> {
//            if(t.level == t1.level)
//                return 0;
//            return t.level < t1.level ? -1 : 1;
//        });









