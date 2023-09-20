import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N, L, size;
	static List<Integer>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		N = stoi(splitedLine[0]);
		L = stoi(splitedLine[1]);

		size = N * 2;
		list = new List[size];
		for (int i = 0; i < size; ++i)
			list[i] = new ArrayList<>();

		// 가로방향
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < N; ++j)
				list[i].add(stoi(splitedLine[j]));
		}

		// 세로방향
		for (int i = N; i < size; ++i) {
			for (int j = 0; j < N; ++j)
				list[i].add(list[j].get(i - N));
		}

		// check
		int result = 0;
		for (int i = 0; i < size; ++i) {
			if (check(i))
				result++;
		}
		System.out.println(result);
	}

	private static boolean check(int n) {
		// 이전에 설치된 자리인지 확인 필요
		// Case : 221122
		boolean[] installed = new boolean[N];

		// 우측 또는 아래쪽 방향으로 이동하며 확인
		for (int i = 0; i < N - 1; i++) {
			int cur = list[n].get(i);
			int diff = cur - list[n].get(i + 1);

			if (diff > 1 || diff < -1)
				return false; // 경사로 높이는 1만 가능
			else {
				if (diff == -1) { // 올라가는 경사로
					for (int j = 0; j < L; j++) {
						int prev = i - j;
						if (prev < 0 || installed[prev])
							return false;
						if (cur != list[n].get(prev))
							return false;
						installed[prev] = true; //경사면 설치
					}
				} else if (diff == 1) { // 내려가는 경사로
					for (int j = 1; j <= L; j++) {
						int next = i + j;
						if (next >= N || installed[next])
							return false;
						if (cur - 1 != list[n].get(next))
							return false;
						installed[next] = true; //경사면 설치
					}
				} else {
					// nothing to do
				}

			}
		}
		return true;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	private static long stol(String s) {
		return Long.parseLong(s);
	}
}