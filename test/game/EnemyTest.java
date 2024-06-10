package game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import consolegui.CDisplay;
import master.Game;

public class EnemyTest {
    public static Game Testmaster = new Game();


    @Test
    public void 敵の生成() throws Exception{
        GameScene Testmanager = new GameScene(Testmaster);
        Enemy enemy = new Enemy(Testmanager,0,0,true,false);
        assertEquals(enemy.X,0);
        assertEquals(enemy.Y,0);
    }

}
