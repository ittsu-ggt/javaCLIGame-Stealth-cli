package start;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import consolegui.CDisplay;
import master.Game;

public class MainMenuControllerTest {
    public static Game Testmaster = new Game();

    public void MainMenuControllerの生成テスト(){
        MainMenuController manager = new MainMenuController(Testmaster);
        HowToPlayScene scene = new HowToPlayScene(manager);
    }
}
