package Shade.light;
import java.awt.Color; // Use of the Color class in order to use the getRgb() method
public class PixelColor extends Color{

    /**
     * Initialize the color with given values
     * @param red red color intensity
     * @param green green color intensity
     * @param blue blue color intensity
     * @requires 0 <= red <= 255 
     * @requires 0 <= green <= 255
     * @requires 0 <= blue <= 255 
     * @ensures this.red = red
     * @ensures this.green = green
     * @ensures this.blue = blue
     */
    public PixelColor(int red, int green, int blue){
        super(red, green, blue);
    }

    /**
     * Initialize color with white(255,255,255) color values
     * @ensures this.red = 255
     * @ensures this.green = 255
     * @ensures this.red = 255
     */
    public PixelColor(){
        this(255,255,255);
    }
    /**
     * Return value with the limit of the color capacity
     * @param val
     * @return val if val is between 0 and 255 else return 0 or 255
     */
    public static int limitColor(int val) {
        if (val > 255){
            return 255;
        }
        if (val < 0){
            return 0;
        }
        return val;
    }
    /**
     * Initialize color from another color multiplied by a scalar
     * @param scalar
     * @param color
     */
    public PixelColor(float scalar,PixelColor color){
        this(limitColor((int)(scalar*color.getRed())),limitColor((int)(scalar*color.getGreen())),limitColor((int)(scalar*color.getBlue())));
    }
    @Override
    public String toString(){
        return "("+super.getRed()+", "+super.getGreen()+", "+super.getBlue()+")";
    }
    
}
