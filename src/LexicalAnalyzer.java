import java.util.List;

/**
 * Created by tjDu on 2016/10/21.
 */
public class LexicalAnalyzer {
    public String inputFile = "file/test.txt";
    private List<Character> programme;
    private int tempIndex;
    private String key;
    private String token;
    private LexicalData lexicalData;

    public static void main(String[] args) {
        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        analyzer.execute();
    }

    public void execute() {
        lexicalData = new LexicalData();
        programme = IOHelper.readFileByChar(inputFile);
    }

    public void scan() {
        token = "";
        key = "";
        char temp = programme.get(tempIndex);
        while (temp == ' ' || temp == '\n' || temp == '\t') {
            temp = programme.get(tempIndex);
            tempIndex++;
        }

        if (isLetter(temp)) {
            while (isLetter(temp) || isDigit(temp)) {
                token += temp;
                if (tempIndex == programme.size()) {
                    break;
                }
                temp = programme.get(++tempIndex);
            }
            tempIndex--;
            key = "variable";
            int tag = lexicalData.isReservedWord(token);
            if (tag > 0) {
                key = tag + "";
            }
        } else if (isDigit(temp)) {
            while (isDigit(temp)) {
                token += temp;
                if (tempIndex == programme.size()) {
                    break;
                }
                temp = programme.get(++tempIndex);
            }
            if (temp == '.') {
                token += temp;
                temp = programme.get(++tempIndex);
                while (isDigit(temp)) {
                    token += temp;
                    if (tempIndex == programme.size()) {
                        break;
                    }
                    temp = programme.get(++tempIndex);
                }
                key = "double";
            } else {
                key = "int";
            }
            tempIndex--;
        }else{

        }
    }

    private boolean isLetter(char ch) {
        if ((ch >= 'a') && (ch <= 'z') || (ch >= 'A') && (ch <= 'Z')) {
            return true;
        }
        return false;
    }

    private boolean isDigit(char ch) {
        if (ch >= '0' && ch <= '9') {
            return true;
        }
        return false;
    }
}
