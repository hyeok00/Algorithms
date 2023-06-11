import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		List<Integer> list = new ArrayList<>();

		int zeroCount = 0;
		int negCount = 0;
		int posCount = 0;

		for (int i = 0; i < N; ++i) {
			int value = Integer.parseInt(in.readLine());
			if (value == 0)
				zeroCount++;
			else {
				list.add(value);
				if (value < 0)
					negCount++;
				else
					posCount++;
			}
		}
		// -4 0 2 6
		int size = list.size();
		list.sort(Integer::compareTo);
		int result = 0;
		if (negCount == 0) {

		} else if (negCount == 1) {
			if (zeroCount > 0) {
				zeroCount--;
			} else
				result += list.get(negCount - 1);
		} else {
			if (negCount % 2 == 0) {
				for (int i = 0; i < negCount; i += 2) {
					result += list.get(i) * list.get(i + 1);
				}
			} else {
				for (int i = 0; i < negCount - 2; i += 2) {
					result += list.get(i) * list.get(i + 1);
				}
				if (zeroCount > 0) {
					zeroCount--;
				} else {
					result += list.get(negCount - 1);
				}
			}
		}
		if (posCount % 2 == 0) {
			for (int i = size - 1; i > negCount; i -= 2) {
				result += Math.max(list.get(i) * list.get(i - 1), list.get(i) + list.get(i - 1));
			}
		} else {
			for (int i = size - 1; i > negCount; i -= 2) {
				result += Math.max(list.get(i) * list.get(i - 1), list.get(i) + list.get(i - 1));
			}
			result += list.get(negCount);
		}
		System.out.println(result);
	}
}