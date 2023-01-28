package object3D;

import java.util.ArrayList;

import ray.Vector3D;

public class Graph {
    private Vector3D center;
    private ArrayList<Vector3D> neightbour;

    /**
     * Initialize the object
     * @ensure center = {0, 0, 0}
     * @ensure neightbour = void
     */
    public Graph() {
        this.center = new Vector3D(0, 0, 0);
        this.neightbour = new ArrayList<Vector3D>();
    }

    /**
     * Initialize the object
     * @param center center of the graph
     * @ensure center = center
     */
    public Graph(Vector3D center) {
        this.center = center;
        this.neightbour = new ArrayList<Vector3D>();
    }

    /**
     * Initialize the object
     * @param v Vector3D
     * @param a ArrayList<Vector3D>
     * @ensure center = v
     * @ensure neightbour = a
     */
    public Graph(Vector3D v, ArrayList<Vector3D> a) {
        this.center = v;
        this.neightbour = a;
    }

    /**
     * Initialize the object
     * @param g Graph
     * @ensure center = g.center
     * @ensure neightbour = g.neightbour
     */
    public Graph(Graph g) {
        this.center = g.getCenter();
        this.neightbour = g.getNeightbour();
    }

    /**
     * Get the center of the graph
     * @return center
     */
    public Vector3D getCenter() {
        return this.center;
    }

    /**
     * Get the list of neightbour
     * @return neightbour
     */
    public ArrayList<Vector3D> getNeightbour() {
        return this.neightbour;
    }

    /**
     * Get the size of the list of neightbour
     * @return neightbourSize
     */
    public int getNeightbourSize() {
        return this.neightbour.size();
    }

    /**
     * Get a neightbour
     * @param i position
     * @return neightbour.get(i)
     * @require i >= 0 && i < neightbourSize
     */
    public Vector3D getNeightbour(int i) {
        if (i < 0 || i >= this.getNeightbourSize()) {
            throw new IllegalArgumentException("Graph.getNeightbour: i is out of range");
        }
        return this.neightbour.get(i);
    }

    /**
     * Set the center of the graph
     * @param center Vector3D
     * @require center != null
     * @ensure center = center
     */
    public void setCenter(Vector3D center) {
        if (center == null) {
            throw new IllegalArgumentException("Graph.setCenter: center is null");
        }
        this.center = center;
    }

    /**
     * Set the list of neightbour
     * @param neightbour ArrayList<Vector3D>
     * @require neightbour != null
     * @ensure neightbour = neightbour
     * @ensure neightbourSize = neightbour.size()
     */
    public void setNeightbour(ArrayList<Vector3D> neightbour) {
        if (neightbour == null) {
            throw new IllegalArgumentException("Graph.setNeightbour: neightbour is null");
        }
        this.neightbour = neightbour;
    }

    /**
     * Set a neightbour
     * @param v Vector3D
     * @require v != null
     * @ensure neightbour.add(v)
     * @ensure neightbourSize ++
     */
    public void addNeightbour(Vector3D v) {
        if (v == null) {
            throw new IllegalArgumentException("Graph.addNeightbour: v is null");
        }
        this.neightbour.add(v);
    }

    public String toString() {
        String s = "";
        s += "center: " + this.center.toString() + "\n";
        s += "neightbour: ";
        for (int i = 0; i < this.getNeightbourSize(); i++) {
            s += this.neightbour.get(i).toString() + " ";
        }
        s += "\n";
        return s;
    }

    public boolean superpose(Graph graph) {
        for (int i = 0; i < this.getNeightbourSize(); i ++) {
            if (this.getNeightbour(i).equals(graph.getCenter())) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.setCenter(new Vector3D(0, 0, 0));
        ArrayList<Vector3D> a = new ArrayList<Vector3D>();
        a.add(new Vector3D(1, 1, 1));
        a.add(new Vector3D(2, 2, 2));
        g.setNeightbour(a);
        System.out.println(g.getCenter());
        System.out.println(g.getNeightbour(0));
        System.out.println(g.getNeightbour(1));
        System.out.println(g);
    }
}

