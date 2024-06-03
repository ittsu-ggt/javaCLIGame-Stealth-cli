package master;

import java.io.IOException;
import consolegui.*;
import game.*;

public class Game {

    public CDisplay view;
    public KeyBoardService key;
    public CDebug debug;

    /**
     * アプリケーション管理モジュールのコンストラクター
     */
    public Game() {
        this.view = new CDisplay(100, 50, 1, 8, true, true);
        this.key = new KeyBoardService(true);
        this.debug = new CDebug(this.view, 0, 0, 1);
    }

    /**
     * アプリケーションの実行
     */
    public void run() throws InterruptedException, IOException {
        GameScene master = new GameScene(this);
        master.run();
    }
}
