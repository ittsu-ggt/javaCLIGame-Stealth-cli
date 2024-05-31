package game;

import java.util.Random;

import consolegui.CColor;
import consolegui.CObject;
import consolegui.SpriteBuildService;

public class Item extends CObject {

    private GameScene manager;

    public Item(GameScene manager, int x, int y, boolean isvisible){
        super(manager.master.view, "def",
                SpriteBuildService.BuildModel("./data/costume/item/item.txt", CColor.BLACK, CColor.DEFAULT), x, y,
                isvisible);
        this.manager = manager;
        

    }

    public Item(GameScene manager, boolean isvisible){
        this(manager, 0, 0, isvisible);
        Random rand = new Random();
        this.SetLocation(rand.nextInt(manager.map.GetCostumeData().get(0).size()-2)+1, rand.nextInt(manager.map.GetCostumeData().size()-2)+1);
        while (IsHit(manager.map,'＃') || IsHit(manager.map,'＠')) {
            this.SetLocation(rand.nextInt(manager.map.GetCostumeData().get(0).size()-2)+1, rand.nextInt(manager.map.GetCostumeData().size()-2)+1);
        }
        
    }

    public boolean Update(){
        if(IsHit(manager.player,'Ｐ')){
            onPlayerTouch();
            return true;
        }
        return false;
    }

    public void onPlayerTouch(){
        this.manager.score += 100;
        this.IsVisible = false;
        this.RemoveMe();
    }
}
