package game;

import org.junit.Test;
import master.Game;

public class MapTest {
    public Game Testmaster = new Game(){
    };

    @Test
    public void マップの生成テスト()throws Exception{
        Map map = new Map(new GameScene(Testmaster));
    }

}
