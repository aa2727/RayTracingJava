package test;
import object3D.*;
import ray.*;
public class TestTriangle {

    public static void main(String[] args) {
        Triangle tri = new Triangle(5,0,5, 0,0, 5, 5, 5, 5);

        System.out.println((tri.isOnEdge(new Vector3D(2.5,2.5,5))));
        System.err.println(tri.isOnLine(new Ray(0,0,5,5,5,5), new Vector3D(2.44,2.44,5)));
        System.out.println((tri.isOnEdge(new Vector3D(2.74,2.74,5))));
        System.out.println((tri.isOnEdge(new Vector3D(27.43,2.74,5))));
        System.out.println(tri.calculPointIntersection(new Ray(0,0,20,50,5,5)));
        System.out.println(tri.calculPointIntersection(new Ray(new Vector3D(2.5,2.5,10),new Vector3D(2.5,2.5,-210))));
        //System.out.println(tri.getCanonicalBaseVector3D(new Vector3D(0,0,1)));
    }
    
}
