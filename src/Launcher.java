import fenetre.Window;
import java.io.IOException;
public class Launcher {
    public static void main(String[] args) {
        try {
            new Window();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}