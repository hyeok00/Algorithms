import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(in.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; ++tc) {
			int N = stoi(in.readLine());
			List<int[]> list = new ArrayList<>();
			for (int i = 0; i < N; ++i) {
				String[] inputs = in.readLine().split(" ");
				list.add(new int[] {stoi(inputs[0]), stoi(inputs[1])});
			}
			Collections.sort(list, (a, b) -> {
				if (a[0] == b[0])
					return b[1] - a[1];
				return a[0] - b[0];
			});

			int first = list.get(0)[0];
			int second = list.get(0)[1];
			int answer = 1;
			for (int i = 1; i < N; ++i) {
				int[] data = list.get(i);
				if (second > data[1]) {
					answer++;
					second = data[1];
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}