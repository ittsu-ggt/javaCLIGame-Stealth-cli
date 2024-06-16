package master;
import ranking.*;

public class Result {
    public boolean isClear;
    public RankingCell rankingCell;
    public int num;

    public Result(boolean isClear, int score, int num, long time) {
        this.isClear = isClear;
        this.rankingCell = new RankingCell(score, time);
        this.num = num;
    }
}
