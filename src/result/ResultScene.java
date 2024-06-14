package result;

import consolegui.*;
import master.*;
import ranking.RankingCell;
import ranking.RankingManager;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ResultScene {
    Game master;
    Result result;
    SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
    CObject result_text;
    StringService title ;
    StringService time;
    StringService collectionNum;
    StringService newrecord;
    StringService button1;

    public ResultScene(Game master, Result result) {
        this.master = master;
        this.result = result;
    }

    public void run() {
        master.view.SetLocation(0, 0);
        title = new StringService(master.view, "結果", master.view.getCameraX() + 45,
                master.view.getCameraY() + 24, CColor.WHITE, CColor.BLACK, true);
        time = new StringService(master.view, "タイム:" + sdf.format(result.rankingCell.getTime()),
                master.view.getCameraX() + 45, master.view.getCameraY() + 25, CColor.WHITE, CColor.BLACK, true);
        collectionNum = new StringService(master.view, "スコア:" + result.rankingCell.getScore(),
                master.view.getCameraX() + 45, master.view.getCameraY() + 26, CColor.WHITE, CColor.BLACK, true);
        newrecord = new StringService(master.view, "ランキング更新！！", master.view.getCameraX() + 45,
                master.view.getCameraY() + 27, CColor.WHITE, CColor.BLACK, false);
        button1 = new StringService(master.view, "スペースキーでタイトルに戻る", master.view.getCameraX() + 45,
                master.view.getCameraY() + 35, CColor.WHITE, CColor.BLACK, true);
        
        ArrayList<ArrayList<DrawCell>> result_text_costume;

        if (result.isClear) {
            result_text_costume = SpriteBuildService.BuildModel("./data/costume/result_text/gameclear.txt",
                    CColor.WHITE,
                    CColor.WHITE);
        } else {
            result_text_costume = SpriteBuildService.BuildModel("./data/costume/result_text/gameover.txt", CColor.WHITE,
                    CColor.WHITE);
        }
        result_text = new CObject(master.view, result_text_costume, master.view.getCameraX() + 20,
                master.view.getCameraY() + 1,
                true);
        result_text.ChangeDrawingOrder(-1);
        int frame = 0;
        if(RankingManager.DataSave(new RankingCell(result.rankingCell.getScore(), result.rankingCell.getTime()))) {
            newrecord.SetVisible(true);
        }
        Timestamp start = new Timestamp(System.currentTimeMillis());
        master.key.bufferClear();
        while (true) {
            if((frame/10)%2==0){
                button1.SetVisible(true);
            }else{
                button1.SetVisible(false);
            }
            char key=master.key.GetKey();
            if(key==' '){
                break;
            }
            sleep(100);
            master.view.Update();
            frame++;
            if((new Timestamp(System.currentTimeMillis()).getTime() - start.getTime()) / 1000 >300){
                break;
            }
        }
        sleep(1000);
        deleteScene();
        return;
    }

    public void sleep(int n){
        try{
            Thread.sleep(n);
        }catch(InterruptedException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteScene() {
        result_text.RemoveMe();
        title.RemoveMe();
        time.RemoveMe();
        collectionNum.RemoveMe();
        newrecord.RemoveMe();
        button1.RemoveMe();
        result_text = null;
        title = null;
        time = null;
        collectionNum = null;
        newrecord = null;
        button1 = null;
        
    }

}
