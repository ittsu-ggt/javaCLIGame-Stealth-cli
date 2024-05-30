package game;

import master.*;
import java.io.IOException;

public class GameScene {
    Game master;
    Player player;
    Map map;

    public GameScene(Game master) throws InterruptedException, IOException {
        this.master = master;
        this.player = new Player(this, 5, 5, true);
        this.map = new Map(this);
        this.map.SetVisible(true);
    }

    public void run() throws InterruptedException, IOException {
        while (true) {
            player.Update();
            master.view.Update();
            Thread.sleep(100);
        }
    }
}
