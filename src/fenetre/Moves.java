package fenetre;

import ray.*;
import Scene.camera.*;

//Every method in this class modify the position of the camera depending on what key is pressed
public class Moves{

    /**
     * Modify Y to change camera's position, here the camera goes up
     * @param camera Camera
     */
    public static void up(Camera camera){
        Vector3D position = camera.getPosition();
        Vector3D upDirection = camera.getUpOrientation();     //"Get the position of the camera         
        position.add(upDirection.normalize());              // and modify the choosen coordinate
    }

    /**
     * Modify Y to change camera's position, here the camera down up
     * @param camera Camera
     */
    public static void down(Camera camera){
        Vector3D position = camera.getPosition();
        Vector3D upDirection = camera.getUpOrientation();     //"Get the position of the camera         
        position.sub(upDirection.normalize());              // and modify the choosen coordinate
    }    

    public static void left(Camera camera){
        Vector3D position = camera.getPosition();
        Vector3D SideDirection = camera.getSideOrientation();     //"Get the position of the camera         
        position.sub(SideDirection.normalize());   
    }

    public static void right(Camera camera){
        Vector3D position = camera.getPosition();
        Vector3D SideDirection = camera.getSideOrientation();     //"Get the position of the camera         
        position.add(SideDirection.normalize());  
    }

    public static void forward(Camera camera){
        Vector3D position = camera.getPosition();
        position.add(camera.getOrientation().normalize()); 
    }

    public static void backward(Camera camera){
        Vector3D position = camera.getPosition();
        position.sub(camera.getOrientation().normalize()); 
    }

    public static void roundLeft(Camera camera){
        Vector3D orientation = camera.getOrientation();
        Vector3D SideDirection = camera.getSideOrientation(); 
        orientation.add(SideDirection.normalize());
        camera.setOrientation(orientation);               //Replace the actual orientation by the given one
    }


    public static void roundRight(Camera camera){
        Vector3D orientation = camera.getOrientation();
        Vector3D SideDirection = camera.getSideOrientation(); 
        orientation.sub(SideDirection.normalize());
        camera.setOrientation(orientation);   
    }


    
}