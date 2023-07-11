import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int N, M;
    static char[][] map;

    static class Pos implements Comparable<Pos> {
        int x;
        int y;

        Pos(int a, int b) {
            x = a;
            y = b;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.x == o.x) {
                return this.y - o.y;
            } else {
                return this.x - o.x;
            }
        }
    }

    static Pos[][] parent;

    static void union(Pos a, Pos b) {
        a = find(a);
        b = find(b);

        if (a.compareTo(b) > 0)
            parent[b.x][b.y] = a;
        else
            parent[a.x][a.y] = b;
//        if (a.x == b.x) {
//            if (a.y > b.y)
//                parent[b.x][b.y] = a;
//            else
//                parent[a.x][a.y] = b;
//        }
//        else{
//            if (a.x > b.x)
//                parent[b.x][b.y] = a;
//            else
//                parent[a.x][a.y] = b;
//        }
    }

    static Pos find(Pos p) {
        if (parent[p.x][p.y].equals(p))
            return p;
        return parent[p.x][p.y] = find(parent[p.x][p.y]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] splitedLine = in.readLine().split(" ");
        N = Integer.parseInt(splitedLine[0]);
        M = Integer.parseInt(splitedLine[1]);

        map = new char[N][M];
        for (int i = 0; i < N; ++i) {
            map[i] = in.readLine().toCharArray();
        }

        parent = new Pos[N][M];
        Pos[][] data = new Pos[N][M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                parent[i][j] = new Pos(i, j);
                data[i][j] = new Pos(i, j);
            }
        }
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                Pos cur = data[i][j];
                Pos adjust = getAdjustPos(i, j);
                if (!find(cur).equals(find(adjust)))
                    union(cur, adjust);
            }
        }

        Set<Pos> set = new HashSet<>();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                set.add(find(parent[i][j]));
            }
        }
        System.out.println(set.size());
    }

    private static Pos getAdjustPos(int i, int j) {
        switch (map[i][j]) {
            case 'L':
                return new Pos(i, j - 1);
            case 'R':
                return new Pos(i, j + 1);
            case 'U':
                return new Pos(i - 1, j);
            case 'D':
                return new Pos(i + 1, j);
            default:
                return null;
        }
    }
}