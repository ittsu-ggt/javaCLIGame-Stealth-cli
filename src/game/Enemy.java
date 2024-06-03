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

    private class Enemysensor {
        CObject front;
        CObject right;
        CObject left;
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
        AddCostume("Left",
                SpriteBuildService.BuildModel("./data/costume/enemy/left.txt", CColor.RED,
                        CColor.DEFAULT));
        AddCostume("Right",
                SpriteBuildService.BuildModel("./data/costume/enemy/right.txt", CColor.RED,
                        CColor.DEFAULT));
        AddCostume("Up", SpriteBuildService.BuildModel("./data/costume/enemy/upper.txt", CColor.RED,
                CColor.DEFAULT));
        AddCostume("Down",
                SpriteBuildService.BuildModel("./data/costume/enemy/lower.txt", CColor.RED,
                        CColor.DEFAULT));
        SwitchCostume("def");
        sensor = new Enemysensor();
        sensor.front = new CObject(manager.master.view, "length",
                SpriteBuildService.BuildModel("./data/costume/enemy/sensorfront.txt", CColor.DEFAULT,
                        CColor.RED),
                this.X, this.Y, isvisible);
        sensor.right = new CObject(manager.master.view, "length",
                SpriteBuildService.BuildModel("./data/costume/enemy/sensorfront2.txt", CColor.DEFAULT,
                        CColor.RED),
                this.X, y, isvisible);
        sensor.left = new CObject(manager.master.view, "length",
                SpriteBuildService.BuildModel("./data/costume/enemy/sensorfront2.txt", CColor.DEFAULT,
                        CColor.RED),
                this.X, y, isvisible);

        sensor.front.AddCostume("vertical",
                SpriteBuildService.BuildModel("./data/costume/enemy/sensorfront2.txt", CColor.DEFAULT,
                CColor.RED));
        sensor.right.AddCostume("vertical",
        SpriteBuildService.BuildModel("./data/costume/enemy/sensorfront.txt", CColor.DEFAULT,
                CColor.RED));
        sensor.left.AddCostume("vertical",
        SpriteBuildService.BuildModel("./data/costume/enemy/sensorfront.txt", CColor.DEFAULT,
                CColor.RED));

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
            sensor.front.SetLocation(this.X, this.Y - 1);
            sensor.right.SetLocation(this.X + 2, this.Y);
            sensor.left.SetLocation(this.X - 1, this.Y);
            sensor.front.SwitchCostume("length");
            sensor.left.SwitchCostume("length");
            sensor.right.SwitchCostume("length");
        } else if (mydirection == 2) {
            sensor.front.SetLocation(this.X + 2, this.Y);
            sensor.right.SetLocation(this.X, this.Y + 2);
            sensor.left.SetLocation(this.X , this.Y - 1);
            sensor.front.SwitchCostume("vertical");
            sensor.left.SwitchCostume("vertical");
            sensor.right.SwitchCostume("vertical");

        } else if (mydirection == 3) {
            sensor.front.SetLocation(this.X, this.Y + 2);
            sensor.right.SetLocation(this.X - 1, this.Y );
            sensor.left.SetLocation(this.X + 2, this.Y );
            sensor.front.SwitchCostume("length");
            sensor.left.SwitchCostume("length");
            sensor.right.SwitchCostume("length");

        } else if (mydirection == 4) {
            sensor.front.SetLocation(this.X - 1, this.Y);
            sensor.right.SetLocation(this.X, this.Y - 1);
            sensor.left.SetLocation(this.X, this.Y + 2);
            sensor.front.SwitchCostume("vertical");
            sensor.left.SwitchCostume("vertical");
            sensor.right.SwitchCostume("vertical");
        } else {
            throw new IllegalArgumentException(this.getClass().getName() + "mydirectionが不正です．");
        }
        sensor.front.ChangeDrawingOrder(0);
        sensor.right.ChangeDrawingOrder(0);
        sensor.left.ChangeDrawingOrder(0);
    }

    private void goAhead() {

        if (mydirection == 1) {
            MoveLocation(0, -1);
            SwitchCostume("Up");
        } else if (mydirection == 2) {
            MoveLocation(1, 0);
            SwitchCostume("Right");
        } else if (mydirection == 3) {
            MoveLocation(0, 1);
            SwitchCostume("Down");
        } else if (mydirection == 4) {
            MoveLocation(-1, 0);
            SwitchCostume("Left");
        } else {
            throw new IllegalArgumentException(this.getClass().getName() + "mydirectionが不正です．");
        }
        if(IsHit(manager.map, '＃', 'Ｅ')){ // FIXME: デバッグ用のため削除
            debugisruninng = false;
            setSensor();
            throw new IllegalArgumentException(this.getClass().getName() + "敵が壁に埋まっています．"+X+":"+Y);
        }

    }

    /**
     * 敵を１コマ分動かす
     */
    public void Update() {
        if(!debugisruninng)return;
        Random rand = new Random();
        if (rand.nextInt(threshold2) == 0) { // 確率で後ろに向く
            TurnRight();
            TurnRight();
        }
        // StringService str = new StringService(manager.master.view, "*", X+1, Y+1, CColor.RED, CColor.DEFAULT, true); // FIXME: デバッグ用のため削除
        setSensor();
        boolean canMoveFront = !(sensor.front.IsHit(manager.map, '＃', sensor.front.GetCostumeData().get(0).get(0).word)
                || sensor.front.IsHit(manager.map, '＠', sensor.front.GetCostumeData().get(0).get(0).word));
        boolean canTrunRight = !(sensor.right.IsHit(manager.map, '＃', sensor.front.GetCostumeData().get(0).get(0).word)
                || sensor.right.IsHit(manager.map, '＠', sensor.front.GetCostumeData().get(0).get(0).word));
        boolean canTrunLeft = !(sensor.left.IsHit(manager.map, '＃', sensor.front.GetCostumeData().get(0).get(0).word)
                     || sensor.left.IsHit(manager.map, '＠', sensor.front.GetCostumeData().get(0).get(0).word));



        if (canMoveFront) { // 前に進めるなら
            if ((canTrunRight || canTrunLeft) && rand.nextInt(threshold1) == 0) { // 左右に曲がれるかを判定
                if (rand.nextBoolean() && canTrunRight) { // 確率で右に曲がる
                    TurnRight();
                } else if (canTrunLeft) { // 確立に負けたら左
                    TurnLeft();
                } else { // 左に行けなければしょうがないので右
                    TurnRight();
                }
            }
        } else {
            if (canTrunRight && canTrunLeft) { // 左右に曲がれるなら
                if (rand.nextBoolean()) { // 確率で右に曲がる
                    TurnRight();
                } else {
                    TurnLeft();
                }
            } else if (canTrunRight) { // 右に曲がれるなら
                TurnRight();
            } else if (canTrunLeft) { // 左に曲がれるなら
                TurnLeft();
            } else { // どっちも曲がれないなら下がる
                TurnRight();
                TurnRight();
            }
        }
        goAhead();
        setSensor();
        if(debugstatus)manager.master.debug.AddLog("X:" + X + " Y:" + Y);
    }

}
