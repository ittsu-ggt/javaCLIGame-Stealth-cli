package game;

import consolegui.*;
import master.*;

public class Map extends CObject {
    private GameScene master;

    public Map(GameScene master) {
        super(master.master.view, "map",
                SpriteBuildService.BuildModel("./data/costume/map/map.txt", CColor.BLACK, CColor.BLACK), 0, 0, false);
        this.master = master;
    }
}
