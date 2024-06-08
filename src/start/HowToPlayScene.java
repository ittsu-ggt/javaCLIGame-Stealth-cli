package start;

import consolegui.*;

public class HowToPlayScene {
    private class parts {
        public CObject title;
        public CObject playButton;
        public CObject rankingButton;
        public CObject howToPlayButton;
        public CObject exitButton;
    }

    public MainMenuController manager;
    public parts parts;

    public HowToPlayScene(MainMenuController manager){
        this.manager = manager;
        this.parts = new parts();
    }

    public void run(){

    }

    public void destroy(){
        
    }

}
