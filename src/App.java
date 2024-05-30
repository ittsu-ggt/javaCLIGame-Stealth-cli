import master.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        Game master = new Game();
        master.run();
    }
}
