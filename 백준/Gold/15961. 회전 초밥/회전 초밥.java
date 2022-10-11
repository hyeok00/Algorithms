import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static String[] splitedLine;
	static int N, D, K, C;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		D = Integer.parseInt(splitedLine[1]);
		K = Integer.parseInt(splitedLine[2]);
		C = Integer.parseInt(splitedLine[3]);

		int[] arr = new int[N];
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		int[] isChecked = new int[D + 1];

		int count = 1;
		++isChecked[C];
		for (int i = 0; i < K; ++i) {
			int value = arr[i];
			if (isChecked[value] == 0)
				count++;

			isChecked[value]++;
		}
		int result = count;

		for (int i = 0; i < N; ++i) {
			int curValue = arr[(i + K) % N];
			int prevValue = arr[i];

			isChecked[prevValue]--;
			if (isChecked[prevValue] == 0) {
				count--;
			}
			if (isChecked[curValue] == 0) {
				count++;
			}
			isChecked[curValue]++;
			if (count > result)
				result = count;
		}
		System.out.println(result);
	}
}