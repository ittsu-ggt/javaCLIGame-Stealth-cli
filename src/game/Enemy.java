package game;

import java.util.Random;

import consolegui.*;

public class Enemy extends CObject {
    GameScene manager;
    Enemysensor sensor;
    // 1:上 2:右 3:下 4:左
    int mydirection = 1;

    int threshold1;
    int threshold2;

    boolean debugstatus = false;
    boolean debugisruninng = true;
    boolean isPlay = true;

    private class Enemysensor {
        CObject wallFront;
        CObject wallRight;
        CObject wallLeft;
        CObject playerFront;
    }

    /**
     * 敵のコンストラクター
     * 
     * @param manager   シーンの管理モジュール
     * @param x         x座標
     * @param y         y座標
     * @param isvisible 表示するかどうか
     */
    public Enemy(GameScene manager, int x, int y, boolean isvisible,boolean debugstatus) {
        this(manager, x, y, 15, 100,isvisible, debugstatus);
    }

    /**
     * 敵のコンストラクター
     * 
     * @param manager    シーンの管理モジュール
     * @param x          x座標
     * @param y          y座標
     * @param isvisible  表示するかどうか
     * @param threshold1 前にすすめるとき、可能な場合に左右に曲がる確率のしきい値。小さいほど曲がりやすくなる
     * @param threshold2 後ろを向く確率のしきい値。小さいほど後ろを向きやすくなる
     */
    public Enemy(GameScene manager, int x, int y, int threshold1, int threshold2, boolean isvisible, boolean debugstatus) {
        super(manager.master.view, "def",
                SpriteBuildService.BuildModel("./data/costume/enemy/def.txt", CColor.RED,
                        CColor.DEFAULT),
                x, y,
                isvisible);
        this.threshold1 = threshold1;
        this.threshold2 = threshold2;
        AddCostume("wallLeft",
                SpriteBuildService.BuildModel("./data/costume/enemy/left.txt", CColor.RED,
                        CColor.DEFAULT));
        AddCostume("wallRight",
                SpriteBuildService.BuildModel("./data/costume/enemy/right.txt", CColor.RED,
                        CColor.DEFAULT));
        AddCostume("Up", SpriteBuildService.BuildModel("./data/costume/enemy/upper.txt", CColor.RED,
                CColor.DEFAULT));
        AddCostume("Down",
                SpriteBuildService.BuildModel("./data/costume/enemy/lower.txt", CColor.RED,
                        CColor.DEFAULT));
        SwitchCostume("def");
        sensor = new Enemysensor();
        sensor.wallFront = new CObject(manager.master.view, "length",
                SpriteBuildService.BuildModel("./data/costume/enemy/sensorHitWall1.txt", CColor.DEFAULT,
                        CColor.RED),
                this.X, this.Y, isvisible);
        sensor.wallRight = new CObject(manager.master.view, "length",
                SpriteBuildService.BuildModel("./data/costume/enemy/sensorHitWall2.txt", CColor.DEFAULT,
                        CColor.RED),
                this.X, y, isvisible);
        sensor.wallLeft = new CObject(manager.master.view, "length",
                SpriteBuildService.BuildModel("./data/costume/enemy/sensorHitWall2.txt", CColor.DEFAULT,
                        CColor.RED),
                this.X, y, isvisible);
        sensor.playerFront = new CObject(manager.master.view, "def",
                SpriteBuildService.BuildModel("./data/costume/enemy/sensorPlayer.txt", CColor.DEFAULT,
                        CColor.DEFAULT),
                this.X, y, isvisible);

        sensor.wallFront.AddCostume("vertical",
                SpriteBuildService.BuildModel("./data/costume/enemy/sensorHitWall2.txt", CColor.DEFAULT,
                CColor.RED));
        sensor.wallRight.AddCostume("vertical",
        SpriteBuildService.BuildModel("./data/costume/enemy/sensorHitWall1.txt", CColor.DEFAULT,
                CColor.RED));
        sensor.wallLeft.AddCostume("vertical",
        SpriteBuildService.BuildModel("./data/costume/enemy/sensorHitWall1.txt", CColor.DEFAULT,
                CColor.RED));
        sensor.playerFront.AddCostume("light",
        SpriteBuildService.BuildModel("./data/costume/enemy/sensorPlayer2.txt", CColor.YELLOW,
                CColor.YELLOW));

        this.manager = manager;
        this.debugstatus = debugstatus;
    }

    private void TurnRight() {
        mydirection += 1;
        if (mydirection > 4) {
            mydirection = 1;
        }
    }

    private void TurnLeft() {
        mydirection -= 1;
        if (mydirection < 1) {
            mydirection = 4;
        }
    }

    private void setSensor() {

        if (mydirection == 1) {
            sensor.wallFront.SetLocation(this.X, this.Y - 1);
            sensor.wallRight.SetLocation(this.X + 2, this.Y);
            sensor.wallLeft.SetLocation(this.X - 1, this.Y);
            sensor.playerFront.SetLocation(this.X, this.Y - 2);
            sensor.wallFront.SwitchCostume("length");
            sensor.wallLeft.SwitchCostume("length");
            sensor.wallRight.SwitchCostume("length");
        } else if (mydirection == 2) {
            sensor.wallFront.SetLocation(this.X + 2, this.Y);
            sensor.wallRight.SetLocation(this.X, this.Y + 2);
            sensor.wallLeft.SetLocation(this.X , this.Y - 1);
            sensor.playerFront.SetLocation(this.X + 2, this.Y);
            sensor.wallFront.SwitchCostume("vertical");
            sensor.wallLeft.SwitchCostume("vertical");
            sensor.wallRight.SwitchCostume("vertical");

        } else if (mydirection == 3) {
            sensor.wallFront.SetLocation(this.X, this.Y + 2);
            sensor.wallRight.SetLocation(this.X - 1, this.Y );
            sensor.wallLeft.SetLocation(this.X + 2, this.Y );
            sensor.playerFront.SetLocation(this.X, this.Y + 2);
            sensor.wallFront.SwitchCostume("length");
            sensor.wallLeft.SwitchCostume("length");
            sensor.wallRight.SwitchCostume("length");

        } else if (mydirection == 4) {
            sensor.wallFront.SetLocation(this.X - 1, this.Y);
            sensor.wallRight.SetLocation(this.X, this.Y - 1);
            sensor.wallLeft.SetLocation(this.X, this.Y + 2);
            sensor.playerFront.SetLocation(this.X - 2, this.Y);
            sensor.wallFront.SwitchCostume("vertical");
            sensor.wallLeft.SwitchCostume("vertical");
            sensor.wallRight.SwitchCostume("vertical");
        } else {
            throw new IllegalArgumentException(this.getClass().getName() + "mydirectionが不正です．");
        }
        sensor.wallFront.ChangeDrawingOrder(0);
        sensor.wallRight.ChangeDrawingOrder(0);
        sensor.wallLeft.ChangeDrawingOrder(0);
        sensor.playerFront.ChangeDrawingOrder(0);
    }

    private void goAhead() {
        sensor.playerFront.SwitchCostume("def");
        if (mydirection == 1) {
            MoveLocation(0, -1);
            SwitchCostume("Up");
        } else if (mydirection == 2) {
            MoveLocation(1, 0);
            SwitchCostume("wallRight");
        } else if (mydirection == 3) {
            MoveLocation(0, 1);
            SwitchCostume("Down");
        } else if (mydirection == 4) {
            MoveLocation(-1, 0);
            SwitchCostume("wallLeft");
        } else {
            throw new IllegalArgumentException(this.getClass().getName() + "mydirectionが不正です．");
        }
        if(IsHit(manager.map, '＃', 'Ｅ')){ // FIXME: デバッグ用のため削除
            debugisruninng = false;
            setSensor();
            throw new IllegalArgumentException(this.getClass().getName() + "敵が壁に埋まっています．"+X+":"+Y);
        }

    }

    private void moveAction(){
        Random rand = new Random();
        if (rand.nextInt(threshold2) == 0) { // 確率で後ろに向く
            TurnRight();
            TurnRight();
        }
        // StringService str = new StringService(manager.master.view, "*", X+1, Y+1, CColor.RED, CColor.DEFAULT, true); // FIXME: デバッグ用のため削除
        setSensor();
        boolean canMovewallFront = !(sensor.wallFront.IsHit(manager.map, '＃', sensor.wallFront.GetCostumeData().get(0).get(0).word)
                || sensor.wallFront.IsHit(manager.map, '＠', sensor.wallFront.GetCostumeData().get(0).get(0).word));
        boolean canTrunwallRight = !(sensor.wallRight.IsHit(manager.map, '＃', sensor.wallFront.GetCostumeData().get(0).get(0).word)
                || sensor.wallRight.IsHit(manager.map, '＠', sensor.wallFront.GetCostumeData().get(0).get(0).word));
        boolean canTrunwallLeft = !(sensor.wallLeft.IsHit(manager.map, '＃', sensor.wallFront.GetCostumeData().get(0).get(0).word)
                     || sensor.wallLeft.IsHit(manager.map, '＠', sensor.wallFront.GetCostumeData().get(0).get(0).word));



        if (canMovewallFront) { // 前に進めるなら
            if ((canTrunwallRight || canTrunwallLeft) && rand.nextInt(threshold1) == 0) { // 左右に曲がれるかを判定
                if (rand.nextBoolean() && canTrunwallRight) { // 確率で右に曲がる
                    TurnRight();
                } else if (canTrunwallLeft) { // 確立に負けたら左
                    TurnLeft();
                } else { // 左に行けなければしょうがないので右
                    TurnRight();
                }
            }
        } else {
            if (canTrunwallRight && canTrunwallLeft) { // 左右に曲がれるなら
                if (rand.nextBoolean()) { // 確率で右に曲がる
                    TurnRight();
                } else {
                    TurnLeft();
                }
            } else if (canTrunwallRight) { // 右に曲がれるなら
                TurnRight();
            } else if (canTrunwallLeft) { // 左に曲がれるなら
                TurnLeft();
            } else { // どっちも曲がれないなら下がる
                TurnRight();
                TurnRight();
            }
        }
        goAhead();
        setSensor();
    }

    private void playerHitAction(){
        if(sensor.playerFront.IsHit(manager.player, 'Ｐ', sensor.playerFront.GetCostumeData().get(0).get(0).word)){
            manager.player.isDamage = true;
            isPlay = false;
        }
    }
    private void lighting(){
        sensor.playerFront.SwitchCostume("light");
    }

    /**
     * 敵を１コマ分動かす
     */
    public void Update() {
        if(!debugisruninng)return;
        if(isPlay){
            moveAction();
            playerHitAction();

        }else{
            lighting();
        }
        if(debugstatus)manager.master.debug.AddLog("X:" + X + " Y:" + Y);
        
    }

}
