package master;

import java.io.IOException;
import consolegui.*;
import game.*;
import result.*;

public class Game {

    public CDisplay view;
    public KeyBoardService key;
    public CDebug debug;

    /**
     * アプリケーション管理モジュールのコンストラクター
     */
    public Game() {
        this.view = new CDisplay(100, 50, CColor.WHITE, CColor.BLACK, true, true);
        this.key = new KeyBoardService(true);
        this.debug = new CDebug(this.view, 0, 0, 1);
    }

    /**
     * アプリケーションの実行
     */
    public void run() throws InterruptedException, IOException {
        // GameScene master = new GameScene(this);
        // Result gameresult = master.run();
        Result gameresult = new Result(true, 100,15,100);
        ResultScene result = new ResultScene(this, gameresult);
        result.run();

    }
}
