import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Solution {
    static StringBuilder sb;
    final static char PLUS = '+';
    final static char MINUS = '-';
    final static char MULTIPLE = '*';
    final static char DIVIDE = '/';
 
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        final int T = 10;
 
        for (int tc = 1; tc <= T; ++tc) {
            int res = 1;
            int n = Integer.parseInt(in.readLine());
            for (int i = 1; i <= n; ++i) {
                String[] splitedLine = in.readLine().split(" ");
                char c = splitedLine[1].charAt(0);
                if (i <= n / 2) {
                    if (getOperation(c) == false) {
                        res = 0;
                    }
                    // 자식이 있다면 연산자를 가져야함.
                } else {
                    // 자식이 없다면 숫자를 가져야 함.
                    if (getOperation(c) == true) {
                        res = 0;
                    }
                }
            }
 
            sb.append("#" + tc + " " + res + "\n");
        }
        System.out.println(sb);
    }
 
    public static boolean getOperation(char c) {
        boolean res = false;
        switch (c) {
        case PLUS:
        case MINUS:
        case MULTIPLE:
        case DIVIDE:
            res = true;
            break;
 
        default:
            break;
        }
        return res;
    }
}