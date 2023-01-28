package Scene;

import java.util.ArrayList;

import Scene.camera.*;
import object3D.Volume;
import Shade.light.Light;

public class Scene{
    private Camera cam;
    private Light lum;
    private ArrayList<Volume> listevol;

    public Scene(){
        this.cam = null;
        this.lum = null;
        this.listevol = new ArrayList<Volume>();
    }

    public Camera getCamera() {
        return this.cam;
    }

    public void setCamera(Camera camera) {
        this.cam = camera;
    }

    public Light getLight() {
        return this.lum;
    }

    public void setLight(Light lumiere) {
        this.lum = lumiere;
    }

    public ArrayList<Volume> getListVolume() {
        return this.listevol;
    }

    public void setListVolume(ArrayList<Volume> listevolume) {
        this.listevol = listevolume;
    }

    public void addObject2Scene(Volume vol){
        this.listevol.add(vol);
    }
    
    public void updateImageScene(){
        if (cam!= null){
            cam.createImage(this.listevol);
        }
    }
    @Override
    public String toString() {
        return "{" +
            " cam='" + getCamera() + "'" +
            ", lum='" + getLight() + "'" +
            ", listevol='" + getListVolume() + "'" +
            "}";
    }
}

