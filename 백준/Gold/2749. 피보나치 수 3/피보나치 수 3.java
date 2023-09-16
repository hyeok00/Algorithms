import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	final static long MOD = 1000000;
	public static long[][] base = {{1, 1}, {1, 0}};

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		long N = stol(in.readLine());
		long[][] arr = {{1, 1}, {1, 0}};
		long[][] result = getFibo(arr, N - 1);

		System.out.println(result[0][0]);

	}

	private static long[][] getFibo(long[][] arr, long n) {
		if (n == 1 || n == 0)
			return arr;

		long[][] next = getFibo(arr, n / 2);
		next = mulMatrix(next, next);

		if (n % 2 == 1)
			next = mulMatrix(next, base);

		return next;
	}

	private static long[][] mulMatrix(long[][] src1, long[][] src2) {
		long[][] des = new long[2][2];

		des[0][0] = ((src1[0][0] * src2[0][0]) + (src1[0][1] * src2[1][0])) % MOD;
		des[0][1] = ((src1[0][0] * src2[0][1]) + (src1[0][1] * src2[1][1])) % MOD;
		des[1][0] = ((src1[1][0] * src2[0][0]) + (src1[1][1] * src2[1][0])) % MOD;
		des[1][1] = ((src1[1][0] * src2[0][1]) + (src1[1][1] * src2[1][1])) % MOD;

		return des;
	}

	private static long stol(String s) {
		return Long.parseLong(s);
	}

}