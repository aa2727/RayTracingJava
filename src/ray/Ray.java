package ray;

import java.lang.Math;

public class Ray {
    private Vector3D origin;
    private Vector3D direction;

    /**
     * @return la différence entre deux valeurs élevées à la puissance de 2
     */
    private static double difference(double a, double b) {
        return Math.pow(a - b, 2);
    }

    /**
     * Construct a ray from the given origin and direction with values Origin = (0,
     * 0, 0) and Direction = (0, 0, 0).
     */
    public Ray() {
        this(new Vector3D(), new Vector3D());
    }

    /**
     * Construct a ray from the given origin and direction.
     * 
     * @param origin    Vector3D
     * @param direction Vector3D
     * @require origin != null
     * @require direction != null
     * @ensure this.origin = origin
     * @ensure this.direction = direction
     */
    public Ray(Vector3D origin, Vector3D direction) {
        this.setOrigin(origin);
        this.setDirection(direction);
    }

    /**
     * Construct a ray from the given origin and direction.
     * 
     * @param xo int x for origin
     * @param yo int y for origin
     * @param zo int z for origin
     * @param xd int x for direction
     * @param yd int y for direction
     * @param zd int z for direction
     * @ensure this.origin = new Vector3D(xo, yo, zo)
     * @ensure this.direction = new Vector3D(xd, yd, zd)
     */
    public Ray(int xo, int yo, int zo, int xd, int yd, int zd) {
        this(new Vector3D(xo, yo, zo), new Vector3D(xd, yd, zd));
    }

        /**
     * Construct a ray from the given origin and direction.
     * 
     * @param xo double x for origin
     * @param yo double y for origin
     * @param zo double z for origin
     * @param xd double x for direction
     * @param yd double y for direction
     * @param zd double z for direction
     * @ensure this.origin = new Vector3D(xo, yo, zo)
     * @ensure this.direction = new Vector3D(xd, yd, zd)
     */
    public Ray(double xo, double yo, double zo, double xd, double yd, double zd) {
        this(new Vector3D(xo, yo, zo), new Vector3D(xd, yd, zd));
    }


    /**
     * Construct a ray from the given origin and direction.
     * 
     * @param ray Ray
     * @require ray != null
     * @require ray.getOrigin() != null
     * @require ray.getDirection() != null
     * @ensure origin = ray.origin
     * @ensure direction = ray.direction
     */
    public Ray(Ray ray) {
        this(ray.getOrigin(), ray.getDirection());
    }

    /**
     * Get the origin of the ray.
     * 
     * @return origin Vector3D
     */
    public Vector3D getOrigin() {
        return this.origin;
    }

    /**
     * Get the direction of the ray.
     * 
     * @return direction Vector3D
     */
    public Vector3D getDirection() {
        return this.direction;
    }

    /**
     * Set the origin of the ray.
     * 
     * @param origin Vector3D
     * @require origin != null
     * @ensure this.origin = origin
     */
    public void setOrigin(Vector3D origin) {
        this.origin = origin;
    }

    /**
     * Set the direction of the ray.
     * 
     * @param direction Vector3D
     * @require direction != null
     * @ensure this.direction = direction
     */
    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }

    /**
     * Get the length of tow vectors.
     * 
     * @param origin    Vector3D
     * @param direction Vector3D
     * @require origin != null
     * @require direction != null
     * @return length double
     */
    public static double length(Vector3D origin, Vector3D direction) {
        return Math.sqrt(difference(origin.getX(), direction.getX()) +
                difference(origin.getY(), direction.getY()) +
                difference(origin.getZ(), direction.getZ()));
    }

    /**
     * Transform a ray into a Vector3D.
     * @param ray Ray
     * @return Vector3D
     */
    public static Vector3D vector(Ray ray) {
        if (ray == null) {
            return null;
        }
        return new Vector3D(ray.getDirection().getX() - ray.getOrigin().getX(),
                ray.getDirection().getY() - ray.getOrigin().getY(), ray.getDirection().getZ() - ray.getOrigin().getZ());
    }

    /**
     * Get the normal vector of a plan
     * 
     * @param vector1 Vector3D
     * @param vector2 Vector3D
     * @require vector1 != null
     * @require vector2 != null
     * @return normal Vector3D
     */
    public static Vector3D normal(Vector3D vector1, Vector3D vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("vector1 or vector2 is null");
        }
        System.out.println(vector1 + " " + vector2);
        return new Vector3D(
            vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY(),
            vector1.getZ() * vector2.getX() - vector1.getX() * vector2.getZ(),
            vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX()
        );
    }

    /**
     * Test if this and the given ray are equals.
     * @param ray Ray
     * @return true if this and the given ray had the same origin and direction
     */
    public boolean equals(Ray ray) {
        if (ray == null) {
            return false;
        }
        return this.getOrigin().equals(ray.getOrigin()) && this.getDirection().equals(ray.getDirection());
    }

    /**
     * toString method
     * @return String
     */
    @Override
    public String toString() {
        return "Ray{" +
                "origin = " + origin +
                ", direction = " + direction +
                '}';
    }

    /**
     * Main function
     * @param args
     */
    public static void main(String[] args) {
        new Ray(new Vector3D(-1, -20, 0), new Vector3D(0, 0, 0));
    }
}