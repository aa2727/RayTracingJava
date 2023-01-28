package Scene.camera;
import ray.Ray;
import ray.Vector3D;
import object3D.Triangle;
import object3D.Volume;
import Shade.light.*;
import java.util.*;

public class Camera{
    private Vector3D position;
    private Vector3D orientation;
    private Vector3D lightPosition;
    private double angle;
    private double[][] rotationMatrix;
    private int imageWidth;
    private int imageHeight;
    private PixelColor[][] pixels;
    static private final int SPACE_CANVAS_ORIGIN = 200;

    public Camera(Vector3D position, Vector3D orientation,Vector3D lightPosition,int height,int width) {
        this.pixels = new PixelColor[width][height];
        this.rotationMatrix = new double[3][3];
        this.position = position;
        this.orientation = orientation;
        this.lightPosition = lightPosition;
        this.imageHeight = height;
        this.imageWidth = width;
        this.setAngleRotation();
        this.setRotationMatrix();
    }

    public Camera() {
        this(new Vector3D(),new Vector3D(0,0,SPACE_CANVAS_ORIGIN),new Vector3D(0,10,0),400,400);
    }

    public Vector3D getPosition() {
        return this.position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Vector3D getOrientation() {
        return this.orientation;
    }

    /**
     * Set the orientation and update the angle rotation and the rotation matrix
     * @param orientation
     */
    public void setOrientation(Vector3D orientation) {
        this.orientation = orientation;
        this.setAngleRotation();
        this.setRotationMatrix();
    }
    @Override
    public String toString() {
        return "{" +
            " position='" + getPosition() + "'" +
            ", orientation='" + getOrientation() + "'" +
            "}";
    }
    /**
     * Create the asked Ray of the canvas to shoot it
     * @param i the x position of the pixel on the image
     * @param j the y position of the pixel on the image
     * @return Ray to shoot in order to colorize or not the pixel[i,j]
     */
    private Ray createCanvasRay(int i, int j){
        int middleWidth = this.imageWidth/2;
        int middleHeight = this.imageHeight/2;
        Vector3D pointPosition = getBasicCenterCanvas();
        pointPosition.add(i-middleWidth, j-middleHeight);
        pointPosition = getRotatedVector3D(pointPosition); 
        return new Ray(this.position,pointPosition);
    }

    /**
     * Get the first vector in order to know the center of the canvas
     * @return Vector3D position of the center
     */
    private Vector3D getBasicCenterCanvas(){
        return new Vector3D(position.getX(),position.getY(),position.getZ()+SPACE_CANVAS_ORIGIN);
    }
    private Vector3D getBasicOrientation(){
        return new Vector3D(0,0,SPACE_CANVAS_ORIGIN);
    }
    /**
     * return the norm of a vector3Ds
     * @param v vector3D
     * @return norm of the vector v
     */
    private double getNorm(Vector3D v){
        return Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY() + v.getZ() * v.getZ());
    }

    /**
     * Get the cosinus of the angle between the basic ray and the wanted ray orientation
     * @return the cosinus of the angle
     */
    private double getCosAngle(){
        double normOrientation = getNorm(orientation);
        Vector3D base = getBasicCenterCanvas();
        double normBase = getNorm(base);
        int scalarProduct = (int)LightRay.scalarProduct(orientation,base);
        double cosAngle = scalarProduct/(normBase*normOrientation);
        return cosAngle;
    }

    /**
     * Set the angle of rotation from the basic orientation of the camera
     */
    private void setAngleRotation(){
        this.angle = Math.toDegrees(Math.acos(getCosAngle()));
    }

    /**
     * Set the rotation matrix given the angle and orientation of the camera
     * https://www.techno-science.net/glossaire-definition/Matrice-de-rotation.html
     */
    private void setRotationMatrix(){
        Vector3D base = getBasicOrientation();
        Vector3D normal = Ray.normal(base, orientation);
        double c = Math.cos(Math.toRadians(this.angle));
        double s = Math.sin(Math.toRadians(this.angle));
        double normNormal = getNorm(normal);
        if(normNormal !=0){
            double normalXUnit = normal.getX()/normNormal;
            double normalYUnit = normal.getY()/normNormal;
            double normalZUnit = normal.getZ()/normNormal;
            this.rotationMatrix[0][0] = (normalXUnit*normalXUnit)+((1-(normalXUnit*normalXUnit))*c);
            this.rotationMatrix[0][1] = (normalXUnit*normalYUnit)*(1-c)-(normalZUnit*s);
            this.rotationMatrix[0][2] = (normalXUnit*normalZUnit)*(1-c)+(normalYUnit*s);
            this.rotationMatrix[1][0] = (normalXUnit*normalYUnit)*(1-c)+(normalZUnit*s);
            this.rotationMatrix[1][1] = (normalYUnit*normalYUnit)+((1-(normalYUnit*normalYUnit))*c);
            this.rotationMatrix[1][2] = (normalYUnit*normalZUnit)*(1-c)-(normalXUnit*s);
            this.rotationMatrix[2][0] = (normalXUnit*normalZUnit)*(1-c)-(normalYUnit*s);
            this.rotationMatrix[2][1] = (normalYUnit*normalZUnit)*(1-c)+(normalXUnit*s);
            this.rotationMatrix[2][2] = (normalZUnit*normalZUnit)+((1-(normalZUnit*normalZUnit))*c);
        }
        else{
            double coeff =  base.getZ()*orientation.getZ();
            if (coeff<0){
                coeff = -1;
            }
            else{
                coeff = 1;
            }
            this.rotationMatrix[0][0] = 1;
            this.rotationMatrix[0][1] = 0;
            this.rotationMatrix[0][2] = 0;
            this.rotationMatrix[1][0] = 0;
            this.rotationMatrix[1][1] = 1;
            this.rotationMatrix[1][2] = 0;
            this.rotationMatrix[2][0] = 0;
            this.rotationMatrix[2][1] = 0;
            this.rotationMatrix[2][2] = coeff;
        }

    }

    /**
     * Function which print the current rotation matrix
     */
    public void printMatrix(){
        for(int i = 0; i < 3;i++){
            for (int j = 0;j < 3;j++){
                System.out.println(this.rotationMatrix[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    /**
     * Print the MainRays of the current canvas
     */
    public void printMainRays(){
        for(int i = 0;i<=imageHeight;i+=25){
            for(int j = 0;j<=imageWidth;j+=25){
                System.out.println(i+ " " + j);
                System.out.println(createCanvasRay(i,j));
            }
        }
    }

    /**
     * Create a rotated vector given the rotation matrix of the camera from the vector v
     * @param v the vector to be rotated
     * @return new Vector3D Rotated
     */
    public Vector3D getRotatedVector3D(Vector3D v) {
        double x = this.rotationMatrix[0][0]*v.getX()+this.rotationMatrix[0][1]*v.getY()+this.rotationMatrix[0][2]*v.getZ();
        double y = v.getX()*this.rotationMatrix[1][0]+v.getY()*this.rotationMatrix[1][1]+v.getZ()*this.rotationMatrix[1][2];
        double z = v.getX()*this.rotationMatrix[2][0]+v.getY()*this.rotationMatrix[2][1]+v.getZ()*this.rotationMatrix[2][2];
        return new Vector3D(x,y,z);
    }

    //https://www.scratchapixel.com/lessons/3d-basic-rendering/introduction-to-ray-tracing/implementing-the-raytracing-algorithm
    /**
     * Set the pixel of the pixels matrix according to the list of objects and the lightPosition
     * @param objList list of objects in the scene
     * @param lightPosition position of the light
     */
    public void sendRay(Collection <Volume> objList, Vector3D lightPosition){
        Ray rayon = null;
        double minDist = Double.POSITIVE_INFINITY;
        double dist;
        Vector3D impact = null;
        Vector3D nearestImpact = null;
        Triangle nearestTri = null;
        boolean isInShadow = true;
        LightRay lightRay = null;

        for(int j = 0; j<imageHeight;j++){
            for (int i = 0;i < imageWidth;i++){
                rayon = createCanvasRay(i, j);
                minDist = Double.POSITIVE_INFINITY;
                impact = null;
                nearestImpact = null;
                nearestTri = null;
                isInShadow = true;
                lightRay = null;

                // Check which object is touched first
                for(Volume obj : objList)
                {
                    for (Triangle tri : obj.getListTriangle())
                    {
                        impact = tri.calculPointIntersection(rayon);
                        if(impact != null)
                        {
                            dist = Ray.length(rayon.getOrigin(),impact);
                            if(dist < minDist)
                            {
                                nearestTri = tri;
                                nearestImpact = impact;
                                minDist = dist;
                            }
                        }
                    }

                }
                // Check if the point is illuminated
                if(nearestTri != null)
                {
                    isInShadow = false;
                    lightRay = new LightRay(nearestImpact,lightPosition);
                    for (Volume obj: objList)
                    {
                        for (Triangle tri : obj.getListTriangle())
                        {
                            impact = Triangle.intersectionTriangle(tri,lightRay);
                        }
                        if (impact != null){
                            isInShadow = true;
                            break;
                        }
                    }
                }
                // 
                if (!isInShadow){
                    //Ray normale = nearestTri.getNormal();
                    //this.pixels[i][j] = lightRay.reflectedLight(rayon,normale,nearestTri).toColor(); If the impact point was correct we could use reflected light to compute the true color
                    if (nearestTri.isOnEdge(nearestImpact)){
                        this.pixels[i][j] = nearestTri.getSegmentColor();
                    }
                    else{
                        this.pixels[i][j] = nearestTri.getFaceColor();
                    }
                }
                else{
                    this.pixels[i][j] = new PixelColor(0,0,0);
                }
            }
        }
    }
    /**
     * Generate an image of the scene named result.png in the ./image directory
     */
    public void createImage(Collection <Volume> objList) {
        this.sendRay(objList, this.lightPosition);
        ImageCreator.createImage(imageHeight, imageWidth, pixels,"image/result.png");
    }
}