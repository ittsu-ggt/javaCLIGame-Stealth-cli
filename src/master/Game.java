package master;
import java.io.IOException;
import consolegui.*;
import game.*;

public class Game  {
    public CDisplay view ;
    public KeyBoardService key;
    public CDebug debug;

    public void run() throws InterruptedException,IOException {
        this.view = new CDisplay(100,50,1,8,true,true);

        this.key= new KeyBoardService(true);
        this.debug = new CDebug(this.view,0,0,10);
        GameScene master = new GameScene(this);
        master.run();
    }
}
