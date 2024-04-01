import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, max, min;
	static int[] arr, oper;

	final static int PLUS = 0;
	final static int MINUS = 1;
	final static int MULTIPLY = 2;
	final static int DEVIDE = 3;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(in.readLine());

		arr = new int[N];
		String[] inputs = in.readLine().split(" ");
		for (int i = 0; i < N; ++i)
			arr[i] = stoi(inputs[i]);

		oper = new int[4];
		inputs = in.readLine().split(" ");
		for (int i = 0; i < 4; ++i)
			oper[i] = stoi(inputs[i]);

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		simulation(1, arr[0]);
		System.out.println(max);
		System.out.println(min);
	}

	private static void simulation(int depth, int value) {
		if (depth == N) {
			max = Math.max(max, value);
			min = Math.min(min, value);
			return;
		}
		for (int i = 0; i < 4; ++i) {
			if (oper[i] < 1)
				continue;
			oper[i]--;
			switch (i) {
				case PLUS:
					simulation(depth + 1, value + arr[depth]);
					break;
				case MINUS:
					simulation(depth + 1, value - arr[depth]);
					break;
				case MULTIPLY:
					simulation(depth + 1, value * arr[depth]);
					break;
				case DEVIDE:
					simulation(depth + 1, value / arr[depth]);
					break;
				default:
					break;
			}
			oper[i]++;
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}