package game;

import master.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameScene {
    Game master;
    Player player;
    Map map;
    ArrayList<Enemy> enemies;

    public GameScene(Game master) throws InterruptedException, IOException {
        this.master = master;
        this.player = new Player(this, 5, 5, true);
        this.map = new Map(this);
        this.map.SetVisible(true);
        this.enemies = new ArrayList<Enemy>();

        this.enemies.add(new Enemy(this, 19, 10, true));
        this.enemies.add(new Enemy(this, 16, 33, true));
        this.enemies.add(new Enemy(this, 34, 96, true));
        this.enemies.add(new Enemy(this, 81, 53, true));
        this.enemies.add(new Enemy(this, 104, 15, true));
        this.enemies.add(new Enemy(this, 133, 26, true));
        this.enemies.add(new Enemy(this, 121, 52, true));
        this.enemies.add(new Enemy(this, 109, 72, true));
        this.enemies.add(new Enemy(this, 109, 95, true));
        this.enemies.add(new Enemy(this, 81, 90, true));
    }

    public void run() throws InterruptedException, IOException {
        while (true) {
            player.Update();
            for (Enemy enemy : enemies) {
                enemy.Update();
            }
            master.view.Update();
            map.ChangeDrawingOrder(0);
            Thread.sleep(100);
        }
    }
}
