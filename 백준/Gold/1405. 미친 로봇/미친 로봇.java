import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] rate = {0, 0, 0, 0};
    static double result;
    static boolean visit[][];

    static class Point {
        int x;
        int y;

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    // 동서남북 순
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] splitedLine = in.readLine().split(" ");
        N = stoi(splitedLine[0]);
        rate[0] = stoi(splitedLine[1]);
        rate[1] = stoi(splitedLine[2]);
        rate[2] = stoi(splitedLine[3]);
        rate[3] = stoi(splitedLine[4]);

        Point start = new Point(N, N);
        visit = new boolean[2 * N + 1][2 * N + 1];
        visit[start.x][start.y] = true;
        result = 0L;
        for (int d = 0; d < 4; ++d) {
            int nextX = start.x + dx[d];
            int nextY = start.y + dy[d];
            Point nextPoint = new Point(nextX, nextY);
            // 해당 방향으로 갈 수 있는 확률이 존재하고,
            // 진행 방향에 처음 가는것이라면 가본다.
            if (rate[d] != 0 && isInRange(nextX, nextY) && !visit[nextX][nextY]) {
                visit[nextX][nextY] = true;
                simulate(nextPoint, 1 , rate[d] * 0.01);
                visit[nextX][nextY] = false;
            }
        }
        System.out.println(result);
    }

    private static boolean isInRange(int nextX, int nextY) {
        if (0 <= nextX && nextX <= 2 * N + 1 && 0 <= nextY && nextY <= 2 * N + 1)
            return true;
        return false;
    }

    private static void simulate(Point current, int depth, double res) {
        if (depth == N) {
            result += res;
            return;
        }

        for (int d = 0; d < 4; ++d) {
            int nextX = current.x + dx[d];
            int nextY = current.y + dy[d];
            Point nextPoint = new Point(nextX, nextY);
            if (rate[d] != 0 && isInRange(nextX, nextY) && !visit[nextX][nextY]) {
                visit[nextX][nextY] = true;
                simulate(nextPoint, depth + 1, res * rate[d] * 0.01);
                visit[nextX][nextY] = false;
            }
        }
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}