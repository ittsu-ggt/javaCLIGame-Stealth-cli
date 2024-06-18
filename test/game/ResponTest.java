package game;

import org.junit.Test;

import master.Game;

public class ResponTest {
    public Game Testmaster = new Game();

    @Test
    public void Responの生成テスト()throws Exception{
        Respon map = new Respon(new GameScene(Testmaster),0,0);
    }
}
