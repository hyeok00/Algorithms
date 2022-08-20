import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Solution {
    static StringBuilder sb;
    static int[] card1;
    static int[] card2;
    static int[] arr;
    static boolean[] visitArr;
    static boolean visit[];
    final static int MAXCOUNT = 3628880;
    static int winCount;
    static int loseCount;
 
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());
 
        for (int tc = 1; tc <= T; ++tc) {
            winCount = 0;
            loseCount = 0;
            String[] spiltedLine = in.readLine().split(" ");
 
            card1 = new int[9];
            card2 = new int[9];
            arr = new int[9];
            visitArr = new boolean[9];
            visit = new boolean[18];
 
            for (int i = 0; i < 9; ++i) {
                int number = Integer.parseInt(spiltedLine[i]);
                card1[i] = number;
                visit[number - 1] = true;
            }
            int idx = 0;
            for (int i = 0; i < 18; ++i) {
                if (visit[i] == false) {
                    arr[idx] = i + 1;
                    idx++;
                }
            }
            perm(0, 9);
            sb.append("#" + tc + " " + winCount + " " + loseCount + "\n");
        }
        System.out.println(sb);
    }
 
    public static void perm(int idx, int depth) {
        if (0 == depth) {
            int score1 = 0;
            int score2 = 0;
            for (int i = 0; i < 9; ++i) {
                if (card1[i] > card2[i]) {
                    score1 = score1 + card1[i] + card2[i];
                } else {
                    score2 = score2 + card1[i] + card2[i];
                }
            }
            if (score1 > score2) {
                winCount++;
            }else if(score1<score2){
                loseCount++;
            }
//          for (int i = 0; i < 9; ++i) {
//              System.out.print(card2[i] + " ");
//          }
//          System.out.println();
            return;
        }
        for (int i = 0; i < 9; ++i) {
            if (visitArr[i] == false) {
                visitArr[i] = true;
                card2[idx] = arr[i];
                perm(idx + 1, depth - 1);
                visitArr[i] = false;
            }
        }
    }
}