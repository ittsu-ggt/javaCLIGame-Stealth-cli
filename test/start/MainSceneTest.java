package start;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import consolegui.CDisplay;
import master.Game;


public class MainSceneTest {
    public static Game Testmaster = new Game();

    public void MainSceneの生成テスト(){
        MainMenuController manager = new MainMenuController(Testmaster);
        MainScene scene = new MainScene(manager);
    }
}
