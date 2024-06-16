package start;

import java.util.ArrayList;

import consolegui.*;

public class HowToPlayScene {


    public MainMenuController manager;
    private ArrayList<StringService> howToPlayText;
    private static final int HOW_TO_PLAY_TEXT_X = 20;

    public HowToPlayScene(MainMenuController manager){
        this.manager = manager;
        this.howToPlayText = new ArrayList<StringService>();
        this.howToPlayText.add(new StringService(manager.master.view, "遊び方:", 0, 0, CColor.BLACK, 0, false));
        this.howToPlayText.add(new StringService(manager.master.view, "・操作方法：", 0, 0, CColor.BLACK, 0, false));
        this.howToPlayText.add(new StringService(manager.master.view, "　・上に移動：Wキー　もしくは　↑　キー", 0, 0, CColor.BLACK, 0, false));
        this.howToPlayText.add(new StringService(manager.master.view, "　・下に移動：Sキー　もしくは　↓　キー", 0, 0, CColor.BLACK, 0, false));
        this.howToPlayText.add(new StringService(manager.master.view, "　・左に移動：Aキー　もしくは　←　キー", 0, 0, CColor.BLACK, 0, false));
        this.howToPlayText.add(new StringService(manager.master.view, "　・右に移動：Dキー　もしくは　→　キー", 0, 0, CColor.BLACK, 0, false));
        this.howToPlayText.add(new StringService(manager.master.view, "・ルール：", 0, 0, CColor.BLACK, 0, false));
        this.howToPlayText.add(new StringService(manager.master.view, "　・ステージ上には１５個のアイテムがあり、プレイヤーはこのアイテムを制限時間内に集めます", 0, 0, CColor.BLACK, 0, false));
        this.howToPlayText.add(new StringService(manager.master.view, "　・ステージ上には敵がいます。敵に見つからないように移動しましょう", 0, 0, CColor.BLACK, 0, false));
        this.howToPlayText.add(new StringService(manager.master.view, "　・敵に見つかると近くのリスポーン地点にワープします。", 0, 0, CColor.BLACK, 0, false));
        this.howToPlayText.add(new StringService(manager.master.view, "　・3回見つかってしまうとゲームオーバです。", 0, 0, CColor.BLACK, 0, false));
        this.howToPlayText.add(new StringService(manager.master.view, "　・制限時間は5分です", 0, 0, CColor.BLACK, 0, false));
        this.howToPlayText.add(new StringService(manager.master.view, "qキーでタイトルに戻る", 0, 0, CColor.BLACK, 0, false));

    }

    public void run(){
        for(int i=0;i<howToPlayText.size();i++){
            howToPlayText.get(i).SetLocation(HOW_TO_PLAY_TEXT_X, 2+i*2);
            howToPlayText.get(i).IsVisible = true;
        }
        while(true){
            if(manager.master.key.isKeyPressed('q')){
                break;
            }
            manager.master.view.Update();
            sleep(100);
        }
        destroy();
    }

    public void destroy(){
        for(StringService text : howToPlayText){
            text.RemoveMe();
        }
        howToPlayText = null;
    }

    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
