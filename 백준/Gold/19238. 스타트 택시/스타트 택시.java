import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static class Point implements Comparable<Point> {
        int x;
        int y;

        Point(int a, int b) {
            x = a;
            y = b;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x)
                return this.y - o.y;
            return this.x - o.x;
        }
    }

    static int N, M, fuel, count;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static int map[][];
    static int guessMin[];
    static Point taxi;
    static Point[] psrc, pdest;
    final static int key = 98765;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] splitedLine = in.readLine().split(" ");
        N = stoi(splitedLine[0]);
        M = stoi(splitedLine[1]);
        fuel = stoi(splitedLine[2]);

        map = new int[N][N];
        for (int i = 0; i < N; ++i) {
            splitedLine = in.readLine().split(" ");
            for (int j = 0; j < N; ++j) {
                map[i][j] = stoi(splitedLine[j]);
                // 벽을 -1로 기록
                if (map[i][j] == 1)
                    map[i][j] = -1;
            }
        }

        splitedLine = in.readLine().split(" ");
        taxi = new Point(stoi(splitedLine[0]) - 1, stoi(splitedLine[1]) - 1);

        guessMin = new int[M + 1];
        psrc = new Point[M + 1];
        pdest = new Point[M + 1];
        for (int i = 0; i < M; ++i) {
            splitedLine = in.readLine().split(" ");
            int startX = stoi(splitedLine[0]) - 1;
            int startY = stoi(splitedLine[1]) - 1;
            int endX = stoi(splitedLine[2]) - 1;
            int endY = stoi(splitedLine[3]) - 1;

            map[startX][startY] = i + 1;
            psrc[i + 1] = new Point(startX, startY);
            pdest[i + 1] = new Point(endX, endY);
            guessMin[i + 1] = Math.abs(startX - endX) + Math.abs(startY - endY);
        }

        simulation();

        if (count == M) // 모두 이동시켰다면 남은 연료의 양
            System.out.println(fuel);
        else // 모두 이동시키지 못한 경우
            System.out.println(-1);
    }

    private static void simulation() {
        while (fuel > 0) {
            if(count ==M)
                break;
            // 가장 가까운 승객을 찾고 해당 위치로 이동한다.
            int passNum = findPassenger();
            if (passNum == -1) {
                fuel = -1;
                return;
            }
            // 승객을 이동한다.
            int dist = getDistance(passNum, taxi, pdest[passNum]);
            if (dist == -1)
                return;
            count++;
        }
    }

    private static int getDistance(int passNum, Point src, Point des) {
        if (src.x == des.x && src.y == des.y)
            return 0;

        Queue<Point> q = new ArrayDeque<>();
        boolean visit[][] = new boolean[N][N];

        visit[taxi.x][taxi.y] = true;
        q.add(taxi);

        int dist = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            dist++;

            // 승객까지의 거리가 남은 연료량보다 크다.
            if (fuel < dist)
                break;

            while (size-- > 0) {
                Point p = q.poll();
                for (int d = 0; d < 4; ++d) {
                    int nextX = p.x + dx[d];
                    int nextY = p.y + dy[d];
                    if (isInRange(nextX, nextY) && map[nextX][nextY] != -1 && visit[nextX][nextY] == false) {
                        // 범위 내부면서, 벽이 아니고, 방문한적이 없다면
                        if (pdest[passNum].x == nextX && pdest[passNum].y == nextY) {
                            // 도착지 도착
                            fuel += dist;
                            taxi.x = nextX;
                            taxi.y = nextY;
                            return dist;
                        } else {
                            q.add(new Point(nextX, nextY));
                            visit[nextX][nextY] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static int findPassenger() {
        if (map[taxi.x][taxi.y] > 0){
            int nextPassNum = map[taxi.x][taxi.y];
            map[taxi.x][taxi.y] = 0;
            return nextPassNum;
        }

        Queue<Point> q = new ArrayDeque<>();
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean visit[][] = new boolean[N][N];

        visit[taxi.x][taxi.y] = true;
        q.add(taxi);

        boolean flag = false;
        int dist = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            dist++;

            // 승객까지의 거리가 남은 연료량보다 크다.
            if (fuel < dist)
                break;

            while (size-- > 0) {
                Point p = q.poll();
                for (int d = 0; d < 4; ++d) {
                    int nextX = p.x + dx[d];
                    int nextY = p.y + dy[d];
                    if (isInRange(nextX, nextY) && map[nextX][nextY] != -1 && visit[nextX][nextY] == false) {
                        // 범위 내부면서, 벽이 아니고, 방문한적이 없다면
                        if (map[nextX][nextY] > 0) {
                            // 누군가의 출발점인 경우
                            flag = true;
                            pq.add(psrc[map[nextX][nextY]]);
                        } else {
                            q.add(new Point(nextX, nextY));
                            visit[nextX][nextY] = true;
                        }
                    }
                }
            }
            // 특정거리에 있는 승객 중 조건에 부합한 승객을 고른다.
            if (flag) {
                Point next = pq.poll();
                fuel -= dist;
                taxi.x = next.x;
                taxi.y = next.y;
                int nextPassNum = map[next.x][next.y];
                map[next.x][next.y] = 0;
                return nextPassNum;
            }
        }
        return -1;
    }

    private static boolean isInRange(int nextX, int nextY) {
        if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N)
            return true;
        return false;
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}