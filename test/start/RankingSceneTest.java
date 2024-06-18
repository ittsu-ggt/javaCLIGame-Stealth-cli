package start;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import consolegui.CDisplay;
import master.Game;


public class RankingSceneTest {
    public static Game Testmaster = new Game();

    public void RankingSceneの生成テスト(){
        MainMenuController manager = new MainMenuController(Testmaster);
        RankingScene scene = new RankingScene(manager);
    }
}
