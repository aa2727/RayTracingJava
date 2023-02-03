package Parser;

import java.io.*;
import java.util.Scanner;
import Scene.*;
import Scene.camera.Camera;
import Shade.light.Light;
import java.util.ArrayList;
import object3D.*;

public class Parser {
    private String filePath;

    // The parser is created and linked to a file
    public Parser(String filePath) {
        this.filePath = filePath;
    }

    // If no file is given we take Test.scene as a demo file
    public Parser() {
        this.filePath = "Test.scene";
    }

    /**
     * Read the file which its path is stored in this.filePath
     * @return a Scene Object described in the file or null if the file is not correct
     */
    public Scene scanFile() {
        Scene scene = null;
        try {
            File file = new File(this.filePath);
            Scanner scan = new Scanner(file);
            ArrayList<String> lignes = getLinesFromFile(scan);
            scan.close();
            if(!lignes.get(0).equals("{") || !lignes.get(lignes.size() -1).equals("}")){
                throw new FileException("Le format du fichier n'est pas bon : les paramètres doivent être entre accolades");
            }

            lignes.remove(lignes.get(0));
            lignes.remove(lignes.get(lignes.size() - 1));

            ArrayList<String[]> lineParam = new ArrayList<String[]>();
            for (int i = 0; i < lignes.size(); i++) {
                lineParam.add((lignes.get(i).split("/")));
            }
            try{
                scene = listeString2Scene(lineParam);
            } catch(FileException e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(FileException e){
            e.printStackTrace();
        }
        return scene;
    }

    /**
     * Function that returns a list of lines from the file in the Scanner scan
     * @param scan Scanner with the file to study
     * @return List of String line
     */
    public ArrayList<String> getLinesFromFile(Scanner scan){
        ArrayList<String> lignes = new ArrayList<String>();
        while (scan.hasNext()) {
            String tmp = scan.nextLine();
            lignes.add(tmp);
        }
        return lignes;
    }

    /**
     * Function which add a cube to the given scene with the given parameters in line
     * @param scene Scene which we want to add the cube
     * @param line List of String in which the parameters are given
     */
    public void addCube(Scene scene, String[]line) {
        if(line[1] !=null){
            Cube cubique = new Cube(Integer.parseInt(line[1]),
            Integer.parseInt(line[2]), Integer.parseInt(line[3]),Integer.parseInt(line[4]));
            scene.addObject2Scene(cubique);
        }
        else{
            Cube cubique = new Cube();
            scene.addObject2Scene(cubique);
        }
    }

    public void addParallelepiped(Scene scene, String[]line) {
        Parallelepiped para = new Parallelepiped(Integer.parseInt(line[1]),
        Integer.parseInt(line[2]), Integer.parseInt(line[3]),Integer.parseInt(line[4]),
        Integer.parseInt(line[5]),Integer.parseInt(line[6]));
        scene.addObject2Scene(para);
    }

    public Scene listeString2Scene(ArrayList<String[]> lineParam) throws FileException {
            Scene scene = new Scene();
            for (int i = 0; i < lineParam.size(); i++) {
                String type = lineParam.get(i)[0];
                switch (type) {
                    case "Cube":
                        addCube(scene, lineParam.get(i));
                        break;
                    case "Parallelepiped":
                        addParallelepiped(scene, lineParam.get(i));
                        break;
                    case "Light":
                        ArrayList<String> container = new ArrayList<String>();
                        for(int j=1; j < 4;j++){
                            String tmp = lineParam.get(i)[j];
                            tmp= tmp.substring(1, tmp.length() - 1);
                            String[] ltmp =tmp.split(",");
                            for(int k=0;k<ltmp.length;k++){
                                container.add(ltmp[k]);
                            }
                        }
                        Shade.light.PixelColor c1 = new Shade.light.PixelColor(Integer.parseInt(container.get(0)),Integer.parseInt(container.get(1)),Integer.parseInt(container.get(2)));
                        Shade.light.PixelColor c2 = new Shade.light.PixelColor(Integer.parseInt(container.get(3)),Integer.parseInt(container.get(4)),Integer.parseInt(container.get(5)));
                        Shade.light.PixelColor c3 = new Shade.light.PixelColor(Integer.parseInt(container.get(6)),Integer.parseInt(container.get(7)),Integer.parseInt(container.get(8)));
                        Light lux = new Shade.light.Light(c1,c2,c3);
                        scene.setLight(lux);
                        break;

                    case "Camera":
                    ArrayList<String> container2 = new ArrayList<String>();
                        for(int j=1; j < 4;j++){
                            String tmp = lineParam.get(i)[j];
                            tmp= tmp.substring(1, tmp.length() - 1);
                            String[] ltmp =tmp.split(",");
                            for(int k=0;k<ltmp.length;k++){
                                container2.add(ltmp[k]);
                            }
                        }
                        ray.Vector3D v1 = new ray.Vector3D(Integer.parseInt(container2.get(0)),Integer.parseInt(container2.get(1)),Integer.parseInt(container2.get(2)));
                        ray.Vector3D v2 = new ray.Vector3D(Integer.parseInt(container2.get(3)),Integer.parseInt(container2.get(4)),Integer.parseInt(container2.get(5)));
                        ray.Vector3D v3 = new ray.Vector3D(Integer.parseInt(container2.get(6)),Integer.parseInt(container2.get(7)),Integer.parseInt(container2.get(8)));
                        Camera cam = new Camera(v1,v2,v3,Integer.parseInt(lineParam.get(i)[4]),Integer.parseInt(lineParam.get(i)[5]));
                        scene.setCamera(cam);
                        break;

                    default:
                        System.out.println("Ligne invalide");
                        throw new FileException("Une des lignes du fichier est invalide");
                }
            }
            return scene;
        } 
}