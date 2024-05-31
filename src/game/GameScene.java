package game;

import master.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;


public class GameScene {
    Game master;
    Player player;
    Map map;
    ArrayList<Enemy> enemies;
    ArrayList<Item> items;;
    StatusLog statuslog;
    int hp = 3;
    String time;
    int score = 0;

    /**
     * ゲームの管理モジュール
     * 
     * @param master アプリケーションの管理モジュール
     * @throws InterruptedException
     * @throws IOException
     */
    public GameScene(Game master) throws InterruptedException, IOException {
        this.master = master;
        this.player = new Player(this, 5, 5, true);
        this.map = new Map(this);
        this.statuslog = new StatusLog(this);
        this.map.SetVisible(true);
        this.enemies = new ArrayList<Enemy>();
        this.items = new ArrayList<Item>();

        this.enemies.add(new Enemy(this, 19, 10, true));
        this.enemies.add(new Enemy(this, 16, 33, true));
        this.enemies.add(new Enemy(this, 34, 96, true));
        this.enemies.add(new Enemy(this, 81, 53, true));
        this.enemies.add(new Enemy(this, 104, 15, true));
        this.enemies.add(new Enemy(this, 135, 26, true));
        this.enemies.add(new Enemy(this, 121, 52, true));
        this.enemies.add(new Enemy(this, 109, 72, true));
        this.enemies.add(new Enemy(this, 109, 95, true));
        this.enemies.add(new Enemy(this, 81, 90, true));

        for (int i = 0; i < 30; i++) {
            this.items.add(new Item(this, true));
        }
    }




    /**
     * シーンの実行
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void run() throws InterruptedException, IOException {
        Timestamp gamestart = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

        while (true) {
            player.Update();
            for (Enemy enemy : enemies) {
                enemy.Update();
            }
            ArrayList<Item> itemscopy = new ArrayList<Item>();
            for (Item item : items) {
                if (!item.Update()) {
                    itemscopy.add(item);
                }
            }
            items = itemscopy;

            map.ChangeDrawingOrder(0);
            var now = new Timestamp(System.currentTimeMillis());
            time = sdf.format(now.getTime() - gamestart.getTime());
            statuslog.refresh(hp, time);
            master.debug.Update();
            master.view.Update();

            Thread.sleep(100);
        }
    }
}
