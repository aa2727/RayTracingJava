package test;

import ray.Vector3D;

public class TestVector3D {
    public static void main(String[] args) {
        Vector3D u = new Vector3D(1,5,5);
        Vector3D v = u.normalize();
        System.out.println(v);
        System.out.println(v.equals(new Vector3D(1,1,1)));
        
    }
}
