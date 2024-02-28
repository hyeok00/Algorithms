import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int G;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		G = stoi(in.readLine());

		int prev = 1;
		int cur = 2;

		List<Integer> list = new ArrayList<>();
		while (true) {
			int diff = cur * cur - prev * prev;
			if (prev + 1 == cur && diff > 100000)
				break;
			if (diff == G) {
				list.add(cur);
				cur++;
				prev++;
			}
			else if (diff < G) {
				cur++;
			} else {
				prev++;
			}
		}
		StringBuilder sb = new StringBuilder();
		if (!list.isEmpty())
			list.stream().forEach(i -> sb.append(i).append("\n"));
		else
			sb.append("-1");
		System.out.println(sb);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}