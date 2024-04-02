import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, min;
	static int[] perm;
	static int[][] arr;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(in.readLine());

		arr = new int[N][N];
		check = new boolean[N];
		//perm = new

		for (int i = 0; i < N; ++i) {
			String[] inputs = in.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				arr[i][j] = stoi(inputs[j]);
			}
		}

		min = Integer.MAX_VALUE;
		permutaion(0, 0);
		System.out.println(min);
	}

	private static void permutaion(int start, int depth) {
		if (depth == N / 2) {
			int team1 = getSum(false);
			int team2 = getSum(true);
			min = Math.min(min, Math.abs(team2 - team1));
			return;
		}
		for (int i = start; i < N; ++i) {
			if (check[i])
				continue;
			check[i] = true;
			permutaion(i, depth + 1);
			check[i] = false;
		}
	}

	private static int getSum(boolean flag) {
		int sum = 0;
		for (int i = 0; i < N; ++i) {
			if (check[i] == flag)
				continue;
			for (int j = 0; j < N; ++j) {
				if (check[j] == flag)
					continue;
				sum += arr[i][j];
			}
		}
		return sum;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}