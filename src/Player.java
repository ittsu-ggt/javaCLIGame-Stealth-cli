import consolegui.*;

public class Player extends CObject {
    KeyBoardService key;
    Game master;

    public Player(Game master,int x, int y, boolean isvisible) {
        super(master.view,"def", SpriteBuildService.BuildModel("./data/costume/player/def.txt", CColor.BLACK, CColor.DEFAULT), x, y, isvisible);
        AddCostume("Left", SpriteBuildService.BuildModel("./data/costume/player/left.txt", CColor.BLACK, CColor.DEFAULT)); 
        AddCostume("Right", SpriteBuildService.BuildModel("./data/costume/player/right.txt", CColor.BLACK, CColor.DEFAULT)); 
        AddCostume("Up", SpriteBuildService.BuildModel("./data/costume/player/upper.txt", CColor.BLACK, CColor.DEFAULT)); 
        AddCostume("Down", SpriteBuildService.BuildModel("./data/costume/player/lower.txt", CColor.BLACK, CColor.DEFAULT)); 
        SwitchCostume("def");
        this.key = master.key;
        this.master = master;
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
        master.debug.AddLog("IsHit : "+IsHit(master.map, '＃',this.GetCostumeData().get(1).get(1).word)+" thisword : "+this.GetCostumeData().get(1).get(1).word);
        if(IsHit(master.map, '＃',this.GetCostumeData().get(1).get(1).word))MoveLocation(-vx, -vy);
        master.map.UpdateLocation();
        master.map.ChangeDrawingOrder(-1);

    }
}
