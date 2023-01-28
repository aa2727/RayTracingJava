package fenetre;
import Scene.Scene;
import Parser.*;

import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.swing.JFileChooser;


class FileOpener implements ActionListener{
    
	private MainPane panel;

    public FileOpener(JMenuItem open, MainPane panel){
		this.panel = panel;
    }

	/**
	 * Modify actionPerformed method, open a file chooser and send the absolute path of the file to scanFile() to create a scene then resfresh the display
	 * @param e ActionEvent
	 */
    public void actionPerformed(ActionEvent e){

        JFileChooser choix = new JFileChooser("./pov"); // Allow us to open the file chooser directly in the ./pov directory
		int res = choix.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
      		File file = choix.getSelectedFile();
			Parser parser = new Parser(file.getAbsolutePath());
			Scene scene = parser.scanFile();
			panel.setScene(scene);
			scene.updateImageScene();
			panel.refreshImage();
			
    	}		
    }

}