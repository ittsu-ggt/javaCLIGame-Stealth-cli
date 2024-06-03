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
        this.player = new Player(this, 81, 53, true);
        this.map = new Map(this);
        this.statuslog = new StatusLog(this);
        this.map.SetVisible(true);
        this.enemies = new ArrayList<Enemy>();
        this.items = new ArrayList<Item>();

        this.enemies.add(new Enemy(this, 21, 10,15,100, true,false)); //左上1
        this.enemies.add(new Enemy(this, 16, 33,8,100, true,false)); // 左上2
        this.enemies.add(new Enemy(this, 34, 96,4,50, true,false)); //左下1
        this.enemies.add(new Enemy(this, 34, 96,4,50, true,false)); //左下2
        this.enemies.add(new Enemy(this, 34, 96,4,50, true,false)); //左下2
        this.enemies.add(new Enemy(this, 10, 52,20,100, true,false));// 中央左1
        this.enemies.add(new Enemy(this, 79, 90,100,200, true,false)); //中央通路1
        this.enemies.add(new Enemy(this, 81, 53,20,100, true,false)); //中央右1
        this.enemies.add(new Enemy(this, 104, 15,4,50, true,false)); //右上1
        this.enemies.add(new Enemy(this, 104, 15,4,50, true,false)); //右上2
        this.enemies.add(new Enemy(this, 133, 28,4,50, true,false)); //右上3
        this.enemies.add(new Enemy(this, 111, 72,8,100, true,false)); // 右下1
        this.enemies.add(new Enemy(this, 109, 95,15,100, true,false)); // 右下2


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
            try{
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
            }catch(Exception e){
                master.debug.AddLog(e.getMessage());// FIXME: デバッグ用のため削除
            }
        }
    }
}
