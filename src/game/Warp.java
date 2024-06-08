package game;

import consolegui.*;

public class Warp extends CObject{
    private GameScene manager;

    public Warp(GameScene manager,int x,int y){
        super(manager.master.view,"Warp",SpriteBuildService.BuildModel("./data/costume/warp/warp.txt",CColor.BLUE,CColor.BLUE),x,y,true);
        this.manager = manager;
    }
}
