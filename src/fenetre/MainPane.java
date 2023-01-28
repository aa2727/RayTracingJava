package fenetre;

import fenetre.Listener.*;
import Scene.Scene;
import javax.swing.*;
import java.awt.*; 
import java.io.IOException;



public class MainPane extends JPanel{

    private JMenuBar menu;
    private JMenu file;
    private JMenu help;
    private JMenuItem shortCuts;
    private JMenuItem open;
    private AfficheImage image;
    private Scene scene;

    public Scene getScene(){
        return this.scene;
    }

    public void setScene(Scene s){
        this.scene = s;
    }

    public MainPane() throws IOException {

        super();
        //The gridBagLayout is there to prevent the menu going on the side when the window is extended by user
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //Creation of the menu, added to the panel later
        this.menu=new JMenuBar();
        this.file=new JMenu("Fichier");
        this.help=new JMenu("Aide");
        this.shortCuts=new JMenuItem("Raccourcis");
        this.open=new JMenuItem("Ouvrir");

        this.file.add(this.open);
        this.help.add(this.shortCuts);
        this.menu.add(this.file);
        this.menu.add(this.help);


        //Just connecting the different class related to movements and menu options
        this.addKeyListener(new KeyListenerClass(this));
        this.addMouseWheelListener(new MouseWheelListenerClass(this));
        this.shortCuts.addActionListener(new AideListener(this.shortCuts));
        this.open.addActionListener(new FileOpener(this.open,this));


        //Instantiation of the panel containing the image 
        AfficheImage imagePanel = new AfficheImage();
        this.image = imagePanel;
        // Setting the grids and adding the elements concerned in it
        gbc.gridx=0;
        gbc.gridy=0;
        this.add(this.menu, gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        this.add(imagePanel, gbc);
        //Make the panel focusable, key/mouseWheel listener works because of that
        setFocusable(true);     
    }

    /** 
     * Refresh the display of the image 
     */
    public void refreshImage(){         
        try{
            image.updateImage();
        }
        catch(IOException e){
            System.out.println("No image found");
        }
        
    }

}


