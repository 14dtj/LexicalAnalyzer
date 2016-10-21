import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tjDu on 2016/10/21.
 */
public class LexicalData {
    public static String rwPath = "file/reserved_word.txt";
    public static String opPath = "file/operator.txt";
    private Map<String, String> reservedWordMap;
    private Map<String, String> operatorMap;

    public LexicalData() {
        reservedWordMap = new HashMap<>();
        operatorMap = new HashMap<>();
        initMaps(reservedWordMap, rwPath);
        initMaps(operatorMap, opPath);
    }

    public int isReservedWord(String str) {
        return getId(str, reservedWordMap);
    }

    public int isOperator(String str) {
        return getId(str, operatorMap);
    }

    private void initMaps(Map<String, String> map, String path) {
        ArrayList<String> list = IOHelper.readFileByLine(path);
        for (String str : list) {
            String[] strs = str.split(" ");
            map.put(strs[0], strs[1]);
        }
    }

    private int getId(String str, Map<String, String> map) {
        String value = map.get(str);
        int result = Integer.parseInt(value);
        return result;
    }

}
