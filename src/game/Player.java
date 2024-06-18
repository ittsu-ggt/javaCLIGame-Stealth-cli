package game;

import consolegui.*;
import master.*;

public class Player extends CObject {
    private KeyBoardService key;
    private GameScene manager;
    public int hp = 3;
    public boolean isDamage = false;


    /**
     * プレイヤーのコンストラクター
     * 
     * @param manager   シーンの管理モジュール
     * @param x         x座標
     * @param y         y座標
     * @param isvisible 表示するかどうか
     */
    public Player(GameScene manager, int x, int y, boolean isvisible) {
        super(manager.master.view, "def",
                SpriteBuildService.BuildModel("../data/costume/player/def.txt", CColor.GREEN, CColor.DEFAULT), x, y,
                isvisible);
        AddCostume("Left",
                SpriteBuildService.BuildModel("../data/costume/player/left.txt", CColor.GREEN, CColor.DEFAULT));
        AddCostume("Right",
                SpriteBuildService.BuildModel("../data/costume/player/right.txt", CColor.GREEN, CColor.DEFAULT));
        AddCostume("Up",
                SpriteBuildService.BuildModel("../data/costume/player/upper.txt", CColor.GREEN, CColor.DEFAULT));
        AddCostume("Down",
                SpriteBuildService.BuildModel("../data/costume/player/lower.txt", CColor.GREEN, CColor.DEFAULT));
        SwitchCostume("def");
        this.key = manager.master.key;
        this.manager = manager;
    }

    private void keyAciton(){
        int vx = 0, vy = 0;
        if (key.isKeyPressed('w') || key.isKeyPressed('↑')) {
            vx = 0;
            vy = -1;
            SwitchCostume("Up");
        }
        if (key.isKeyPressed('s') || key.isKeyPressed('↓')) {
            vx = 0;
            vy = 1;
            SwitchCostume("Down");
        }
        if (key.isKeyPressed('a') || key.isKeyPressed('←')) {
            vx = -1;
            vy = 0;
            SwitchCostume("Left");
        }
        if (key.isKeyPressed('d') || key.isKeyPressed('→')) {
            vx = 1;
            vy = 0;
            SwitchCostume("Right");
        }
        MoveLocation(vx, vy);
        // manager.master.debug.AddLog("X:" + X + " Y:" + Y);// FIXME: デバッグ用のため削除
        if (IsHit(manager.map, '＃', this.GetCostumeData().get(1).get(1).word) ||
                IsHit(manager.map, '｜', this.GetCostumeData().get(1).get(1).word) ||
                IsHit(manager.map, '＿', this.GetCostumeData().get(1).get(1).word)) {
            MoveLocation(-vx, -vy);
            SwitchCostume("def");
        }
        if(IsHit(manager.warp1)){
            SetLocation(manager.warp2.X+2, manager.warp2.Y-5); //下側
        }
        if(IsHit(manager.warp2)){
            SetLocation(manager.warp1.X+2, manager.warp1.Y+5); //上側
        }
    }

    private void setDisplayLocation(){
        int x = manager.player.X - manager.master.view.getWidth() / 2;
        if (manager.player.X < manager.master.view.getWidth() / 2)
            x = 0;
        if (manager.player.X > manager.map.GetCostumeData().get(0).size() - manager.master.view.getWidth() / 2)
            x = manager.map.GetCostumeData().get(0).size() - manager.master.view.getWidth();
        int y = manager.player.Y - manager.master.view.getHeight() / 2;
        if (manager.player.Y < manager.master.view.getHeight() / 2)
            y = 0;
        if (manager.player.Y > manager.map.GetCostumeData().size() - manager.master.view.getHeight() / 2)
            y = manager.map.GetCostumeData().size() - manager.master.view.getHeight();
        manager.master.view.SetLocation(x, y);
    }

    private int responDistance(Respon r){
        return Math.abs(r.X - X) + Math.abs(r.Y - Y);
    }

    private void responAction(){
        // 最も近いresponを求める
        Respon nearRespon = manager.respons.get(0);
        SwitchCostume("def");
        for (Respon r : manager.respons) {
            if (responDistance(r) < responDistance(nearRespon)) {
                nearRespon = r;
            }
        }
        if(nearRespon.X-1 == X && nearRespon.Y-1 == Y){
            isDamage = false;
            hp--;
            for(Enemy e : manager.enemies){
                e.isPlay = true;
            }
        }else if(nearRespon.X-1 < X){
            MoveLocation(-1, 0);
        }else if(nearRespon.X-1 > X){
            MoveLocation(1, 0);
        }else if(nearRespon.Y-1 < Y){
            MoveLocation(0, -1);
        }else if(nearRespon.Y-1 > Y){
            MoveLocation(0, 1);
        }
    }

    /**
     * プレイヤーの更新処理
     */
    public void Update() {
        if(isDamage)responAction();
        else keyAciton();

        setDisplayLocation();

    }
}
