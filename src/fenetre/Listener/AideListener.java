package fenetre.Listener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AideListener implements ActionListener{
    private JMenuItem shortCuts;

    public AideListener(JMenuItem shortCuts){
        this.shortCuts=shortCuts;
    }

    String helpText = " Touches pour se déplacer : \n Monter ( suivant l\'axe y ) : z \n Descendre ( suivant l\'axe y ) : s \n Aller vers la droite : d \n Aller vers la gauche : q \n Se rapprocher de l\'objet : Bouton central de la souris vers l'avant \n S\'éloigner de l\'objet : Bouton central de la souris vers l\'arrière \n Pivoter autour de l\'objet par la droite : E \n Pivoter autour de l\'objet par la gauche : A";
    
    
    /**
     * Modify actionPerformed and open a message dialog displaying the movement keyboards
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(null, helpText);
        
    }
}