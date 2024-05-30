package game;

import consolegui.*;

public class Enemy extends CObject {
    GameScene manager;

    private class Enemysensor {
        CObject front;
        CObject right;
        CObject left;
    }

    public Enemy(GameScene manager, int x, int y, boolean isvisible) {
        super(manager.master.view, "def",
                SpriteBuildService.BuildModel("./data/costume/enemy/def.txt", CColor.BLACK, CColor.DEFAULT), x, y,
                isvisible);
        AddCostume("Left",
                SpriteBuildService.BuildModel("./data/costume/enemy/left.txt", CColor.BLACK, CColor.DEFAULT));
        AddCostume("Right",
                SpriteBuildService.BuildModel("./data/costume/enemy/right.txt", CColor.BLACK, CColor.DEFAULT));
        AddCostume("Up", SpriteBuildService.BuildModel("./data/costume/enemy/upper.txt", CColor.BLACK, CColor.DEFAULT));
        AddCostume("Down",
                SpriteBuildService.BuildModel("./data/costume/enemy/lower.txt", CColor.BLACK, CColor.DEFAULT));
        SwitchCostume("def");
        Enemysensor sensor = new Enemysensor();
        sensor.front = new CObject(manager.master.view, "def",
                SpriteBuildService.BuildModel("./data/costume/enemy/sensor.txt", CColor.DEFAULT, CColor.DEFAULT),
                this.X, this.Y - 3, isvisible);
        sensor.right = new CObject(manager.master.view, "def",
                SpriteBuildService.BuildModel("./data/costume/enemy/sensor.txt", CColor.DEFAULT, CColor.DEFAULT),
                this.X - 3, y, isvisible);
        sensor.left = new CObject(manager.master.view, "def",
                SpriteBuildService.BuildModel("./data/costume/enemy/sensor.txt", CColor.DEFAULT, CColor.DEFAULT),
                this.X + 3, y, isvisible);
        this.manager = manager;
    }

    public void Update() {

    }

}
