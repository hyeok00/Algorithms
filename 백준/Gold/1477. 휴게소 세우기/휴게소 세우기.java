import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N, M, L;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");
		N = stoi(inputs[0]);
		M = stoi(inputs[1]);
		L = stoi(inputs[2]);

		List<Integer> list = new ArrayList<>();
		inputs = in.readLine().split(" ");
		for (int i = 0; i < N; ++i)
			list.add(stoi(inputs[i]));

		list.add(0);
		list.add(L);
		Collections.sort(list);

		List<Integer> diff = new ArrayList<>();
		for (int i = 0; i < N + 1; ++i)
			diff.add(list.get(i + 1) - list.get(i) - 1);

		// 이분탐색으로 휴게소와 휴게소 사이의 적당한 거리를 찾아보자.
		int left = 1;
		int right = L - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			int count = diff.stream()
				.mapToInt(i -> i / mid)
				.sum();

			if (count > M)
				left = mid + 1;
			else
				right = mid - 1;
		}
		System.out.println(left);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}