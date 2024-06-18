package game;

import java.util.Random;

import consolegui.CColor;
import consolegui.CObject;
import consolegui.SpriteBuildService;

public class Item extends CObject {

    private GameScene manager;

    public Item(GameScene manager, int x, int y, boolean isvisible){
        super(manager.master.view, "def",
                SpriteBuildService.BuildModel("../data/costume/item/item.txt", CColor.BLACK, CColor.DEFAULT), x, y,
                isvisible);
        this.manager = manager;
        

    }

    public Item(GameScene manager, boolean isvisible){
        this(manager, 0, 0, isvisible);
        Random rand = new Random();
        this.SetLocation(rand.nextInt(manager.map.GetCostumeData().get(0).size()-2)+1, rand.nextInt(manager.map.GetCostumeData().size()-2)+1);
        boolean flag = true;
        int count = 0;
        while (count<100 || IsHit(manager.map,'＃') || IsHit(manager.map,'＠')) {
            this.SetLocation(rand.nextInt(manager.map.GetCostumeData().get(0).size()-2)+1, rand.nextInt(manager.map.GetCostumeData().size()-2)+1);
            if(!(IsHit(manager.map,'＃') || IsHit(manager.map,'＠'))){
                flag = false;
                if(manager.items.size() > 0){
                    for(Item it:manager.items){
                        if(Math.abs(it.X - this.X) + Math.abs(it.Y - this.Y) <25){
                            flag = true;
                        }
                    }
                }
            }
            count++;
        } // 万が一、条件に合致しない状態が100回続いた場合、強制的に配置する
        
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
