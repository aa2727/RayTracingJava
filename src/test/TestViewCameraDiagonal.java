package test;
import java.util.*;

import Scene.camera.*;
import object3D.*;
import ray.*;
public class TestViewCameraDiagonal {
    public static void main(String[] args) {
        Camera test = new Camera(new Vector3D(7,7,7),new Vector3D(-7,-7,-7),new Vector3D(0,0,15),400,400); 
        Volume t = new Parallelepiped(5,5,5,0,0,0);
        ArrayList<Volume> at =  new ArrayList<Volume>();
        at.add(t);
        test.createImage(at);
    }
}