package game;

import org.junit.Test;

import master.Game;

public class PlayerTest {
    public Game Testmaster = new Game();

    @Test
    public void Playerの生成テスト()throws Exception{
        Player map = new Player(new GameScene(Testmaster),0,0,true);
    }
}
