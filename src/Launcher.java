import fenetre.Window;
import java.io.IOException;
public class Launcher {
    public static void main(String[] args) {
        try {
            Window window = new Window();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}