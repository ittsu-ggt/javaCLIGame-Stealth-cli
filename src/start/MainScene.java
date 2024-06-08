package start;

import consolegui.*;

public class MainScene {

    private class button {
        public StringService playButton = new StringService(manager.master.view, "ゲーム開始", 40, 30, CColor.BLACK, 0, true);
        public StringService rankingButton = new StringService(manager.master.view, "ランキング", 40, 33, CColor.BLACK, 0, true);
        public StringService howToPlayButton = new StringService(manager.master.view, "遊び方", 40, 36, CColor.BLACK, 0, true);
        public StringService exitButton = new StringService(manager.master.view, "ゲームの終了", 40, 39, CColor.BLACK, 0, true);
        public int selected = 0;
        private void RemoveMe(){
            playButton.RemoveMe();
            rankingButton.RemoveMe();
            howToPlayButton.RemoveMe();
            exitButton.RemoveMe();
            playButton = null;
            rankingButton = null;
            howToPlayButton = null;
            exitButton = null;
        }
    }
    private class title{
        public CObject logo = new CObject(manager.master.view,SpriteBuildService.BuildModel("./data/costume/title/ItemCollector.txt",CColor.CYAN,CColor.CYAN),10,3,true);
        public int direction = -1;
        public int frame = 0;
        private void RemoveMe(){
            logo.RemoveMe();
            logo = null;
        }
    }


    public MainMenuController manager;
    private button buttons;
    private title title;
    public StringService operationExplain;



    public MainScene(MainMenuController manager){
        this.manager = manager;
        this.buttons = new button();
        this.title = new title();
        operationExplain = new StringService(manager.master.view, "上下キーで選択・スペースキーで決定", 60, 48, CColor.BLACK, 0, true);
    }

    public int run(){
        manager.master.view.SetLocation(0, 0);
        while (true) {
            titleAnimation();
            if(buttonOperation()){
                return decideButtonAnimation();
            }
            buttonAnimation();
            manager.master.view.Update();
            sleep(100);
        }
    }
    private void buttonAnimation(){
        buttons.playButton.ChangeColor(CColor.BLACK,0);
        buttons.rankingButton.ChangeColor(CColor.BLACK,0);
        buttons.howToPlayButton.ChangeColor(CColor.BLACK,0);
        buttons.exitButton.ChangeColor(CColor.BLACK,0);
        switch (buttons.selected){
            case 0:
                buttons.playButton.ChangeColor(CColor.CYAN,0);
                break;
            case 1:
                buttons.rankingButton.ChangeColor(CColor.CYAN,0);
                break;
            case 2:
                buttons.howToPlayButton.ChangeColor(CColor.CYAN,0);
                break;
            case 3:
                buttons.exitButton.ChangeColor(CColor.CYAN,0);
                break;
            default:
                throw new IllegalStateException("Main Secene : 不正な操作です．Select = " + buttons.selected);
        }
    }

    private int decideButtonAnimation(){
        StringService selectedButton = null;
        switch (buttons.selected){
            case 0:
                selectedButton = buttons.playButton;
                break;
            case 1:
                selectedButton = buttons.rankingButton;
                break;
            case 2:
                selectedButton = buttons.howToPlayButton;
                break;
            case 3:
                selectedButton = buttons.exitButton;
                break;
            default:
                throw new IllegalStateException("Main Secene : 不正な操作です．Select = " + buttons.selected);
        }
        selectedButton.ChangeColor(CColor.RED,0);
        manager.master.view.Update();
        sleep(500);
        selectedButton.ChangeColor(CColor.BLACK,0);
        manager.master.view.Update();
        sleep(500);
        int t = buttons.selected;
        destroy();
        return t;
    }

    private boolean buttonOperation(){
        if(manager.master.key.isKeyPressed('↑') || manager.master.key.isKeyPressed('w')){
            if(buttons.selected>0)buttons.selected--;
        }
        if(manager.master.key.isKeyPressed('↓') || manager.master.key.isKeyPressed('s')){
            if(buttons.selected<3)buttons.selected++;
        }
        if(manager.master.key.isKeyPressed(' ')){
            return true;
        }
        return false;
    }

    private void titleAnimation(){
        if (title.frame == 0) {
            title.logo.MoveLocation(0, title.direction);
        }
        if (title.logo.getY() >= 3) {
            title.direction = -1;
        }
        if (title.logo.getY() <= 1) {
            title.direction = 1;
        }
        title.frame++;
        if (title.frame >= 10) {
            title.frame = 0;
        }
    }

    public void destroy(){
        buttons.RemoveMe();
        title.RemoveMe();
        operationExplain.RemoveMe();
        buttons = null;
        title = null;
        operationExplain = null;
        
    }

    private void sleep(int t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
