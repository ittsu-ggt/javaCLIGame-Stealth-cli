import master.*;
import java.io.IOException;
import java.nio.file.Path;

public class Model {
    public static void main(String[] args) throws InterruptedException, IOException {
        Game master = new Game();
        master.run();
        return;
    }
}
