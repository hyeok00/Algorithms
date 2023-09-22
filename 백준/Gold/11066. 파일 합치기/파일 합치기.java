import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] arr, sum;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = stoi(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; ++tc) {
			int N = stoi(in.readLine());
			arr = new int[N + 1]; // 파일의 비용
			sum = new int[N + 1]; // 누적합
			dp = new int[N + 1][N + 1]; // [a,b]구간 계산결과
			for (int i = 0; i <= N; ++i) {
				for (int j = 0; j <= N; ++j) {
					dp[i][j] = Integer.MAX_VALUE;
				}
			}

			String[] splitedLine = in.readLine().split(" ");
			for (int i = 1; i <= N; ++i) {
				arr[i] = stoi(splitedLine[i - 1]);
				sum[i] = sum[i - 1] + arr[i];
			}
			sb.append(getResult(1, N)).append("\n");
		}
		System.out.println(sb);
	}

	private static int getResult(int left, int right) {
		if (dp[left][right] != Integer.MAX_VALUE) // 계산한 적이 있다면
			return dp[left][right]; // 계산된 값 리턴

		if (left == right) // 동일 파일을 합치는 경우는 없음
			return dp[left][right] = 0;

		if (right - left == 1) // 바로 옆의 파일이라면
			return dp[left][right] = arr[left] + arr[right]; // 합친값 리턴

		// 길이 3이상의 구간이라면, 각각의 경우를 모두 계산해본다.
		for (int i = left; i < right; ++i) {
			int cost1 = getResult(left, i);
			int cost2 = getResult(i + 1, right);
			dp[left][right] = Math.min(dp[left][right], cost1 + cost2);
		}
		// [left, right]가 합쳐질때 발생하는 최소비용을 계산완료 했다.
		// left와 right 사이의 기본 파일 크기를 다 더해주자.
		dp[left][right] = dp[left][right] + sum[right]-sum[left-1];
		return dp[left][right] ;

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	private static long stol(String s) {
		return Long.parseLong(s);
	}
}