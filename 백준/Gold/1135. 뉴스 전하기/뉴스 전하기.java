import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N;
	static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		list = new List[N];
		for (int i = 0; i < N; ++i)
			list[i] = new ArrayList<>();
		String[] splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			int p = Integer.parseInt(splitedLine[i]);
			if (p != -1) {
				list[p].add(i);
			}
		}

		int res = getTime(0) - 1;
		System.out.println(res);
	}

	private static int getTime(int idx) {
		int max = 0;
		int width = list[idx].size() - 1;

		List<Integer> arr = new ArrayList<>();
		for (int i : list[idx])
			arr.add(getTime(i));

		Collections.sort(arr);
		for (int i : arr)
			max = Math.max(max, i + width--);

		return max + 1;
	}
}