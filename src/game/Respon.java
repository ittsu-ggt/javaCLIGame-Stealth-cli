package game;

import consolegui.*;
public class Respon extends CObject {
    private GameScene manager;

    public Respon(GameScene manager,int x,int y) {
        super(manager.master.view,"Respon",SpriteBuildService.BuildModel("../data/costume/respon/respon.txt",CColor.WHITE,CColor.CYAN),x,y,true);
    }
}
