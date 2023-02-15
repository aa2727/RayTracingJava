package object3D;

import java.util.ArrayList;
import Shade.materials.*;
import ray.*;

public class Triangle extends Material{
    private Vector3D vertexA;
    private Vector3D vertexB;
    private Vector3D vertexC;
    private double[][] passageMatrix2Canonical;
    private Ray normal;

    /**
     * Créer un Triangle avec les coordonnées de tous ses sommet valant 0,0,0
     */
    public Triangle() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    /**
     * Créer un Triangle avec les coordonnées de ses sommet
     * @param vectA coordonnées du premier sommet
     * @param vectB coordonnées du deuxième sommet
     * @param vectC coordonnées du troisième sommet
     */
    public Triangle(Vector3D vectA, Vector3D vectB, Vector3D vectC) {
        super();
        this.setVertexA(vectA);
        this.setVertexB(vectB);
        this.setVertexC(vectC);
        this.passageMatrix2Canonical = new double[3][3];
        this.setNormal();
    }

    /**
     * Créer un Triangle avec les coordonnées de ses sommet
     * @param xa x coordonnée du premier sommet
     * @param ya y coordonnée du premier sommet
     * @param za z coordonnée du premier sommet
     * @param xb x coordonnée du deuxième sommet
     * @param yb y coordonnée du deuxième sommet
     * @param zb z coordonnée du deuxième sommet
     * @param xc x coordonnée du troisième sommet
     * @param yc y coordonnée du troisième sommet
     * @param zc z coordonnée du troisième sommet
     */
    public Triangle(int xa, int ya, int za, int xb, int yb, int zb, int xc, int yc, int zc) {
        this(new Vector3D(xa, ya, za),new Vector3D(xb, yb, zb),new Vector3D(xc, yc, zc));
    }

    /**
     * Créer un Triangle avec les coordonnées de ses sommet
     * @param xa x coordonnée du premier sommet
     * @param ya y coordonnée du premier sommet
     * @param za z coordonnée du premier sommet
     * @param xb x coordonnée du deuxième sommet
     * @param yb y coordonnée du deuxième sommet
     * @param zb z coordonnée du deuxième sommet
     * @param xc x coordonnée du troisième sommet
     * @param yc y coordonnée du troisième sommet
     * @param zc z coordonnée du troisième sommet
     */
    public Triangle(double xa, double ya, double za, double xb, double yb, double zb, double xc, double yc, double zc) {
        this(new Vector3D(xa, ya, za),new Vector3D(xb, yb, zb),new Vector3D(xc, yc, zc));
    }

    /**
     * Get the Vertex A
     * @return the Vertex A
     */
    public Vector3D getVertexA() {
        return this.vertexA;
    }

    /**
     * Get the Vertex B
     * @return the Vertex B
     */
    public Vector3D getVertexB() {
        return this.vertexB;
    }

    /**
     * Get the Vertex C
     * @return the Vertex C
     */
    public Vector3D getVertexC() {
        return this.vertexC;
    }

    /**
     * Set the Vertex A
     * @param vect the Vertex A
     */
    public void setVertexA(Vector3D vect) {
        if (vect == null) {
            throw new IllegalArgumentException("Vertex A cannot be null");
        }
        this.vertexA = vect;
    }

    /**
     * Set the Vertex B
     * @param vect the Vertex B
     */
    public void setVertexB(Vector3D vect) {
        if (vect == null) {
            throw new IllegalArgumentException("Vertex B cannot be null");
        }
        this.vertexB = vect;
    }

    /**
     * Set the Vertex C
     * @param vect the Vertex C
     */
    public void setVertexC(Vector3D vect) {
        if (vect == null) {
            throw new IllegalArgumentException("Vertex C cannot be null");
        }
        this.vertexC = vect;
    }

    public Ray getRay1() {
        return new Ray(this.getVertexA(), this.getVertexB());
    }

    public Ray getRay2() {
        return new Ray(this.getVertexB(), this.getVertexC());
    }

    public Ray getRay3() {
        return new Ray(this.getVertexC(), this.getVertexA());
    }

    /**
     * Set the Normal Ray
     */
    private void setNormal() {
        Ray ray = new Ray(this.vertexA, Ray.normal(Vector3D.substract(vertexC, vertexA), Vector3D.substract(vertexB, vertexA)));
        this.normal = ray;
    }

    /**
     * Get the Normal Ray
     * @return the Normal Ray
     */
    public Ray getNormal() {
        return this.normal;
    }

    /**
     * Get the list of Vertex
     * @return the list of Vertex
     */
    public ArrayList<Vector3D> getVertices() {
        ArrayList<Vector3D> list = new ArrayList<Vector3D>();
        list.add(this.vertexA);
        list.add(this.vertexB);
        list.add(this.vertexC);
        return list;
    }

    /**
     * Test if this and the Triangle are equals
     * @param triangle the Triangle to test
     * @return true if the Triangle has the same Vertex
     */
    public boolean equals(Triangle triangle) {
        return (this.getVertexA().equals(triangle.getVertexA()) || this.getVertexA().equals(triangle.getVertexB())
                || this.getVertexA().equals(triangle.getVertexC()))
                && (this.getVertexB().equals(triangle.getVertexA()) || this.getVertexB().equals(triangle.getVertexB())
                        || this.getVertexB().equals(triangle.getVertexC()))
                && (this.getVertexC().equals(triangle.getVertexA()) || this.getVertexC().equals(triangle.getVertexB())
                        || this.getVertexC().equals(triangle.getVertexC()));
    }

    /**
     * toString method
     * @return the String representation of the Triangle
     */
    public String toString() {
        return "Triangle: " + this.vertexA + " " + this.vertexB + " " + this.vertexC;
    }

    /**
     * Print the Matrix in parameter
     * @param matrix the Matrix to print
     */
    public static void printMatrix33(double[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * Change the vector v in the base of the triangle to the canonical base 
     * @param v Vector3D in the triangle base
     * @return the vector v in a canonical base
     */
    public Vector3D getCanonicalBaseVector3D(Vector3D v) {
        double x = this.passageMatrix2Canonical[0][0] * v.getX() + this.passageMatrix2Canonical[0][1] * v.getY() + this.passageMatrix2Canonical[0][2] * v.getZ();
        double y = v.getX() * this.passageMatrix2Canonical[1][0] + v.getY() * this.passageMatrix2Canonical[1][1] + v.getZ() * this.passageMatrix2Canonical[1][2];
        double z = v.getX() * this.passageMatrix2Canonical[2][0] + v.getY() * this.passageMatrix2Canonical[2][1] + v.getZ() * this.passageMatrix2Canonical[2][2];
        return new Vector3D(x,y,z);
    }

    /**
     * Compute the passage matrix from the (u,v,Ot) base to the canonical base
     * @param u Vector3D
     * @param v Vector3D
     * @param Ot Vector3D
     * @return the passage matrix
     */
    public double[][] computePassageMatrix2TriangleBase(Vector3D u,Vector3D v, Vector3D Ot){
        double [][] res = new double[3][3];
        res[0][0] = u.getX();
        res[0][1] = v.getX();
        res[0][2] = Ot.getX();
        res[1][0] = u.getY();
        res[1][1] = v.getY();
        res[1][2] = Ot.getY();
        res[2][0] = u.getZ();
        res[2][1] = v.getZ();
        res[2][2] = Ot.getZ();
        return res;

    }

    /**
     * Check if a point is on a straight line
     * @param ray straight light
     * @param point point to check
     * @return True if the distance between the point and the line is under 0.1, otherwise False
     */
    //https://fr.wikipedia.org/wiki/Distance_d%27un_point_%C3%A0_une_droite
    public boolean isOnLine(Ray ray,Vector3D point) {
        double dist = Vector3D.vectorialProduct(new Vector3D(point.getX() - ray.getOrigin().getX(),point.getY() - ray.getOrigin().getY(),point.getZ() - ray.getOrigin().getZ()), Ray.vector(ray)).norm() / Ray.vector(ray).norm();
        //System.out.println(dist);
        return dist <= 0.1;
    }

    /**
     * Check if a point on the triangle is on an edge
     * @param point in the triangle to check
     * @return True if the point is on the edge, False otherwise
     */
    public boolean isOnEdge(Vector3D point){
        Ray AB = getRay1();
        Ray AC = getRay3();
        Ray BC = getRay2();
        return isOnLine(AB, point) || isOnLine(AC, point) || isOnLine(BC, point);
    }

    /**
     * Compute the passage matrix from the (u,v,Ot) base to the canonical base
     * @param u Vector of the base of the triangle
     * @param v Vector of the base of the triangle
     * @param r Vector of the ray 
     */
    public void computePassageMatrix2Canonical(Vector3D u,Vector3D v, Vector3D r){
        double [][] res = computePassageMatrix2TriangleBase(u, v, r);
        this.passageMatrix2Canonical = res;
    }
    
    /**
     * Find the point intersection with the triangle and the ray r
     * @param r ray shoot from the camera
     * @return point of intersection between the ray and the triangle (the point may be incorrect)
     */
    //https://fr.wikipedia.org/wiki/Ray_tracing
    public Vector3D calculPointIntersection(Ray r){
        Vector3D Ot = Ray.vector(new Ray(getVertexA(),r.getOrigin()));
        Vector3D vect_AB = Ray.vector(new Ray(getVertexA(),getVertexB()));
        Vector3D vect_AC = Ray.vector(new Ray(getVertexA(),getVertexC()));
        computePassageMatrix2Canonical(vect_AB, vect_AC, Ray.vector(r));
        Vector3D Vp_Ot_v = Vector3D.vectorialProduct(Ot,vect_AC);
        Vector3D Vp_Ot_u = Vector3D.vectorialProduct(vect_AB,Ot);
        Vector3D n = Vector3D.vectorialProduct(vect_AB, vect_AC);
        double divisor = Vector3D.scalarProduct(n, Ray.vector(r));
        double x = -1*(Vector3D.scalarProduct(n, Ot)/divisor);
        double y = Vector3D.scalarProduct(Vp_Ot_v, Ray.vector(r))/divisor;
        double z = Vector3D.scalarProduct(Vp_Ot_u, Ray.vector(r))/divisor;

        if ((y + z <= 1) && (x >= 0) && (y >= 0) && (y <= 1) && (z >= 0) && (z <= 1)){
            Vector3D res = getCanonicalBaseVector3D(new Vector3D(y, z, x));
            res.add(r.getOrigin());
            res.setX(res.getX()/2);
            res.setY(res.getY()/2);
            return res; 
        }
        return null;
    }

    /**
     * Test if the ray hit the triangle
     * @return point of intersection between the ray and the triangle
     */
    public static Vector3D intersectionTriangle(Triangle triangle, Ray ray) {
        Vector3D point = triangle.calculPointIntersection(ray);
        return point;
    }
}