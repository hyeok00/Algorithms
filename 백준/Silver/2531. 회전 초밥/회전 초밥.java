import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static int N, D, K, C;
	static List<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		N = stoi(splitedLine[0]);
		D = stoi(splitedLine[1]);
		K = stoi(splitedLine[2]);
		C = stoi(splitedLine[3]);

		list = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			list.add(stoi(in.readLine()));
		}

		int count = 0;
		int[] arr = new int[D + 1];
		for (int i = 0; i < K; i++) {
			if (arr[list.get(i)] == 0)
				count++;
			arr[list.get(i)]++;
		}

		int max = count;
		for (int i = 1; i <= N; i++) {
			if (max <= count) {
				if (arr[C] == 0)
					max = count + 1;
				else
					max = count;
			}

			arr[list.get((i - 1) % N)]--;
			if (arr[list.get((i - 1) % N)] == 0)
				count--;

			if (arr[list.get((i + K - 1) % N)] == 0)
				count++;
			arr[list.get((i + K - 1) % N)]++;
		}
		System.out.println(max);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}