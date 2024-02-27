import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(in.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; ++i) {
			String[] inputs = in.readLine().split(" ");
			int dist = stoi(inputs[1]) - stoi(inputs[0]);
			int sqrtValue = (int)Math.sqrt(dist);

			if (sqrtValue == Math.sqrt(dist))
				sb.append(2 * sqrtValue - 1).append("\n");
			else if (dist <= sqrtValue * sqrtValue + sqrtValue)
				sb.append(2 * sqrtValue).append("\n");
			else
				sb.append(2 * sqrtValue + 1).append("\n");
		}
		System.out.println(sb);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}