package game;

import consolegui.*;
import master.*;

public class Map extends CObject {
    private GameScene master;

    /**
     * マップのコンストラクター
     * @param master シーンの管理モジュール
     */
    public Map(GameScene master) {
        super(master.master.view, "map",
                SpriteBuildService.BuildModel("./data/costume/map/map.txt", 
                        "./data/costume/map/mapfgcolor.txt",
                        "./data/costume/map/mapbgcolor.txt"),
                        0, 0, false);
        this.master = master;
    }
}
