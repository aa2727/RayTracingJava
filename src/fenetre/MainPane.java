package fenetre;

import fenetre.Listener.*;
import Scene.Scene;
import javax.swing.*;
import java.awt.*; 
import java.io.IOException;



public class MainPane extends JPanel{

    private JMenuBar menu;
    private JMenu fileMenu;
    private JMenu help;
    private JMenuItem shortCuts;
    private JMenuItem open;
    private AfficheImage image;
    private Scene scene;
    private AfficheImage imagePanel;
    private GridBagConstraints gbc;

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
        //Creation of the menu, added to the panel later
        this.createMenu();
        //Just connecting the different class related to movements and menu options
        this.addAllListeners();
        //Instantiation of the panel containing the image 
        this.setImage();
        // Setting the grids and adding the elements concerned in it
        this.displayPanel();
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

    /**
     * Create the menu with the two buttons aide and ouvrir
     */
    private void createMenu() {

        this.menu = new JMenuBar();
        this.fileMenu = new JMenu("Fichier");
        this.help = new JMenu("Aide");
        this.shortCuts = new JMenuItem("Raccourcis");
        this.open = new JMenuItem("Ouvrir");

        this.fileMenu.add(this.open);
        this.help.add(this.shortCuts);
        this.menu.add(this.fileMenu);
        this.menu.add(this.help);   
    }

    private void addAllListeners() {

        this.addKeyListener(new KeyListenerClass(this));
        this.addMouseWheelListener(new MouseWheelListenerClass(this));
        this.shortCuts.addActionListener(new AideListener());
        this.open.addActionListener(new FileOpener(this.open,this));
    }

    private void setImage() throws IOException {
        this.imagePanel = new AfficheImage();
        this.image = imagePanel;
    }

    private void displayPanel() {
        this.gbc = new GridBagConstraints();
        this.gbc.gridx=0;
        this.gbc.gridy=0;
        this.add(this.menu, gbc);
        this.gbc.gridx=0;
        this.gbc.gridy=1;
        this.add(this.imagePanel, gbc);
    }
}


