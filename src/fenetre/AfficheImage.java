package fenetre;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AfficheImage extends JPanel{


    private JLabel label;
    
    //This constructor also put a default image ; which appears when we launch the application.
    public AfficheImage() throws IOException{
        super();
        int width = 400;
        int height = 400;
        setPreferredSize(new Dimension(width,height));
        this.label = new JLabel();
        this.setDefaultImage();
        this.add(label);
    }
    
    /**
     * Take the path of the image we need to open and define it as the icon 
     */
    public void updateImage() throws IOException{
        File file = new File("image/result.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        label.setIcon(imageIcon);              //Define imageIcon as the new Icon
    }

    private void setDefaultImage() throws IOException {
        File file = new File("image/bush.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        label.setIcon(imageIcon);     
    }

}

//Code taken here and modified according to our needing https://www.delftstack.com/fr/h