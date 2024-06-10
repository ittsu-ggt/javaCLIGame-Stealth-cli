package game;

import master.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import consolegui.*;

public class GameSceneTest {
    public static Game Testmaster = new Game();

    @Test

    public void カレントディレクトリの確認() throws Exception{
        Path p1 = Paths.get("");
        Path p2 = p1.toAbsolutePath();
        System.out.println("カレントディレクトリの確認中");
        System.out.println(p2.toString());
        assertTrue(true);
    }

      

    @Test

    public void プレイヤーが生成されているか() throws Exception{
        GameScene master = new GameScene(Testmaster);
        assertEquals(master.player.X,6);
        assertEquals(master.player.Y,9);
        assertEquals(master.player.IsVisible,true);
    }

      

    @Test
    public void 敵が生成されているか() throws Exception{
        GameScene master = new GameScene(Testmaster);
        assertEquals(master.enemies.size(),13);
    }

      

    @Test
    public void アイテムが生成されているか()throws Exception{
        GameScene master = new GameScene(Testmaster);
        assertEquals(master.items.size(),15);
    }

      

    @Test
    public void リスポーン地点が生成されているか() throws Exception{
        GameScene master = new GameScene(Testmaster);
        assertEquals(master.respons.size(),6);
    }
      

    @Test 
    public void このシーン実行に必要なファイルが存在するか() throws Exception{
        Path p1 = Paths.get("./data/costume/enemy/def.txt");
        Path p2 = Paths.get("./data/costume/enemy/left.txt");
        Path p3 = Paths.get("./data/costume/enemy/lower.txt");
        Path p4 = Paths.get("./data/costume/enemy/right.txt");
        Path p5 = Paths.get("./data/costume/enemy/sensorHitWall1.txt");
        Path p6 = Paths.get("./data/costume/enemy/sensorHitWall2.txt");
        Path p7 = Paths.get("./data/costume/enemy/sensorPlayer.txt");
        Path p8 = Paths.get("./data/costume/enemy/sensorPlayer2.txt");
        Path p9 = Paths.get("./data/costume/enemy/upper.txt");
        Path p10 = Paths.get("./data/costume/item/item.txt");
        Path p11 = Paths.get("./data/costume/map/map.txt");
        Path p12 = Paths.get("./data/costume/map/mapbgcolor.txt");
        Path p13 = Paths.get("./data/costume/map/mapfgcolor.txt");
        Path p14 = Paths.get("./data/costume/player/def.txt");
        Path p15 = Paths.get("./data/costume/player/left.txt");
        Path p16 = Paths.get("./data/costume/player/lower.txt");
        Path p17 = Paths.get("./data/costume/player/right.txt");
        Path p18 = Paths.get("./data/costume/player/upper.txt");
        Path p19 = Paths.get("./data/costume/respon/respon.txt");
        Path p20 = Paths.get("./data/costume/result_text/gameclear.txt");
        Path p21 = Paths.get("./data/costume/result_text/gameover.txt");
        assertTrue(p1.toFile().exists());
        assertTrue(p2.toFile().exists());
        assertTrue(p3.toFile().exists());
        assertTrue(p4.toFile().exists());
        assertTrue(p5.toFile().exists());
        assertTrue(p6.toFile().exists());
        assertTrue(p7.toFile().exists());
        assertTrue(p8.toFile().exists());
        assertTrue(p9.toFile().exists());

        assertTrue(p10.toFile().exists());
        assertTrue(p11.toFile().exists());
        assertTrue(p12.toFile().exists());
        assertTrue(p13.toFile().exists());
        assertTrue(p14.toFile().exists());
        assertTrue(p15.toFile().exists());
        assertTrue(p16.toFile().exists());
        assertTrue(p17.toFile().exists());
        assertTrue(p18.toFile().exists());
        assertTrue(p19.toFile().exists());
        assertTrue(p20.toFile().exists());
        assertTrue(p21.toFile().exists());
        
    }

}