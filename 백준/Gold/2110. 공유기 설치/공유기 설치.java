import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N, C;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");
		N = stoi(inputs[0]);
		C = stoi(inputs[1]);

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; ++i)
			list.add(stoi(in.readLine()));

		Collections.sort(list);

		int left = 0;
		int right = 1000000000;

		while (left <= right) {
			int mid = (left + right) / 2;

			int count = 1;
			int last = list.get(0);
			for (int i = 1; i < list.size(); ++i) {
				if (list.get(i) - last >= mid) {
					last = list.get(i);
					count++;
				}
			}
			if (count < C)
				right = mid - 1;
			else
				left = mid + 1;
		}
		System.out.println(left - 1);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}