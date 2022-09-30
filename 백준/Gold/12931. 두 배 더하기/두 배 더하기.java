import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];

		String[] splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(splitedLine[i]);
		}

		int count = 0;
		while (true) {
			boolean flag = true;
			for (int i = 0; i < N; ++i) {
				if (arr[i] % 2 == 1) {
					arr[i]--;
					count++;
				}
				if (arr[i] > 0)
					flag = false;
			}

			if (flag)
				break;

			for (int i = 0; i < N; ++i) {
				arr[i] = arr[i] / 2;
			}
			count++;
		}
		System.out.println(count);
	}
}