package game;
import consolegui.*;
import master.*;

public class Player extends CObject {
    KeyBoardService key;
    GameScene manager;

    public Player(GameScene manager,int x, int y, boolean isvisible) {
        super(manager.master.view,"def", SpriteBuildService.BuildModel("./data/costume/player/def.txt", CColor.BLACK, CColor.DEFAULT), x, y, isvisible);
        AddCostume("Left", SpriteBuildService.BuildModel("./data/costume/player/left.txt", CColor.BLACK, CColor.DEFAULT)); 
        AddCostume("Right", SpriteBuildService.BuildModel("./data/costume/player/right.txt", CColor.BLACK, CColor.DEFAULT)); 
        AddCostume("Up", SpriteBuildService.BuildModel("./data/costume/player/upper.txt", CColor.BLACK, CColor.DEFAULT)); 
        AddCostume("Down", SpriteBuildService.BuildModel("./data/costume/player/lower.txt", CColor.BLACK, CColor.DEFAULT)); 
        SwitchCostume("def");
        this.key = manager.master.key;
        this.manager = manager;
    }

    public void Update() {
        int vx=0,vy=0;
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
        // master.debug.AddLog("IsHit : "+IsHit(master.map, '＃',this.GetCostumeData().get(1).get(1).word)+" thisword : "+this.GetCostumeData().get(1).get(1).word);
        if(IsHit(manager.map, '＃',this.GetCostumeData().get(1).get(1).word)||
            IsHit(manager.map, '｜',this.GetCostumeData().get(1).get(1).word)||
            IsHit(manager.map, '＿',this.GetCostumeData().get(1).get(1).word))MoveLocation(-vx, -vy);


        int x = manager.player.X - manager.master.view.getWidth() / 2;
        if(manager.player.X < manager.master.view.getWidth() / 2)x=0;
        if(manager.player.X > manager.map.GetCostumeData().get(0).size() - manager.master.view.getWidth()/2)x=manager.map.GetCostumeData().get(0).size() - manager.master.view.getWidth();
        int y = manager.player.Y - manager.master.view.getHeight() / 2;
        if(manager.player.Y < manager.master.view.getHeight() / 2)y=0;
        if(manager.player.Y > manager.map.GetCostumeData().size()- manager.master.view.getHeight()/2)y=manager.map.GetCostumeData().size() - manager.master.view.getHeight();
        manager.master.view.SetLocation(x,y);
        manager.map.ChangeDrawingOrder(-1);


    }
}
