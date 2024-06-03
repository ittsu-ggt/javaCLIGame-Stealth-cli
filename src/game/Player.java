package game;

import consolegui.*;
import master.*;

public class Player extends CObject {
    KeyBoardService key;
    GameScene manager;

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
                SpriteBuildService.BuildModel("./data/costume/player/def.txt", CColor.GREEN, CColor.DEFAULT), x, y,
                isvisible);
        AddCostume("Left",
                SpriteBuildService.BuildModel("./data/costume/player/left.txt", CColor.GREEN, CColor.DEFAULT));
        AddCostume("Right",
                SpriteBuildService.BuildModel("./data/costume/player/right.txt", CColor.GREEN, CColor.DEFAULT));
        AddCostume("Up",
                SpriteBuildService.BuildModel("./data/costume/player/upper.txt", CColor.GREEN, CColor.DEFAULT));
        AddCostume("Down",
                SpriteBuildService.BuildModel("./data/costume/player/lower.txt", CColor.GREEN, CColor.DEFAULT));
        SwitchCostume("def");
        this.key = manager.master.key;
        this.manager = manager;
    }

    /**
     * プレイヤーの更新処理
     */
    public void Update() {
        int vx = 0, vy = 0;
        if (key.isKeyPressed('w')) {
            vx = 0;
            vy = -1;
            SwitchCostume("Up");
        }
        if (key.isKeyPressed('s')) {
            vx = 0;
            vy = 1;
            SwitchCostume("Down");
        }
        if (key.isKeyPressed('a')) {
            vx = -1;
            vy = 0;
            SwitchCostume("Left");
        }
        if (key.isKeyPressed('d')) {
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
}
