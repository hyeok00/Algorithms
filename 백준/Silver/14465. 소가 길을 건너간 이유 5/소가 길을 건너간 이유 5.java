import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		splitedLine = in.readLine().split(" ");
		int N = Integer.parseInt(splitedLine[0]);
		int K = Integer.parseInt(splitedLine[1]);
		int B = Integer.parseInt(splitedLine[2]);

		boolean[] arr = new boolean[N + 1];
		for (int i = 1; i <= B; ++i) {
			arr[Integer.parseInt(in.readLine())] = true;
		}

		// 로직
		int sum = 0;
		for (int i = 1; i <= K; ++i) {
			if (arr[i] == false)
				sum++;
		}
		int max = sum;
		for (int i = K + 1; i <= N; ++i) {
			if (arr[i - K] == false)
				sum--;
			if (arr[i] == false)
				sum++;
			max = sum > max ? sum : max;
		}

		sb.append(K-max);
		// 출력부
		System.out.println(sb);
	}
}

/*
1 2 3 4 5 6 7 8 9 10
X X O O X O O O X X
 */