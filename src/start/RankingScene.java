package start;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import consolegui.*;
import ranking.*;

public class RankingScene {

    public MainMenuController manager;
    public ArrayList<StringService> rankingText;
    public StringService subtitle;
    public StringService quit;

    public RankingScene(MainMenuController manager){
        this.manager = manager;
        this.rankingText = new ArrayList<StringService>();
        this.subtitle = new StringService(manager.master.view, "順位,スコア ,タイム", 40, 20, CColor.BLACK, 0, true);
        this.quit = new StringService(manager.master.view, "qキーで戻る", 40, 40, CColor.BLACK, 0, true);

    }

    public void run(){
        var ranking = RankingManager.DataRoad();
        Collections.sort(ranking, Collections.reverseOrder());
        for(int i=0;i<ranking.size();i++){
            this.rankingText.add(new StringService(manager.master.view,(i+1)+"位,"+ ranking.get(i).toString(), 40, 22+i*2, CColor.BLACK, 0, true));
        }
        quit.SetLocation(40, 22+ranking.size()*2+2);
        while(true){
            if(manager.master.key.isKeyPressed('q')){
                break;
            }
            manager.master.view.Update();
            sleep(100);
        }
        destroy();
    }

    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroy(){
        subtitle.RemoveMe();
        quit.RemoveMe();
        for(StringService text : rankingText){
            text.RemoveMe();
        }
        subtitle = null;
        rankingText = null;
        quit = null;
    }


}
