package master;

/**
 * シーンインターフェース
 */
public interface IScene {
    private Game game;

    /**
     * シーンが呼び出されたときの処理を追加する
     */
    public void run(Game game);

}
