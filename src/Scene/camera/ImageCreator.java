package Scene.camera;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import Shade.light.PixelColor;

public class ImageCreator {

    /**
     * Create a png format image given the pixels tab of Pixel color
     * @param height height of the image
     * @param width width of the image
     * @param pixels
     * @param name name of the png image
     * @return 0 if the image has been well writen -1 else
     */
    public static int createImage(int height, int width, PixelColor[][] pixels,String name) {

        BufferedImage img = new BufferedImage(height,width,BufferedImage.TYPE_INT_RGB); // Create a bufferedImage to stock data
        File file = new File(name); // Create the file to be writen
        PixelColor color = new PixelColor();
        // Set all the pixel from the matrix pixels
        for(int i = 0; i < height;i++){
            for(int j = 0;j < height;j++){
                color = pixels[i][j];
                img.setRGB(i, j, color.getRGB());
            }
        }
        try {
            ImageIO.write(img, "png", file); // Write the file
        }
        catch (IOException e) {
            System.out.println(e);
            return -1;
        }
        return 0;
    }
}
