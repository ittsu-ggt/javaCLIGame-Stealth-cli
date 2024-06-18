package game;

import org.junit.Test;

import master.Game;

public class StatusLogTest {
    public Game Testmaster = new Game();

    @Test
    public void StatusLogの生成テスト()throws Exception{
        StatusLog map = new StatusLog(new GameScene(Testmaster));
    }
}
