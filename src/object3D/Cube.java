package object3D;

public class Cube extends Parallelepiped {

    /**
     * Créer un cube de côté 1 et en position de départ (1,1,1)
     */
    public Cube() {
        super(1, 1, 1, 1, 1, 1);
    }

    /**
     * Créer un cube de côté size et en position (x,y,z)
     * @param x position x
     * @param y position y
     * @param z position z
     * @param size côté du cube
     */
    public Cube(int x, int y, int z, int size) {
        super(size, size, size, x, y, z);
    }

    /**
     * Main function
     * @param args
     */
    public static void main(String[] args) {
        Cube p = new Cube();
        System.out.println("Objectif triangle = " + p.getListTriangle().size() + " / 12");
        System.out.println(p.toString());
    }
}