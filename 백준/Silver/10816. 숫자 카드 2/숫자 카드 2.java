import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = stoi(in.readLine());
		Map<Integer, Integer> map = new HashMap<>();

		String[] splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			int value = stoi(splitedLine[i]);
			int count = map.getOrDefault(value, 0);
			map.put(value, count + 1);
		}

		StringBuilder sb = new StringBuilder();

		int M = stoi(in.readLine());
		splitedLine = in.readLine().split(" ");
		for (int i = 0; i < M; ++i)
			sb.append(map.getOrDefault(stoi(splitedLine[i]), 0)).append(" ");

		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}