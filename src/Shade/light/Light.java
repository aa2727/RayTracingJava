package Shade.light;


public class Light{
    private PixelColor ambient;
    private PixelColor diffuse;
    private PixelColor specular;
    /**
     * Initialize a Light instance with the given PixelColor
     * @param ambient ambient color
     * @param diffuse diffuse color
     * @param specular specular color
     * @ensures this.ambient = ambient
     * @ensures this.diffuse = diffuse
     * @ensures this.specular = specular
     */
    public Light(PixelColor ambient,PixelColor diffuse,PixelColor specular){
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
    }

    /**
     * Initialize a Light instance with (white,white,white)
     */
    public Light(){
        this(new PixelColor(),new PixelColor(),new PixelColor());
    }
    /**
     * Get the ambient component.
     * @return ambient PixelColor
     */
    public PixelColor getAmbient(){
        return this.ambient;
    }
    /**
     * Get the diffuse component.
     * @return diffuse PixelColor
     */
    public PixelColor getDiffuse(){
        return this.diffuse;
    }
    /**
     * Get the specular component.
     * @return specular PixelColor
     */
    public PixelColor getSpecular(){
        return this.specular;
    }
    /**
    * Convert the light into its PixelColor equivalent 
    * @return
    */
    public PixelColor toColor(){
        int sommeRed = ambient.getRed() + diffuse.getRed() + specular.getRed();
        int sommeBlue = ambient.getBlue() + diffuse.getBlue() + specular.getBlue();
        int sommeGreen = ambient.getGreen() + diffuse.getGreen() + specular.getGreen();
        if (sommeBlue > 255){
            sommeBlue = 255;
        }
        if (sommeGreen > 255){
            sommeGreen = 255;
        }
        if (sommeRed > 255){
            sommeRed = 255;
        }
        return new PixelColor(sommeRed,sommeGreen,sommeBlue);
    }

    @Override
    public String toString(){
        return "Light with :\n" +this.ambient +" Ambient Color\n"+this.diffuse+" Diffuse Color\n"+this.specular+" Specular Color";
    }
}
