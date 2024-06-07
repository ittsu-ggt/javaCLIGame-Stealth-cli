package ranking;

import java.util.Collection;

public class RankingManager {
    private static final String FILE_NAME = "./data/ranking/ranking.txt";

    public static ArrayList<Integer> DataRoad(){
        List<String> lines = FileReader(FILE_NAME);
        ArrayList<Integer> data = new ArrayList<Integer>();
        for (String line : lines) {
            data.add(Integer.parseInt(line));
        }
        return DataSort(data);
    }

    public static boolean DataSave(int data){
        List<Integer> lines = DataRoad();
        lines.add(data);
        // 上から５つを残す
        Collections.sort(lines);
        if(lines.size() > 5){
            lines.remove(5);
        }
        FileWriter(FILE_NAME, DataSort(lines));
        return;
    }

    private static ArrayList<Integer> DataSort(Array<Integer> data){
        Collections.sort(data);
    }

    private static List<String> FileReader(String FilePath) {
        Path File_Path = Path.of(FilePath);
        try {
            List<String> lines = Files.readAllLines(File_Path, StandardCharsets.UTF_8);

            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            Path tmp = File_Path.toAbsolutePath();
            throw new IllegalArgumentException(
                    SpriteBuildService.class.getName() + " : ファイルの読み込みに失敗しました．ファイル名 : " + tmp.toString());
        }
    }

    private static void FileWriter(String FilePath, List<String> lines) {
        Path File_Path = Path.of(FilePath);
        try {
            Files.write(File_Path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            Path tmp = File_Path.toAbsolutePath();
            throw new IllegalArgumentException(
                    SpriteBuildService.class.getName() + " : ファイルの書き込みに失敗しました．ファイル名 : " + tmp.toString());
        }
    }

    
}
