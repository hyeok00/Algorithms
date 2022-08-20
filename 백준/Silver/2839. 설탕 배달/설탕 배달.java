import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static int N;
	static int[] dp;
	final static int THREEBAG = 3;
	final static int FIVEBAG = 5;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		N = Integer.parseInt(in.readLine());
		dp = new int[N + 1];

		// 로직
		int a = -1, b = -1;
		a = N / FIVEBAG;
		for (int i = a; i >= 0; --i) {
			int remain = N - i * FIVEBAG;
			if (remain % THREEBAG != 0) {
				// none
			} else {
				a = i;
				b = remain / THREEBAG;
				break;
			}
		}
		if (b == -1)
			sb.append(-1);
		else
			sb.append(a + b);
		// 출력부
		System.out.println(sb);
	}
}
