package game;
import master.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import consolegui.CDebug;
import consolegui.CDisplay;
import consolegui.KeyBoardService;

public class GameSceneTest {
    public Game Testmaster = new Game(){
        {
        this.view = new CDisplay(100,50,1,8,true,true);
        this.key= new KeyBoardService(true);
        this.debug = new CDebug(this.view,0,0,10);
        }
    };


    @Test
    public void カレントディレクトリの確認() throws Exception{
        Path p1 = Paths.get("");
        Path p2 = p1.toAbsolutePath();
        System.out.println("カレントディレクトリの確認中");
        System.out.println(p2.toString());
        assertTrue(true);
    }

    @Test
    public void プレイヤーの生成テスト() throws Exception{
        GameScene master = new GameScene(Testmaster);
        assertEquals(master.player.X,5);
        assertEquals(master.player.Y,5);
        assertEquals(master.player.IsVisible,true);
    }

}
