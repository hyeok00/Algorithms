import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		int N = Integer.parseInt(in.readLine());

		// 로직
		int[] first = { 2, 3, 5, 7 };
		for (int i = 0; i < first.length; ++i) {
			recur(first[i], N);
		}

		// 출력
		System.out.println(sb);
	}

	public static void recur(int start, int depth) {
		if (depth == 1) {
			if (isPrime(start))
				sb.append(start + "\n");
			return;
		}
		for (int i = 1; i <= 9; ++i) {
			int curValue = start*10 + i;
			if (isPrime(curValue)) {
				recur(curValue, depth - 1);
			}
		}
	}

	public static boolean isPrime(int n) {
		boolean res = true;

		for (int i = 2; i <= Math.sqrt(n); ++i) {
			if (n % i == 0) {
				res = false;
				break;
			}
		}

		return res;
	}
}
