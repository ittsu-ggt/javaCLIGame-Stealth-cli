package start;

import consolegui.*;
import master.*;

public class MainMenuController {
    private class defaultcolor{
        public int backGroundColor;
        public int wordColor;
    }
    Game master;
    
    private MainScene mainScene;
    private RankingScene rankingScene;
    private defaultcolor defaultcolor;
    private HowToPlayScene howToPlayScene;

    public MainMenuController(Game master) {
        this.master = master;
        this.defaultcolor = new defaultcolor();
        this.defaultcolor.backGroundColor = master.view.defaultBackGroundColor;
        this.defaultcolor.wordColor = master.view.defaultWordColor;
    }

    public boolean run(){
        master.view.defaultBackGroundColor = CColor.WHITE;
        master.view.defaultWordColor = CColor.WHITE;
        boolean isEnd = false;
        while(true){
            this.mainScene = new MainScene(this);
            int select = mainScene.run();
            mainScene = null;
            if(select == 0){
                isEnd = false;
                break;
            }
            else if(select == 1){
                this.rankingScene = new RankingScene(this);
                rankingScene.run();
                rankingScene = null;
            }
            else if(select == 2){
                this.howToPlayScene = new HowToPlayScene(this);
                howToPlayScene.run();
                howToPlayScene = null;
            }
            else if(select == 3){
                isEnd = true;
                break;
            }else{
                throw new RuntimeException("MainMenuController : 不正な操作です．Select = " + select);
            }
        }
        master.view.defaultBackGroundColor = defaultcolor.backGroundColor;
        master.view.defaultWordColor = defaultcolor.wordColor;
        return isEnd;        
    }

    public void destroy(){
        mainScene = null;
        rankingScene = null;
        howToPlayScene = null;
    }


}
