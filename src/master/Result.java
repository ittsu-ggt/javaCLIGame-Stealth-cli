package master;

import consolegui.*;

import java.sql.Timestamp;

public class Result {
    public boolean isClear;
    public int score;
    public int num;
    public Timestamp time;

    public Result(boolean isClear, int score, int num, long time) {
        this.isClear = isClear;
        this.score = score;
        this.num = num;
        this.time = new Timestamp(time*1000);
    }
}
