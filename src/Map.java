import consolegui.*;

public class Map extends CObject{
    private Game master;

    public Map(Game master){
        super(master.view,"map",SpriteBuildService.BuildModel("./data/costume/map/map.txt",CColor.BLACK,CColor.BLACK),0,0,false);
        this.master = master;
    }

    public void UpdateLocation(){
        int x = master.player.X - master.view.getWidth() / 2;
        if(master.player.X < master.view.getWidth() / 2)x=0;
        if(master.player.X > this.GetCostumeData().get(0).size() - master.view.getWidth()/2)x=this.GetCostumeData().get(0).size() - master.view.getWidth();
        int y = master.player.Y - master.view.getHeight() / 2;
        if(master.player.Y < master.view.getHeight() / 2)y=0;
        if(master.player.Y > this.GetCostumeData().size()- master.view.getHeight()/2)y=this.GetCostumeData().size() - master.view.getHeight();
        master.view.SetLocation(x,y);
    }
}
