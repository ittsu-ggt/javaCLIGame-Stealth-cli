package ranking;

import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import master.*;

public class RankingManager {
    private static Game master;
    private static final String FILE_NAME = "./data/ranking/ranking.txt";

    public static void SetMaster(Game master) {
        RankingManager.master = master;
    }

    public static ArrayList<RankingCell> DataRoad(String FilePath) {
        Path File_Path = Path.of(FilePath);
        try{
            List<String> lines = Files.readAllLines(File_Path, StandardCharsets.UTF_8);
            ArrayList<RankingCell> data = new ArrayList<RankingCell>();
            for (String line : lines) {
                String[] tmp = line.split(",");
                int score = Integer.parseInt(tmp[0]);
                long time = Long.parseLong(tmp[1]);
                data.add(new RankingCell(score, time));
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            Path tmp = File_Path.toAbsolutePath();
            throw new RuntimeException(
                    "RankingManager : ファイルの読み込みに失敗しました．ファイル名 : " + tmp.toString());
        }
    }
    public static boolean DataSave(RankingCell data) {
        return DataSave(data, FILE_NAME);
    }
    public static ArrayList<RankingCell> DataRoad() {
        return DataRoad(FILE_NAME);
    }

    public static boolean DataSave(RankingCell data, String FilePath) {
        Path File_Path = Path.of(FilePath);
        ArrayList<RankingCell> table = DataRoad(FilePath);
        Collections.sort(table, Collections.reverseOrder());
        boolean RewriteRecord = false;
        for(int i=0;i<5;i++){
            if(table.get(i).compareTo(data)<=0){
                RewriteRecord = true;
            }
        }
        table.add(data);
        Collections.sort(table, Collections.reverseOrder());
        // 上から５つを残す
        if (table.size() > 5) {
            table.remove(5);
        }
        Collections.sort(table, Collections.reverseOrder());
        List<String> writeData = new ArrayList<String>();
        for (RankingCell line : table) {
            writeData.add(line.getScore()+","+line.getTime());
        }
        FileWriter(FilePath, writeData);
        return RewriteRecord;
    }

    private static void FileWriter(String FilePath, List<String> lines) {
        Path File_Path = Path.of(FilePath);
        try {
            Files.write(File_Path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            Path tmp = File_Path.toAbsolutePath();
            throw new RuntimeException(
                    "RankingManager : ファイルの書き込みに失敗しました．ファイル名 : " + tmp.toString());
        }
    }

}
