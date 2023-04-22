package fenetre;

import javax.swing.JFrame;
import java.awt.*;
import java.io.IOException;


public class Window extends JFrame{

    //The main Frame
    public Window() throws IOException {
        super("Lancer de rayons 3D");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);
        this.setContentPane(new MainPane());
        this.setVisible(true);
    }
}
