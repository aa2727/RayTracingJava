package Shade.light;
import java.lang.Math;
import Shade.materials.*;
import ray.*;

public class LightRay extends Ray{

    private Light sourceLight;

    public LightRay(Vector3D origin, Vector3D direction,Light sourceLight){
        super(origin, direction);
        this.sourceLight = sourceLight;
    }
    public LightRay(Vector3D origin,Vector3D direction){
        super(origin, direction);
        this.sourceLight = new Light();
    }

    public LightRay(){
        super();
        this.sourceLight = new Light();
    }
    /**
     * Convert a Ray into Vector3D
     * @param r the Ray to be converted into Vector3D
     * @return Vector3D
     */
    public static Vector3D getVector(Ray r){
        return new Vector3D((r.getDirection().getX()-r.getOrigin().getX()),(r.getDirection().getY()-r.getOrigin().getY()),(r.getDirection().getZ() - r.getOrigin().getZ()));
    }
    public static Vector3D getVector(Vector3D origin, Vector3D direction){
        return new Vector3D(origin.getX()-direction.getX(),origin.getY()-direction.getY(),origin.getZ()-direction.getZ());
    }
    public Vector3D getVector(){
        return getVector(super.getOrigin(),super.getDirection());
    }
    /**
     * Calculate the scalar product between two Rays
     * @param firstRay
     * @param secondRay
     * @return the scalar product
     */
    public static double scalarProduct(Ray firstRay,Ray secondRay){
        Vector3D firstVector3d = getVector(firstRay);
        Vector3D secondVector3d = getVector(secondRay);
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
    /**
     * Calculate the vector from the soustraction between the first and the second vector
     * @param firstVector3D
     * @param secondVector3D
     * @require firstVector3D != null
     * @require secondVector3D != null
     * @return a Vector3D from the soustraction
     */
    public static Vector3D soustract(Vector3D firstVector3D,Vector3D secondVector3D){
        return new Vector3D(firstVector3D.getX()-secondVector3D.getX(),firstVector3D.getY()-secondVector3D.getY(),firstVector3D.getZ()-secondVector3D.getZ());
    }
    /**
     * Calculate the new vector multiplicated by a scalar
     * @param k scalar 
     * @param firstVect vector to multiply
     * @require firstVector != null
     * @return a Vector3D instance from the product between k and firstVect
     */
    public static Vector3D productVectorScalar(double k,Vector3D firstVect){
        return new Vector3D((int)k*firstVect.getX(),(int)k*firstVect.getY(),(int)k*firstVect.getZ());
    }
    /**
     * Vector3D refracted from the lightRay 
     * @param lightRay lightRay emitted
     * @param normal normal from the point where the light ray hits
     * @return Refracted vector3D
     */
    public static Vector3D refraction(Vector3D lightRay,Vector3D normal){
        double k = 2*(scalarProduct(lightRay,normal));
        return soustract(productVectorScalar(k, normal),lightRay);
    }
    /**
     * Find the new light reflected from a point of a object given the normale Ray
     * @param fromView Rayon qui a pour origine le point de réflexion et destination la vue
     * @param normale Normal from a point to a surface of an object
     * @param form Object3D implementing Material
     * @return Light emitted from the point
     */
    public Light reflectedLight(Ray fromView,Ray normale,Material form){
        PixelColor ambient = new PixelColor(form.getAmbientCompo(),this.sourceLight.getAmbient());
        PixelColor diffuse = new PixelColor((float)(form.getDiffuseCompo()*scalarProduct(normale, fromView)),this.sourceLight.getDiffuse());
        Vector3D refractedVect = refraction(getVector(super.getOrigin(), super.getDirection()),getVector(normale));
        float intensity = (float)(form.getSpecularCompo()*Math.pow(scalarProduct(refractedVect, getVector(fromView)),form.getAlpha()));
        PixelColor specular = new PixelColor(intensity,this.sourceLight.getSpecular());
        return new Light(ambient,diffuse,specular);
        
    }
}