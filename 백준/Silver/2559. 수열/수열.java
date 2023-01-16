import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedLine = in.readLine().split(" ");
		int N = Integer.parseInt(splitedLine[0]);
		int K = Integer.parseInt(splitedLine[1]);

		int[] arr = new int[N];
		splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(splitedLine[i]);
		}
		int max = 0;
		int sum = 0;
		for (int i = 0; i < N; ++i) {
			if (i < K) {
				sum += arr[i];
				max = sum;
			} else {
				sum = sum - arr[i-K]+arr[i];
				if(sum>max)
					max = sum;
			}
		}
		System.out.println(max);
	}
}