package fenetre.Listener;

import Scene.Scene;
import fenetre.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseWheelListenerClass implements MouseWheelListener{
    

  private MainPane panel;


  public MouseWheelListenerClass(MainPane panel){
      this.panel = panel;
  }

  
  /**
   * Listen if the user is scrolling down/up, according to that the camera goes forward/backward (zoom/dezoom)
   * @param evt MouseWheelEvent
   */
  public void mouseWheelMoved(MouseWheelEvent evt){  


    int sens = evt.getWheelRotation();
    Scene scene = this.panel.getScene();


    if (sens<0){                                                      //scroll up
      //JOptionPane.showMessageDialog(null, "Vous avez avancé");      //Used as a test and now help to remember what part make the camera go forward/backward
      Moves.forward(scene.getCamera());
      scene.updateImageScene();                                       // Ask to the camera to create a new image according to the actual scene
      panel.refreshImage();                                           // Change the panel's image
    }


    else{                                                             //scroll down
      //JOptionPane.showMessageDialog(null, "Vous avez reculé");        Do not delete/erase
      Moves.backward(scene.getCamera());
      scene.updateImageScene();
      panel.refreshImage();
    }
    
  }
}
