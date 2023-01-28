package Shade.materials;

import Shade.light.PixelColor;

public abstract class Material {
    protected PixelColor faceColor;
    protected PixelColor segmentColor;
    protected float ambientCompo;
    protected float diffuseCompo;
    protected float specularCompo;
    protected int alpha;
    /**
     * Initialize Material instance given the three components of the material
     * @param ambientCompo ambient light component
     * @param diffuseCompo diffuse light component
     * @param specularCompo specular light component
     * @param alpha shiness of the object
     * @requires ambientCompo != null
     * @requires diffuseCompo != null
     * @requires specularCompo != null
     * @requires 0 <= ambientCompo <= 1
     * @requires 0 <= diffuseCompo <= 1
     * @requires 0 <= specularCompo <= 1
     * @ensures this.ambientCompo = ambientCompo
     * @ensures this.diffuseCompo = diffuseCompo
     * @ensures this.specularCompo = specularCompo
     * @ensures this.alpha = alpha
     */
    public Material(float ambientCompo,float diffuseCompo,float specularCompo,int alpha,PixelColor faceColor,PixelColor segmentColor){
        this.ambientCompo = ambientCompo;
        this.diffuseCompo = diffuseCompo;
        this.specularCompo = specularCompo;
        this.faceColor = faceColor;
        this.segmentColor = segmentColor;
        this.alpha = alpha;
    }
    /**
     * Initialize Material instance with (1,1,1,1) components.
     * @ensures this.ambientCompo = 1
     * @ensures this.diffuseCompo = 1
     * @ensures this.specularCompo = 1
     * @ensures this.alpha = 1
     */
    public Material(){
        this(1,1,1,1,new PixelColor(),new PixelColor(0,0, 250));
    }

    /**
     * Get the ambient component.
     * @return ambientCompo float
     */
    public float getAmbientCompo(){
        return this.ambientCompo;
    }
    /**
     * Get the diffuse component.
     * @return diffuseCompo float
     */
    public float getDiffuseCompo(){
        return this.diffuseCompo;
    }
    /**
     * Get the specular component.
     * @return specularCompo float
     */
    public float getSpecularCompo(){
        return this.specularCompo;
    }
    /**
     * Get the Alpha component
     * @return alpha int
     */
    public float getAlpha() {
        return this.alpha;
    }

    public PixelColor getFaceColor() {
        return this.faceColor;        
    }

    public PixelColor getSegmentColor(){
        return this.segmentColor;
    }
}
