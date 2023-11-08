import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = stoi(in.readLine());

        for (int i = 0; i < N; ++i) {
            String str = in.readLine();
            int result = isPalindrome(0, str.length() - 1, str, false);
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    private static int isPalindrome(int left, int right, String str, boolean flag) {
        while (true) {
            if (left >= right)
                break;
            if (str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            } else {
                if (flag == false) {
                    int leftPass = isPalindrome(left + 1, right, str, true);
                    int rightPass = isPalindrome(left, right - 1, str, true);
                    if (leftPass == 0 || rightPass == 0)
                        return 1;
                    else
                        return 2;
                } else
                    return 2;
            }
        }
        return 0;
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}