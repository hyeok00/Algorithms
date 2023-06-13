import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, S;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		S = Integer.parseInt(splitedLine[1]);
		int[] arr = new int[N];
		splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(splitedLine[i]);
		}

		int minLength = Integer.MAX_VALUE;
		int start = 0;
		int end = 0;
		int sum = 0;
		boolean flag = false;

		while (true) {

			if (sum >= S) {
				minLength = Math.min(minLength, end - start + 1);
				flag = true;
			}

			if (sum - arr[start] >= S) {
				sum -= arr[start];
				start++;
				continue;
			}

			if (end == N)
				break;

			sum += arr[end];
			end++;

		}
		if (flag == false)
			System.out.println(0);
		else
			System.out.println(minLength - 1);
	}
}