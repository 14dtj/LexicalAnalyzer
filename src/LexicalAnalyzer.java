import java.util.ArrayList;
import java.util.List;

/**
 * Created by tjDu on 2016/10/21.
 */
public class LexicalAnalyzer {
    public String inputFile = "file/test.txt";
    private List<Character> programme;
    private int tempIndex = 0;
    private String key;
    private String token;
    private LexicalData lexicalData;
    public char[] speOperator = {'<', '>', '!', '+', '-', '=', '|', '&'};

    public static void main(String[] args) {
        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        analyzer.execute();
    }

    public void execute() {
        lexicalData = new LexicalData();
        programme = IOHelper.readFileByChar(inputFile);
        List<String> output = new ArrayList<>();
        while (tempIndex < programme.size()) {
            scan();
            output.add(key + ": " + token);
        }
        IOHelper.writeFile(output);
        System.out.println("done");
    }

    public void scan() {
        token = "";
        key = "";
        char temp = programme.get(tempIndex++);
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
                temp = programme.get(tempIndex++);
            }
            tempIndex--;
            key = "ID";
            int tag = lexicalData.isReservedWord(token);
            if (tag > 0) {
                key = "keyword" + tag;
            }
        } else if (isDigit(temp)) {
            while (isDigit(temp)) {
                token += temp;
                if (tempIndex == programme.size()) {
                    break;
                }
                temp = programme.get(tempIndex++);
            }
            if (temp == '.') {
                token += temp;
                temp = programme.get(tempIndex++);
                while (isDigit(temp)) {
                    token += temp;
                    if (tempIndex == programme.size()) {
                        break;
                    }
                    temp = programme.get(tempIndex++);
                }
                key = "double";
            } else {
                key = "int";
            }
            tempIndex--;
        } else {
            if (isSpecialOp(temp)) {
                token += temp;
                if (tempIndex < programme.size()) {
                    char t = programme.get(tempIndex);
                    String s = temp + "";
                    s += t;
                    int tag1 = lexicalData.isOperator(s);
                    if (tag1 > 0) {
                        token += t;
                        key = "operator" + tag1;
                    } else {
                        key = "operator" + lexicalData.isOperator(temp + "");
                    }
                }
            } else {
                int tag = lexicalData.isOperator(temp + "");
                if (tag < 0) {
                    key = "error";
                } else {
                    key = "operator" + tag;
                    token += temp;
                }
            }
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

    private boolean isSpecialOp(char ch) {
        for (int i = 0; i < speOperator.length; i++) {
            if (ch == speOperator[i]) {
                return true;
            }
        }
        return false;
    }
}
