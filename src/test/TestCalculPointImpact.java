package test;

import object3D.Triangle;
import ray.Ray;
import ray.Vector3D;

public class TestCalculPointImpact {
    public static void main(String[] args) {
        Triangle tri = new Triangle(0,-4,0, -3,0, 0, -4, -5, 0);

        Vector3D resultat = new Vector3D(-1,-2.67,0);
        System.out.println("Comparaison fonctionnelle : " + resultat.equals(new Vector3D(-1,-2.67,0)));

        Ray h = new Ray(6.0,-8.0,10.0,-1.0,-2.67,0.0);
        System.out.println("Comparaison Point H : " + resultat.equals(tri.calculPointIntersection(h)));
        if(!resultat.equals(tri.calculPointIntersection(h))){
            System.out.println("Obtenu : " + tri.calculPointIntersection(h) + "\nAttendu :" + resultat);
        };

        Ray f = new Ray(4.0,2.0,10.0,-1.0,-2.67,0.0);
        System.out.println("Comparaison Point H : " + resultat.equals(tri.calculPointIntersection(f)));
        if(!resultat.equals(tri.calculPointIntersection(f))){
            System.out.println("Obtenu : " + tri.calculPointIntersection(f) + "\nAttendu :" + resultat);
        };

        Ray e = new Ray(-8,4.0,9.0,-1.0,-2.67,0.0);
        System.out.println("Comparaison Point e : " + resultat.equals(tri.calculPointIntersection(e)));
        if(!resultat.equals(tri.calculPointIntersection(e))){
            System.out.println("Obtenu : " + tri.calculPointIntersection(e) + "\nAttendu :" + resultat);
        };

        Ray g = new Ray(-7,9.0,7.0,-1.0,-2.67,0.0);
        System.out.println("Comparaison Point g : " + resultat.equals(tri.calculPointIntersection(g)));
        if(!resultat.equals(tri.calculPointIntersection(g))){
            System.out.println("Obtenu : " + tri.calculPointIntersection(g) + "\nAttendu :" + resultat);
        };


    }
}
