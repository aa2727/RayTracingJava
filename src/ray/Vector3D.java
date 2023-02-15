package ray;

public class Vector3D extends Vector2D {
    /// Z coordonate
    private double z;

    /**
     * Créer un Vector 3D avec les coordonnées 0,0,0
     */
    public Vector3D() {
        super(0, 0);
        this.setZ(0);
    }

    /**
     * Créer un Vector 3D avec les coordonnées x,y,z
     * 
     * @param x coordonate x
     * @param y coordonate y
     * @param z coordonate z
     * @ensure x = x and y = y and z = z
     */
    public Vector3D(double x, double y, double z) {
        super(x, y);
        this.setZ(z);
    }

    /**
     * Creer un Vector 3D avec les coordonnées x,y du Vector 2D et z = 0
     * 
     * @param v Vector2D to copy
     * @require v != null
     * @ensure x = v.x and y = v.y and z = 0
     */
    public Vector3D(Vector2D v) {
        super(v.getX(), v.getY());
        this.setZ(0);
    }

    /**
     * Creer un Vector 3D avec les coordonnées x,y,z du Vector 3D
     * 
     * @param v Vector3D to copy
     * @ensure x = v.x and y = v.y and z = v.z
     */
    public Vector3D(Vector3D v) {
        super(v.getX(), v.getY());
        this.setZ(v.getZ());
    }

    /**
     * Get the coordonate z
     * @return coordonate z
     */
    public double getZ() {
        return this.z;
    }

    /**
     * Set the coordonate z
     * @param z coordonate z
     * @ensure z = z
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Return a string representation of the vector.
     * @return a string representation of the vector.
     */
    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")";
    }

    /**
     * @return true if the vector is equal to the given vector
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Vector3D)) {
            return false;
        }
        Vector3D vector3D = (Vector3D) o;
        return this.getZ() == vector3D.getZ() && (super.getY() == vector3D.getY())&&(super.getX() == vector3D.getX());
    }

    /**
     * Compute the norm of the vector
     * @return the norm
     */
    public double norm() {
        return Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY() + this.getZ() * this.getZ());
    }

    /**
     * Create a normalized version of the vector
     * @return the Vector3D normalized
     */
    public Vector3D normalize(){
        double norm = this.norm();
        return new Vector3D(this.getX()/norm,this.getY()/norm,this.getZ()/norm);
    }

    /**
     * Add the given vector to this vector.
     * 
     * @param v Vector3D to copy
     * @require v != null
     * @ensure x = v.x and y = v.y and z = v.z
     */
    public void add(Vector3D v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector3D.add: v is null");
        }
        this.add(v.getX(), v.getY(), v.getZ());
    }

    /**
     * Add the given value to the vector
     * 
     * @param x double
     * @param y double
     * @param z double
     * @ensure x = x + x and y = y + y and z = z + z
     */
    public void add(double x, double y, double z) {
        super.add(x, y);
        this.setZ(this.getZ() + z);
    }

    /**
     * Substract the given vector to this vector.
     * @param v Vector3D to substract
     * @return the substracted vector
     */
    public void sub(Vector3D v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector3D.sub: v is null");
        }
        this.sub(v.getX(), v.getY(), v.getZ());
    }

    /**
     * Substract the given value to the vector
     * 
     * @param x double
     * @param y double
     * @param z double
     * @ensure x = x - x and y = y - y and z = z - z
     */
    public void sub(double x, double y, double z) {
        super.sub(x, y);
        this.setZ(this.getZ() - z);
    }


    /**
     * Get the middle of two vectors
     * @param v1 Vector3D
     * @param v2 Vector3D
     * @return the middle of the two vectors
     */
    public static Vector3D middle(Vector3D v1, Vector3D v2) {
        if (v1 == null || v2 == null) {
            return null;
        }
        return new Vector3D((v1.getX() + v2.getX()) / 2, (v1.getY() + v2.getY()) / 2, (v1.getZ() + v2.getZ()) / 2);
    }

    /**
     * Create the vectorial product of two vectors
     * @param a Vector3D
     * @param b Vector3D
     * @return the vectorial product of the two vectors
     */
    public static Vector3D vectorialProduct(Vector3D a, Vector3D b) {
        if (a == null || b == null) {
            return null;
        }
        return new Vector3D(a.getY()*b.getZ()-a.getZ()*b.getY(),a.getZ()*b.getX()-a.getX()*b.getZ(),a.getX()*b.getY()-a.getY()*b.getX());
    }

    /**
     * Calculate the scalar product between two Rays
     * @param firstRay
     * @param secondRay
     * @return the scalar product
     */
    public static double scalarProduct(Ray firstRay,Ray secondRay){
        Vector3D firstVector3d = Ray.vector(firstRay);
        Vector3D secondVector3d = Ray.vector(secondRay);
        return scalarProduct(firstVector3d,secondVector3d);
    }

    /**
     * Calculate the scalar product between two vectors
     * @param firstVector3D
     * @param secondVector3D
     * @return the scalar product
     */
    public static double scalarProduct(Vector3D firstVector3D,Vector3D secondVector3D){
        return firstVector3D.getX()*secondVector3D.getX()+firstVector3D.getY()*secondVector3D.getY()+firstVector3D.getZ()*secondVector3D.getZ();
    }

    public static Vector3D substract(Vector3D v, Vector3D u) {
        return new Vector3D(v.getX()-u.getX(),v.getY()-u.getY(),v.getZ()-u.getZ());
    }
    
    /**
     * Main function for testing
     */
    public static void main(String argv[]) {
        Vector3D v = new Vector3D(5, 0,0);
        Vector3D v3 = new Vector3D(5,5,0);
        System.out.println(vectorialProduct(v, v3));
    }
}