import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tjDu on 2016/10/20.
 */
public class IOHelper {
    public static ArrayList<String> readFileByLine(String path) {
        File file = new File(path);
        FileReader fileReader;
        ArrayList<String> dataList = null;
        BufferedReader br;
        try {
            fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            dataList = new ArrayList<>();
            String temp;
            while ((temp = br.readLine()) != null) {
                dataList.add(temp);
            }
            br.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static void writeFile(Map<String, String> map) {
        String path = "file/output.txt";
        File file = new File(path);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                writer.write(iterator.next().toString());
                writer.newLine();
            }
            writer.flush();
            writer.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Character> readFileByChar(String path) {
        ArrayList<String> temp = readFileByLine(path);
        List<Character> result = new ArrayList<>();
        for (String str : temp) {
            for (char c : str.toCharArray())
                result.add(c);
        }
        return result;
    }
}
