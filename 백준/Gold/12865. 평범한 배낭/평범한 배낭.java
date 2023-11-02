import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	 static int N, K;
	 static int[] weight, value, dp;
	
	 public static void main(String[] args) throws IOException {
	 	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	 	String[] splitedLine = in.readLine().split(" ");
	 	N = stoi(splitedLine[0]);
	 	K = stoi(splitedLine[1]);
	
	 	weight = new int[N + 1];
	 	value = new int[N + 1];
	 	dp = new int[K + 1];
	
	 	for (int i = 0; i < N; ++i) {
	 		splitedLine = in.readLine().split(" ");
	 		weight[i] = stoi(splitedLine[0]);
	 		value[i] = stoi(splitedLine[1]);
	 	}
	
	 	for (int i = 0; i < N; ++i) {
	 		for (int j = K; j >= weight[i]; --j) {
	 			dp[j] = Math.max(dp[j], value[i] + dp[j - weight[i]]);
	 		}
	 	}
	 	System.out.println(dp[K]);
	 }

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}