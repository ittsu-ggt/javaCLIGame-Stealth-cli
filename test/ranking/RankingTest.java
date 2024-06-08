package ranking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import consolegui.CDebug;
import consolegui.CDisplay;
import consolegui.KeyBoardService;
import master.*;
import ranking.*;

public class RankingTest {
    @Test
    public void RankingCellの生成テスト() throws Exception {
        int score = 100;
        long time = 1000;
        RankingCell rankingCell = new RankingCell(score, time);
        assertEquals(score, rankingCell.getScore());
        assertEquals(time, rankingCell.getTime());
    }

    @Test
    public void RankingCellのtoStringテスト() throws Exception {
        int score = 100;
        long time = 1000;
        RankingCell rankingCell = new RankingCell(score, time);
        assertEquals("100,1000", rankingCell.toString());
        assertEquals("100,1000", rankingCell+"");
    }

    @Test
    public void RankingCellの比較テスト() throws Exception {
        RankingCell rankingCell1 = new RankingCell(100, 1000);
        RankingCell rankingCell2 = new RankingCell(100, 1000);
        assertEquals(0, rankingCell1.compareTo(rankingCell2)); // 同じ
        rankingCell2 = new RankingCell(100, 1001); // スコアは同じだが，時間が大きい．つまりランキングが下がる
        assertEquals(1, rankingCell1.compareTo(rankingCell2));
        rankingCell2 = new RankingCell(101, 1000); // スコアが大きい．つまりランキングが上がる
        assertEquals(-1, rankingCell1.compareTo(rankingCell2));
        rankingCell2 = new RankingCell(99, 1000); // スコアが小さい．つまりランキングが下がる
        assertEquals(1, rankingCell1.compareTo(rankingCell2));
        rankingCell2 = new RankingCell(101, 1001); // スコアも時間も大きい．つまりランキングが上がる
        assertEquals(-1, rankingCell1.compareTo(rankingCell2));
        rankingCell2 = new RankingCell(99, 999); // スコアも時間も小さい．つまりランキングが下がる
        assertEquals(1, rankingCell1.compareTo(rankingCell2));
    }

    @Test 
    public void RankingのDataRoadのテスト() throws Exception {
        var r = RankingManager.DataRoad("./testdata/ranking/testfile1.txt");
        String ans = "500,10000\n" +
                        "200,800\n" +
                        "200,950\n" +
                        "150,1000\n" +
                        "100,1000\n" ;
        String res = "";
        for (var i : r) {
            res += i.toString() + "\n";
        }
        assertEquals(ans, res);
        
    }

    @Test
    public void RankingのDataSaveのテスト() throws Exception {
        RankingCell rankingCell = new RankingCell(120, 1000);
        RankingManager.DataSave(rankingCell, "./testdata/ranking/testfile2.txt");
        var r = RankingManager.DataRoad("./testdata/ranking/testfile2.txt");
        String ans = "500,10000\n" +
                        "200,800\n" +
                        "200,950\n" +
                        "150,1000\n" +
                        "120,1000\n" ;
        String res = "";
        for (var i : r) {
            res += i.toString() + "\n";
        }
        assertEquals(ans, res);
    }

}
