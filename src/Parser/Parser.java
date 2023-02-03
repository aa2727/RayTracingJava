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

    // If no file is given we take Default.txt as a demo file
    public Parser() {
        this.filePath = "Default.txt";
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

            ArrayList<String> lignes = new ArrayList<String>();
            while (scan.hasNext()) {
                String tmp = scan.nextLine();
                lignes.add(tmp);
            }
            if(!lignes.get(0).equals("{") || !lignes.get(lignes.size() -1).equals("}")){
                scan.close();
                System.err.println("29");
                throw new FileException("Le format du fichier n'est pas bon: les parametres doivent être entre accolades");
            }
            System.out.println(lignes.get(lignes.size()-1));
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
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(FileException e){
            e.printStackTrace();
        }
        return scene;
    }

    public Scene listeString2Scene(ArrayList<String[]> lineParam) throws FileException {
            Scene param = new Scene();
            for (int i = 0; i < lineParam.size(); i++) {
                String type = lineParam.get(i)[0];
                switch (type) {
                    case "Cube":
                        if(lineParam.get(i)[1] !=null){
                            Cube cubique = new Cube(Integer.parseInt(lineParam.get(i)[1]),
                            Integer.parseInt(lineParam.get(i)[2]), Integer.parseInt(lineParam.get(i)[3]),Integer.parseInt(lineParam.get(i)[4]));
                            param.addObject2Scene(cubique);
                        }
                        else{
                            Cube cubique = new Cube();
                            param.addObject2Scene(cubique);
                        }
                        break;
                    case "Parallelepiped":
                        Parallelepiped para = new Parallelepiped(Integer.parseInt(lineParam.get(i)[1]),
                                Integer.parseInt(lineParam.get(i)[2]), Integer.parseInt(lineParam.get(i)[3]),Integer.parseInt(lineParam.get(i)[4]),Integer.parseInt(lineParam.get(i)[5]),Integer.parseInt(lineParam.get(i)[6]));
                        param.addObject2Scene(para);
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
                        param.setLight(lux);
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
                        param.setCamera(cam);
                        break;

                    default:
                        System.out.println("Ligne invalide");
                        throw new FileException("Une des lignes du fichier est invalide");
                }
            }
            return param;
        } 
}