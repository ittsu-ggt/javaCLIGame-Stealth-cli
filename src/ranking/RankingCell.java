public class RankingCell {
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
        
        return  score + " , " + time;
    }
}
