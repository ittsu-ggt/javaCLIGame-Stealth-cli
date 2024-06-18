package start;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import consolegui.CDisplay;
import master.Game;


public class HowToPlaySceneTest {
    public static Game Testmaster = new Game();

    public void HowToPlaySceneの生成テスト(){
        MainMenuController manager = new MainMenuController(Testmaster);
        HowToPlayScene scene = new HowToPlayScene(manager);
    }
}
