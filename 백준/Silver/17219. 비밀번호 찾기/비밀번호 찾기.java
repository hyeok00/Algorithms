import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedLine = in.readLine().split(" ");
		N = stoi(splitedLine[0]);
		M = stoi(splitedLine[1]);
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			map.put(splitedLine[0], splitedLine[1]);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; ++i) {
			String key = in.readLine();
			sb.append(map.get(key)).append("\n");
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}