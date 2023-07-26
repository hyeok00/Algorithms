import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static int N;

	static class Point {
		int x;
		int y;

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	static class Move {
		int sec;
		char dir;

		Move(int a, char b) {
			sec = a;
			dir = b;
		}
	}

	final static int APPLE = -1;
	final static int BLANK = 0;
	final static int SNAKE = 1;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());
		map = new int[N][N];

		int k = Integer.parseInt(in.readLine());
		for (int i = 0; i < k; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			map[Integer.parseInt(splitedLine[0])-1][Integer.parseInt(splitedLine[1])-1] = APPLE;
		}

		List<Move> moves = new ArrayList<>();
		k = Integer.parseInt(in.readLine());
		for (int i = 0; i < k; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			moves.add(new Move(Integer.parseInt(splitedLine[0]), splitedLine[1].charAt(0)));
		}

		int result = 0;
		int idx = 0;
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(0, 0));
		map[0][0] = SNAKE;
		char dir = 'R';
		Point head = new Point(0, 0);
		while (true) {
			head.x = getNextX(head.x, dir);
			head.y = getNextY(head.y, dir);

			if (isAvailable(head)) {
				if (map[head.x][head.y] == APPLE) {

				} else if (map[head.x][head.y] == SNAKE) {
					break;
				} else {
					Point poll = q.poll();
					map[poll.x][poll.y] = BLANK;
				}
				result++;
				q.add(new Point(head.x, head.y));
				map[head.x][head.y] = SNAKE;
			} else {
				break;
			}

			if (idx < moves.size() && moves.get(idx).sec == result) {
				dir = getDirection(dir, moves.get(idx).dir);
				idx++;
			}
		}
		System.out.println(result + 1);
	}

	private static char getDirection(char dir, char dir1) {
		char result = dir;
		if (dir == 'U') {
			if (dir1 == 'L')
				result = 'L';
			else
				result = 'R';
		} else if (dir == 'D') {
			if (dir1 == 'L')
				result = 'R';
			else
				result = 'L';
		} else if (dir == 'L') {
			if (dir1 == 'L')
				result = 'D';
			else
				result = 'U';
		} else {
			if (dir1 == 'L')
				result = 'U';
			else
				result = 'D';
		}
		return result;
	}

	private static int getNextX(int x, char dir) {
		int result = 0;
		switch (dir) {
			case 'U':
				result = x - 1;
				break;
			case 'D':
				result = x + 1;
				break;
			default:
				result = x;
		}
		return result;
	}

	private static int getNextY(int x, char dir) {
		int result = 0;
		switch (dir) {
			case 'L':
				result = x - 1;
				break;
			case 'R':
				result = x + 1;
				break;
			default:
				result = x;
		}
		return result;
	}

	private static boolean isAvailable(Point head) {
		if (0 <= head.x && head.x < N && 0 <= head.y && head.y < N && map[head.x][head.y] != SNAKE)
			return true;
		return false;
	}
}