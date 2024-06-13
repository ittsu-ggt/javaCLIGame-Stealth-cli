package ranking;

import java.text.SimpleDateFormat;

public class RankingCell implements Comparable<RankingCell> {
    private int score;
    private long time;

    public RankingCell(int score, long time) {
        this.score = score;
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public long getTime() {
        return time;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return score + "," + sdf.format(time);
    }

    @Override
    public int compareTo(RankingCell other) {
        if (this.score == other.score) {
            return -1*Long.compare(this.time, other.time);
        } else {
            return Integer.compare(this.score, other.score);
        }
    }
}
