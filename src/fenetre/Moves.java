package fenetre;

import ray.*;
import javax.swing.*;

import Scene.camera.*;
import fenetre.AfficheImage;
import java.awt.*; 


//Every method in this class modify the position of the camera depending on what key is pressed
public class Moves{


    public Moves(){};


    /**
     * Modify Y to change camera's position, here the camera goes up
     * @param camera Camera
     */
    public static void up(Camera camera){
        Vector3D position = camera.getPosition();       //"Collect" the position of the camera         
        position.setY(position.getY() -1);              // and modify the choosen coordinates
    }


    /**
     * Modify Y to change camera's position, here the camera down up
     * @param camera Camera
     */
    public static void down(Camera camera){
        Vector3D position = camera.getPosition();
        position.setY(position.getY() +1);
    }    

    
    public static void left(Camera camera){
        Vector3D position = camera.getPosition();
        position.setX(position.getX() -1);
    }


    public static void right(Camera camera){
        Vector3D position = camera.getPosition();
        position.setX(position.getX() +1);
    }


    public static void forward(Camera camera){
        Vector3D position = camera.getPosition();
        position.setZ(position.getZ() -1);
    }


    public static void backward(Camera camera){
        Vector3D position = camera.getPosition();
        position.setZ(position.getZ() +1);
    }


    public static void roundLeft(Camera camera){
        Vector3D orientation = camera.getOrientation();
        orientation.setX(orientation.getX() +1);
        camera.setOrientation(orientation);                 //Replace the actual orientation by the given one
    }


    public static void roundRight(Camera camera){
        Vector3D orientation = camera.getOrientation();
        orientation.setX(orientation.getX() -1);
        camera.setOrientation(orientation);
    }


    
}