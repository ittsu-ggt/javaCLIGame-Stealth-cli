package master;

import consolegui.CColor;
import consolegui.StringService;

public class ExitDisplay {
    private Game master;
    private StringService stringService;

    public ExitDisplay(Game master){
        this.master = master;
        this.stringService = new StringService(master.view,"ゲームを終了するには任意のキーを2回押してください",30,24,CColor.WHITE,CColor.BLACK,false);
    }

    public void run(){
        this.stringService.IsVisible = true;
        master.view.Update();
    }


}
