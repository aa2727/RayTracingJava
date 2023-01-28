package object3D;

import java.util.ArrayList;

import ray.Vector3D;

public class Parallelepiped extends Volume {

    /**
     * Créer un Parallelogramme de côté 1 et en position de départ (1,1,1) le parrallelogramme dans ce cas là est un cube
     */
    public Parallelepiped() {
        this(1, 1, 1, 1, 1, 1);
    }

    /**
     * Créer un Parallelogramme de côté size et en position (x,y,z)
     * @param width largeur
     * @param height hauteur
     * @param deep profondeur
     * @param x position x
     * @param y position y
     * @param z position z
     * 
     * @ensure width = width, height = height, deep = deep, x = x, y = y, z = z
     */
    public Parallelepiped(int width, int height, int deep, int x, int y, int z) {
        super(x, y, z);
        // Création des sommets
        Vector3D v1 = new Vector3D(x, y, z);
        Vector3D v2 = new Vector3D(width + x, y, y);
        Vector3D v3 = new Vector3D(x, height + y, z);
        Vector3D v4 = new Vector3D(x, y, deep + z);
        Vector3D v5 = new Vector3D(width + x, height + y, z);
        Vector3D v6 = new Vector3D(width + x, y, deep + z);
        Vector3D v7 = new Vector3D(x, height + y, deep + z);
        Vector3D v8 = new Vector3D(width + x, height + y, deep + z);

        // Création des graph pour les triangles
        Graph g1 = new Graph(v1);
        g1.addNeightbour(v2);
        g1.addNeightbour(v3);
        g1.addNeightbour(v4);
        
        Graph g2 = new Graph(v2);
        g2.addNeightbour(v1);
        g2.addNeightbour(v5);
        g2.addNeightbour(v6);

        Graph g3 = new Graph(v3);
        g3.addNeightbour(v1);
        g3.addNeightbour(v7);
        g3.addNeightbour(v5);

        Graph g4 = new Graph(v4);
        g4.addNeightbour(v1);
        g4.addNeightbour(v7);
        g4.addNeightbour(v6);

        Graph g5 = new Graph(v5);
        g5.addNeightbour(v2);
        g5.addNeightbour(v3);
        g5.addNeightbour(v8);

        Graph g6 = new Graph(v6);
        g6.addNeightbour(v2);
        g6.addNeightbour(v4);
        g6.addNeightbour(v8);

        Graph g7 = new Graph(v7);
        g7.addNeightbour(v3);
        g7.addNeightbour(v4);
        g7.addNeightbour(v8);

        Graph g8 = new Graph(v8);
        g8.addNeightbour(v5);
        g8.addNeightbour(v6);
        g8.addNeightbour(v7);

        // Création de la liste de touts les graphs pour appeller la méthode makeTriangle
        ArrayList<Graph> g = new ArrayList<Graph>();
        g.add(g1);
        g.add(g2);
        g.add(g3);
        g.add(g4);
        g.add(g5);
        g.add(g6);
        g.add(g7);
        g.add(g8);

        // attribut la liste des graphs à la classe Volume
        this.setListGraph(g);
    }

    /**
     * Main function
     * @param args
     */
    public static void main(String[] args) {
        Parallelepiped p = new Parallelepiped();
        System.out.println("Objectif triangle = " + p.getListTriangle().size() + " / 12");
        System.out.println(p.toString());
    }
}