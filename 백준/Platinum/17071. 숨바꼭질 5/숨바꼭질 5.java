import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] splitedLine = in.readLine().split(" ");
        N = Integer.parseInt(splitedLine[0]);
        K = Integer.parseInt(splitedLine[1]);

        int[][] time = new int[2][500001];
        time[0][N] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);

        if (N == K) {
            System.out.println("0");
            return;
        }
        int sec = 0;
        while (!q.isEmpty()) {
            sec++;
            int size = q.size();
            while (size-- > 0) {
                int cur = q.poll();

                int prev = cur - 1;
                int next = cur + 1;
                int jump = cur * 2;

                if (0 <= prev && prev <= 500000) {
                    if (time[sec % 2][prev] == 0) {
                        time[sec % 2][prev] = time[(sec + 1) % 2][cur] + 1;
                        q.add(prev);
                    }
                }
                if (0 <= next && next <= 500000) {
                    if (time[sec % 2][next] == 0) {
                        time[sec % 2][next] = time[(sec + 1) % 2][cur] + 1;
                        q.add(next);
                    }
                }
                if (0 <= jump && jump <= 500000) {
                    if (time[sec % 2][jump] == 0) {
                        time[sec % 2][jump] = time[(sec + 1) % 2][cur] + 1;
                        q.add(jump);
                    }
                }
            }
        }

        for (int i = 0; i < 500001; ++i) {
            int pos = K + i * (i + 1) / 2;
            if (pos > 500000) {
                System.out.println("-1");
                return;
            }
            if (time[i % 2][pos] <= i) {
                System.out.println(i);
                return;
            }
        }
    }
}