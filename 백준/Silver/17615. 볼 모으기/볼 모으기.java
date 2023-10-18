import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static int N;
	static String ballString;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		ballString = in.readLine();

		int minMoveCount = Integer.MAX_VALUE;
		// Case1. Red를 모두 왼쪽으로
		minMoveCount = Math.min(minMoveCount, getCountForLeft('R'));

		// Case2. Red를 모두 오른쪽으로
		minMoveCount = Math.min(minMoveCount, getCountForRight('R'));

		// Case3. Blue를 모두 왼쪽으로
		minMoveCount = Math.min(minMoveCount, getCountForLeft('B'));

		// Case4. Blue를 모두 오른쪽으로
		minMoveCount = Math.min(minMoveCount, getCountForRight('B'));

		System.out.println(minMoveCount);
	}

	private static int getCountForLeft(char type) {
		int result = 0;
		List<Integer> targets = new ArrayList<>();
		int firstOtherIndex = Integer.MAX_VALUE;
		boolean isFindFirst = false;

		for (int i = 0; i < N; ++i) {
			if (ballString.charAt(i) == type) {
				targets.add(i);
			} else {
				if (!isFindFirst) {
					isFindFirst = true;
					firstOtherIndex = i;
				}
			}
		}

		for (int i = 0; i < targets.size(); ++i) {
			if (firstOtherIndex < targets.get(i))
				result++;
		}
		return result;
	}

	private static int getCountForRight(char type) {
		int result = 0;
		List<Integer> targets = new ArrayList<>();
		int lastOtherIndex = Integer.MIN_VALUE;

		for (int i = 0; i < N; ++i) {
			if (ballString.charAt(i) == type)
				targets.add(i);
			else
				lastOtherIndex = i;

		}

		for (int i = 0; i < targets.size(); ++i) {
			if (lastOtherIndex > targets.get(i))
				result++;
		}
		return result;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}