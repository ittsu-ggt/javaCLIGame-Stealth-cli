package game;

import consolegui.*;

public class StatusLog {
    GameScene manager;

    CObject popup;
    StringService title;
    StringService time;
    StringService hp;

    /**
     * ステータスログのコンストラクター
     * 
     * @param manager シーンの管理モジュール
     */
    public StatusLog(GameScene manager) {
        this.manager = manager;
        popup = new CObject(manager.master.view, "popup",
                SpriteBuildService.BuildModel("./data/costume/statuslog/popup.txt",
                        "./data/costume/statuslog/popupfgcolor.txt",
                        "./data/costume/statuslog/popupbgcolor.txt"),
                0, 0, false);
        title = new StringService(manager.master.view, "title", 0, 0, CColor.BLACK, CColor.WHITE, true);
        time = new StringService(manager.master.view, "00:00", 0, 0, CColor.BLACK, CColor.WHITE, true);
        hp = new StringService(manager.master.view, "⭐⭐⭐", 0, 0, CColor.BLACK, CColor.WHITE, true);
        popup.SetLocation(
                manager.master.view.getCameraX() + manager.master.view.getWidth()
                        - popup.GetCostumeData().get(0).size()
                        - 1,
                manager.master.view.getCameraY() + 1);
        popup.IsVisible = true;
    }

    /**
     * ステータスログの更新処理
     * 
     * @param _hp   プレイヤーのHP
     * @param _time 経過時間の文字列
     */
    public void refresh(int _hp, String _time) {
        popup.SetLocation(
                manager.master.view.getCameraX() + manager.master.view.getWidth()
                        - popup.GetCostumeData().get(0).size()
                        - 1,
                manager.master.view.getCameraY() + 1);
        if (Math.pow(manager.player.X-popup.getX(),2)+Math.pow(manager.player.Y-popup.getY(),2)<=400){
            popup.SetLocation(manager.master.view.getCameraX()+1, manager.master.view.getCameraY()+1);
        }

        title.SetLocation(popup.X + 2, popup.Y + 2);
        time.SetLocation(popup.X + 2, popup.Y + 3);
        hp.SetLocation(popup.X + 2, popup.Y + 4);
        time.ChangeString("TIME:" + _time);
        String strtmp = "";
        for (int i = 0; i < _hp; i++) {
            strtmp += "⭐";
        }
        hp.ChangeString("HP:" + strtmp);
        popup.ChangeDrawingOrder(-1);
        title.ChangeDrawingOrder(-1);
        time.ChangeDrawingOrder(-1);
        hp.ChangeDrawingOrder(-1);

    }
}
