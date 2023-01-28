package fenetre.Listener;

import Scene.Scene;
import fenetre.*;

import javax.swing.*;
import java.awt.event.*;

public class KeyListenerClass implements KeyListener{

  private MainPane panel;

  public KeyListenerClass(MainPane panel){
      this.panel = panel;
  }


  //Even if we don't use keyTyped and keyReleased we have to declare them because KeyListener is an interface
  public void keyTyped(KeyEvent evt){}
  public void keyReleased(KeyEvent evt){}


  /**
   * Detect what key is pressed and depending on that, call a method modifying the location of the camera
   * @param evt KeyEvent
   */
  public void keyPressed(KeyEvent evt){     

    Scene scene = this.panel.getScene();

    if(evt.getKeyCode() ==KeyEvent.VK_Z){                       // if the key pressed is Z, then it calls Moves.up()
      Moves.up(scene.getCamera());
      scene.updateImageScene();                                 // Ask to the camera to create a new image according to the actual scene
      panel.refreshImage();                                     // Change the panel's image
    }

    if(evt.getKeyCode() ==KeyEvent.VK_S){                       // if the key pressed is S, then it calls Moves.down()
      Moves.down(scene.getCamera());
      scene.updateImageScene();
      panel.refreshImage();
    }

    if(evt.getKeyCode() ==KeyEvent.VK_Q){
      Moves.left(scene.getCamera());
      scene.updateImageScene();
      panel.refreshImage();
    }

    if(evt.getKeyCode() ==KeyEvent.VK_D){
      Moves.right(scene.getCamera());
      scene.updateImageScene();
      panel.refreshImage();
    }

    if(evt.getKeyCode() ==KeyEvent.VK_A){
      //JOptionPane.showMessageDialog(null, "Vous avez pivotés vers la gauche");      Do not erase
      Moves.roundRight(scene.getCamera());
      scene.updateImageScene();
      panel.refreshImage();
    
    }

    if(evt.getKeyCode() ==KeyEvent.VK_E){
      //JOptionPane.showMessageDialog(null, "Vous avez pivotés vers la droite");      Do not erase
      Moves.roundLeft(scene.getCamera());
      scene.updateImageScene();
      panel.refreshImage();
      
    }
    


  }

}