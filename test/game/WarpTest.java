package game;

import org.junit.Test;

import master.Game;

public class WarpTest {
    public Game Testmaster = new Game();

    @Test
    public void Warpの生成テスト()throws Exception{
        Warp map = new Warp(new GameScene(Testmaster),0,0);
    }
}
