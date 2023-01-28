package object3D;

import java.util.ArrayList;

import ray.*;

public class Volume{
    private Vector3D position;
    private ArrayList<Triangle> listTriangle;
    private ArrayList<Vector3D> listCenter;
    private ArrayList<Graph> listGraph;

    /**
     * Create the triangle for draw the object
     * @ensure listTriangle = list triangle without duplicate
     */
    private void makeTriangle() {
        // Initialise les listes et le boolean
        ArrayList<Graph> arrayGraph = new ArrayList<>();
        this.listTriangle = new ArrayList<Triangle>();
        this.listCenter = new ArrayList<Vector3D>();
        boolean exist;

        // La boucle va se faire pour chaques graph présent dans la liste de graph de la classe
        for (int i = 0; i < this.listGraph.size(); i++) {
            exist = false;

            // Cherche si un triangle créer par le graph est déjà présent dans la liste
            for (int j = 0; j < arrayGraph.size(); j++) {
                if (this.listGraph.get(i).superpose(arrayGraph.get(j))) {
                    exist = true;
                    break;
                }
            }

            // Si le triangle n'est pas présent dans la liste, on l'ajoute
            if (!exist) {
                Graph g = this.listGraph.get(i);
                arrayGraph.add(g);
                this.listCenter.add(g.getCenter());
                for (int j = 0; j < g.getNeightbourSize(); j++) {
                    this.listTriangle.add(new Triangle(g.getCenter(), g.getNeightbour(j % g.getNeightbourSize()),
                            g.getNeightbour((j + 1) % g.getNeightbourSize())));
                }
            }
        }
    }

    /**
     * Constructor for the object
     * 
     * @ensure x = 0, y = 0, z = 0 and triangleSize = 0
     */
    public Volume() {
        this(0, 0, 0);
    }

    /**
     * Constructor for the object
     * 
     * @param x position x
     * @param y position y
     * @param z position z
     * @ensure x = x, y = y, z = z and listTriangle = new ArrayList<Triangle>()
     */
    public Volume(int x, int y, int z) {
        super();
        this.position = new Vector3D(x, y, z);
        this.listTriangle = new ArrayList<Triangle>();
        this.listCenter = new ArrayList<Vector3D>();
        this.listGraph = new ArrayList<Graph>();
        this.makeTriangle();
    }

    /**
     * Constructor for the object
     * 
     * @param x    position x
     * @param y    position y
     * @param z    position z
     * @param list list of triangles
     * @requires list != null
     * @ensure x = x, y = y, z = z and listTriangle = list
     */
    public Volume(int x, int y, int z, ArrayList<Triangle> list) {
        this(x, y, z);
        if (list == null) {
            throw new IllegalArgumentException("Object.Object: list is null");
        }
        this.setListTriangle(list);
    }

    /**
     * Constructor for the object
     * 
     * @param position Vector3D
     * @require position != null
     * @ensure x = position.x, y = position.y, z = position.z
     */
    public Volume(Vector3D position) {
        this((int) position.getX(), (int) position.getY(), (int) position.getZ());
    }

    /**
     * Constructor for the object
     * 
     * @param object object to copy
     * @ensure x = object.x, y = object.y, z = object.z
     */
    public Volume(Volume object) {
        this((int) object.getPosition().getX(), (int) object.getPosition().getY(), (int) object.getPosition().getZ());
        this.setListTriangle(object.getListTriangle());
    }

    /**
     * Get the position of the object
     * 
     * @return position
     */
    public ArrayList<Triangle> getListTriangle() {
        return listTriangle;
    }

    /**
     * Get the position of the object
     * 
     * @return Vector3D position
     */
    public Vector3D getPosition() {
        return this.position;
    }

    /**
     * Set the position of the object
     * 
     * @param list list of triangles
     * @requires list != null
     */
    public void setListTriangle(ArrayList<Triangle> list) {
        if (list == null) {
            throw new IllegalArgumentException("Object.setListTriangle: list is null");
        }
        this.listTriangle = list;
    }

    /**
     * Get the position of the object
     * 
     * @param list ArrayList<Triangle>
     * @requires list != null
     */
    public void setListGraph(ArrayList<Graph> list) {
        if (list == null) {
            throw new IllegalArgumentException("Object.setListGraph: list is null");
        }
        this.listGraph = list;
        this.makeTriangle();
    }

    /**
     * Set the position of the object
     * 
     * @param position Vector3D position
     * @require position != null
     * @ensure position = position
     */
    public void setPosition(Vector3D position) {
        if (position == null) {
            throw new IllegalArgumentException("position is null");
        }
        this.position = position;
    }

    /**
     * Set the position of the object
     * 
     * @param x position x
     * @param y position y
     * @param z position z
     * @ensure x = x, y = y, z = z
     */
    public void setPosition(int x, int y, int z) {
        this.position = new Vector3D(x, y, z);
    }

    /**
     * toString method
     * @return String
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < this.listTriangle.size(); i++) {
            str += this.listTriangle.get(i) + "\n";
        }
        return str;
    }

    public static void main(String[] args) {
        Volume object = new Volume(1, 2, 3);
        Graph g = new Graph();
        g.addNeightbour(new Vector3D(1, 2, 3));
        g.addNeightbour(new Vector3D(2, 3, 4));
        g.addNeightbour(new Vector3D(3, 4, 5));
        g.addNeightbour(new Vector3D(4, 5, 6));
        ArrayList<Graph> list = new ArrayList<Graph>();
        list.add(g);
        Graph g2 = new Graph();
        g2.addNeightbour(new Vector3D(1, 2, 3));
        g2.addNeightbour(new Vector3D(2, 3, 4));
        g2.addNeightbour(new Vector3D(3, 4, 5));
        g2.addNeightbour(new Vector3D(4, 5, 6));
        list.add(g2);
        object.setListGraph(list);
        System.out.println(object.getPosition());
        System.out.println(object);
    }
}