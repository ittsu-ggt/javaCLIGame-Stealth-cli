package game;

import org.junit.Test;

import consolegui.CDebug;
import consolegui.CDisplay;
import consolegui.KeyBoardService;
import master.Game;

public class MapTest {
    public Game Testmaster = new Game(){
        {
        this.view = new CDisplay(100,50,1,8,true,true);
        this.key= new KeyBoardService(true);
        this.debug = new CDebug(this.view,0,0,10);
        }
    };

    @Test
    public void マップの生成テスト()throws Exception{
        Map map = new Map(new GameScene(Testmaster));
    }

}
