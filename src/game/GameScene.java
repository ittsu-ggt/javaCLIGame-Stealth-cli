package game;

import master.*;
import consolegui.*;
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
    ArrayList<Item> items;
    ArrayList<Respon> respons;
    StatusLog statuslog;
    String time;
    int score = 0;
    int item_num = 0;

    /**
     * ゲームの管理モジュール
     * 
     * @param master アプリケーションの管理モジュール
     * @throws InterruptedException
     * @throws IOException
     */
    public GameScene(Game master) throws InterruptedException, IOException {
        this.master = master;
        this.player = new Player(this, 6, 9, true);
        this.map = new Map(this);
        this.statuslog = new StatusLog(this);
        this.map.SetVisible(true);
        this.enemies = new ArrayList<Enemy>();
        this.items = new ArrayList<Item>();
        this.respons = new ArrayList<Respon>();

        this.enemies.add(new Enemy(this, 21, 10, 15, 100, true, false)); // 左上1
        this.enemies.add(new Enemy(this, 16, 33, 8, 100, true, false)); // 左上2
        this.enemies.add(new Enemy(this, 34, 96, 4, 50, true, false)); // 左下1
        this.enemies.add(new Enemy(this, 34, 96, 4, 50, true, false)); // 左下2
        this.enemies.add(new Enemy(this, 34, 96, 4, 50, true, false)); // 左下2
        this.enemies.add(new Enemy(this, 10, 52, 20, 100, true, false));// 中央左1
        this.enemies.add(new Enemy(this, 79, 90, 100, 200, true, false)); // 中央通路1
        this.enemies.add(new Enemy(this, 81, 53, 20, 100, true, false)); // 中央右1
        this.enemies.add(new Enemy(this, 104, 15, 4, 50, true, false)); // 右上1
        this.enemies.add(new Enemy(this, 104, 15, 4, 50, true, false)); // 右上2
        this.enemies.add(new Enemy(this, 133, 28, 4, 50, true, false)); // 右上3
        this.enemies.add(new Enemy(this, 111, 72, 8, 100, true, false)); // 右下1
        this.enemies.add(new Enemy(this, 109, 95, 15, 100, true, false)); // 右下2

        for (int i = 0; i < 15; i++) {
            this.items.add(new Item(this, true));
        }
        this.item_num = items.size();

        this.respons.add(new Respon(this, 7, 10));
        this.respons.add(new Respon(this, 55, 97));
        this.respons.add(new Respon(this, 88, 70));
        this.respons.add(new Respon(this, 58, 40));
        this.respons.add(new Respon(this, 91, 13));
        this.respons.add(new Respon(this, 139, 100));

    }

    /**
     * シーンの実行
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public Result run() throws InterruptedException, IOException {
        Timestamp gamestart = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        master.view.SetLocation(0, 0);

        while (player.hp > 0 && items.size() > 0) {
            try {
                player.Update();
                for (Enemy enemy : enemies) {
                    enemy.Update();
                }
                player.ChangeDrawingOrder(-1);
                ArrayList<Item> itemscopy = new ArrayList<Item>();
                for (Item item : items) {
                    if (!item.Update()) {
                        itemscopy.add(item);
                        item.ChangeDrawingOrder(-1);
                    }
                }
                items = itemscopy;

                map.ChangeDrawingOrder(0);
                var now = new Timestamp(System.currentTimeMillis());
                time = sdf.format(now.getTime() - gamestart.getTime());
                statuslog.refresh(player.hp, time);
                master.debug.Update();
                master.view.Update();

                Thread.sleep(50);
            } catch (Exception e) {
                master.debug.AddLog(e.getMessage());// FIXME: デバッグ用のため削除
            }
        }
        var end = new Timestamp(System.currentTimeMillis());
        Result result;
        boolean result_flag;
        if (player.hp <= 0) {
            result_flag = false;
        } else if (items.size() <= 0) {
            result_flag = true;
        } else {
            throw new RuntimeException(
                    "GameScene : ゲーム終了条件に問題が発生しました\nplayer.hp = " + player.hp + "\nitems.size() = " + items.size());
        }
        result = new Result(result_flag, score,item_num-items.size() ,(end.getTime() - gamestart.getTime()) / 1000);
        CObject result_text;
        ArrayList<ArrayList<DrawCell>> result_text_costume;
        if (result.isClear) {
            result_text_costume = SpriteBuildService.BuildModel("./data/costume/result_text/gameclear.txt", CColor.BLUE,
                    CColor.BLUE);
        } else {
            result_text_costume = SpriteBuildService.BuildModel("./data/costume/result_text/gameover.txt", CColor.RED,
                    CColor.RED);
        }
        result_text = new CObject(master.view, result_text_costume, master.view.getCameraX(), master.view.getCameraY(),
                true);
        result_text.ChangeDrawingOrder(-1);
        for(int i=0;i<3;i++){
            result_text.SetVisible(true);
            master.view.Update();
            Thread.sleep(1000);
            result_text.SetVisible(false);
            master.view.Update();
            Thread.sleep(1000);
        }
        deleteScene();
        master.view.Update();
        return result;
    }


    public void deleteScene(){
        player.RemoveMe();
        map.RemoveMe();
        for(Enemy enemy:enemies){
            enemy.RemoveMe();
        }
        for(Item item:items){
            item.RemoveMe();
        }
        for(Respon respon:respons){
            respon.RemoveMe();
        }
        statuslog.RemoveMe();
        player=null;
        map=null;
        enemies=null;
        items=null;
        respons=null;
        statuslog=null;
        master.view.SetLocation(0, 0);
    }
}
