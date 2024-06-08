package result;

import consolegui.*;
import master.*;
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
        title = new StringService(master.view, "結果", master.view.getCameraX() + 45,
                master.view.getCameraY() + 24, CColor.WHITE, CColor.BLACK, true);
        time = new StringService(master.view, "タイム:" + sdf.format(result.time),
                master.view.getCameraX() + 45, master.view.getCameraY() + 25, CColor.WHITE, CColor.BLACK, true);
        collectionNum = new StringService(master.view, "スコア:" + result.rankingCell.getScore(),
                master.view.getCameraX() + 45, master.view.getCameraY() + 26, CColor.WHITE, CColor.BLACK, true);
        newrecord = new StringService(master.view, "ランキング更新！！", master.view.getCameraX() + 45,
                master.view.getCameraY() + 27, CColor.WHITE, CColor.BLACK, true);
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
        int frame = -10;
        Timestamp now = new Timestamp(System.currentTimeMillis());
        while (true) {
            if(frame<0){
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
            if(frame>10){
                frame=-10;
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
