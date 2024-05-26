import java.io.IOException;

import consolegui.*;

public class Game  {

    public CDisplay view ;
    public Map map ;
    public KeyBoardService key;
    public Player player;
    public CDebug debug;

    public static void main(String[] args) throws InterruptedException,IOException {
        Game game = new Game();
        game.view = new CDisplay(100,50,1,8,true,true);
        game.map = new Map(game);
        game.key= new KeyBoardService(true);
        game.player = new Player(game,5,5,true);
        game.debug = new CDebug(game.view,0,0,10);
        game.map.SetVisible(true);

        while (true) {
            game.player.Update();
            game.view.Update();
            Thread.sleep(100);
        }
    }

    public static void startGame() {
        System.out.println("Game started!");
    }
}
