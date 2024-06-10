import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
	static int H, W;
	static char[][] arr;
	static boolean[][] visit;
	static Set<Character> key;

	final static int EMPTY_SPACE = 0;
	final static int WALL = 1;
	final static int DOCUMENT = 2;
	final static int DOOR = 3;
	final static int KEY = 4;

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));

		int T = stoi(in.readLine());

		for (int tc = 0; tc < T; ++tc) {
			String[] inputs = in.readLine().split(" ");
			H = stoi(inputs[0]);
			W = stoi(inputs[1]);
			arr = new char[H][W];
			visit = new boolean[H][W];
			for (int i = 0; i < H; ++i)
				arr[i] = in.readLine().toCharArray();

			key = new HashSet<>();
			setKey(in.readLine());
			simulation();
		}

		System.out.println(sb);
	}

	private static void setKey(String s) {
		if (s.equals("0"))
			return;
		for (int i = 0; i < s.length(); ++i)
			key.add(s.charAt(i));
	}

	private static void simulation() {
		int result = 0; // 발견한 전체 문서의 수

		Queue<int[]> q = new ArrayDeque<>(); // 탐색용 Queue

		// 외곽선을 순회하며 시작하자.
		for (int i = 0; i < W; ++i) {
			q.add(new int[] {0, i});
			q.add(new int[] {H - 1, i});
			visit[0][i] = true;
			visit[H - 1][i] = true;
		}
		for (int i = 1; i < H - 1; ++i) {
			q.add(new int[] {i, 0});
			q.add(new int[] {i, W - 1});

			visit[i][0] = true;
			visit[i][W - 1] = true;
		}

		Map<Character, Set<List<Integer>>> doorPos = new HashMap<>();

		// 본격적인 탐색.
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];

			char c = arr[x][y];
			int type = getType(c);

			// 외곽선을 넣는 과정에서 벽이 있을 수 있다. 벽은 탐색 불가.
			if (type == WALL)
				continue;

			// 현재 위치가 문이 있는 위치인 경우
			if (type == DOOR) {
				char lowerCase = Character.toLowerCase(c);
				// 처음보는 문이라면, 위치를 기록한다.
				Set<List<Integer>> posList = doorPos.getOrDefault(lowerCase, new HashSet<>());
				posList.add(Arrays.stream(cur).boxed().collect(Collectors.toList()));
				doorPos.put(lowerCase, posList);

				// 열쇠가 없다면, 더이상 진행할 수 없다.
				if (!key.contains(lowerCase))
					continue;
			}

			// 현재 칸이 문서가 있는 곳
			if (type == DOCUMENT)
				result++;

			// 현재 칸이 열쇠가 있는 곳
			if (type == KEY) {
				key.add(c); // 열쇠 추가

				// 대응하는 문을 본적이 있다면, 해당 위치부터 재탐색 한다.
				if (doorPos.containsKey(c))
					for (List<Integer> iter : doorPos.get(c))
						q.add(new int[] {iter.get(0), iter.get(1)});
			}

			// 상하좌우 중 갈 수 있는 방향에 대해서 탐색을 진행한다.
			for (int d = 0; d < 4; ++d) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				// 범위 내부이면서 가본적 없는 곳
				if (isInRange(nx, ny) && !visit[nx][ny] && arr[nx][ny] != WALL) {
					visit[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}

		sb.append(result).append("\n");
	}

	private static int getType(char c) {
		if (c == '.')
			return EMPTY_SPACE;
		if (c == '*')
			return WALL;
		if (c == '$')
			return DOCUMENT;
		if ('A' <= c && c <= 'Z')
			return DOOR;
		if ('a' <= c && c <= 'z')
			return KEY;
		return -1;
	}

	private static boolean isInRange(int x, int y) {
		if (0 <= x && x < H && 0 <= y && y < W)
			return true;
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}