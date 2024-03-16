import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = stoi(in.readLine());
		char[] str = in.readLine().toCharArray();

		Map<Character, Character> map = new HashMap<>();
		map.put('B', 'J');
		map.put('O', 'B');
		map.put('J', 'O');

		int[] cost = new int[N];
		Arrays.fill(cost, 987654321);
		cost[0] = 0;

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < i; ++j) {
				if (str[j] == map.get(str[i]))
					cost[i] = Math.min(cost[i], cost[j] + ((i - j) * (i - j)));
			}
		}
		System.out.println(cost[N - 1] == 987654321 ? -1 : cost[N - 1]);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}