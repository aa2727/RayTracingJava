package ray;

public class Vector2D {
    /// X coordonate
    private double x;
    /// Y coordonate
    private double y;

    /**
     * Créer un Vector 2D avec les coordonnées 0,0
     */
    public Vector2D () {
        this(0, 0);
    }

    /**
     * Creer un Vector 2D avec les coordonnées x,y
     * @param x coordonnée x
     * @param y coordonnée y
     * @ensure x = x
     * @ensure y = y
     */
    public Vector2D(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Creer un Vector 2D avec les coordonnées x,y du Vector 2D
     * @param v Vector2D
     * @requires v != null
     * @ensure x = v.x
     * @ensure y = v.y
     */
    public Vector2D(Vector2D v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector2D: v is null");
        }
        this.setX(v.getX());
        this.setY(v.getY());
    }

    /**
     * Get the coordonate x
     * @return coordonate x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get the coordonate y
     * @return coordonate y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Set the coordonate x
     * @param x coordonate x
     * @ensure x = x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set the coordonate y
     * @param y coordonate y
     * @ensure y = y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Additionne les coordonnées du Vector 2D avec celui passé en paramètre
     * @param v Vector2D
     * @requires v != null
     * @ensure x = x + v.x
     * @ensure y = y + v.y
     */
    public void add(Vector2D v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector2D.add: v is null");
        }
        this.add(v.getX(), v.getY());
    }

    /**
     * Additionne les coordonnées du Vector 2D avec les coordonnées x,y passées en paramètre
     * @param x double
     * @param y double
     * @ensure x = x + x
     * @ensure y = y + y
     */
    public void add(double x, double y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

    /**
     * Multiplie les coordonnées du Vector 2D avec les coordonnées x,y passées en paramètre
     * @ensure this.x *= x
     * @ensure this.y *= y
     */
    public void multiply(double x, double y) {
        this.setX(this.getX() * x);
        this.setY(this.getY() * y);
    }

    /**
     * Multiplie les coordonnées du Vector 2D avec celui passé en paramètre
     * @param v Vector2D
     * @requires v != null
     * @ensure this.x *= x
     * @ensure this.y *= y
     */
    public void multiply(Vector2D v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector2D.multiply: v is null");
        }
        this.multiply(v.getX(), v.getY());
    }

    /**
     * Return a string representation of the vector.
     * @return a string representation of the vector.
     */
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }


    /**
     * main function
     * @param args
     */
    public static void main(String[] args) {
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(v1);
        System.out.println(v2);
    }
}