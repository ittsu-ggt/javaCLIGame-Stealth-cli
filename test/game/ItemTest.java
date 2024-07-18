package game;

import org.junit.Test;

import master.Game;

public class ItemTest {
    public Game Testmaster = new Game();

    @Test
    public void Itemの生成テスト()throws Exception{
        Item map = new Item(new GameScene(Testmaster),0,0,true);
    }
}
