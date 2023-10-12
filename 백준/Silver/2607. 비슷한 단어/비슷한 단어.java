import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] count;
	final static int NUM_ALPHA = 27;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = stoi(in.readLine());
		String base = in.readLine();
		count = getCountArr(base);

		int result = 0;
		for (int t = 1; t < N; ++t) {
			String str = in.readLine();
			int[] arr = getCountArr(str);
			if (base.length() == str.length()) {
				if (isSimilar(count, arr, true))
					result++;
			} else {
				if (isSimilar(count, arr, false))
					result++;
			}
		}
		System.out.println(result);
	}

	private static boolean isSimilar(int[] count, int[] arr, boolean isSameLength) {
		if (isSameLength) {
			int cnt = 0;
			for (int i = 0; i < NUM_ALPHA; ++i) {
				if (count[i] != arr[i])
					cnt += Math.abs(count[i]-arr[i]);
			}
			if (cnt == 2 || cnt == 0)
				return true;
			return false;
		} else {
			boolean flag = false;
			for (int i = 0; i < NUM_ALPHA; ++i) {
				if (count[i] == arr[i])
					continue;
				if (Math.abs(count[i] - arr[i]) > 1)
					return false;
				if ((count[i] + 1 == arr[i]) || (count[i] - 1 == arr[i])) {
					if (flag) {
						return false;
					} else {
						flag = true;
					}
				}
			}
			return true;
		}
	}

	private static int[] getCountArr(String s) {
		int[] arr = new int[NUM_ALPHA];
		for (int i = 0; i < s.length(); ++i)
			arr[s.charAt(i) - 'A']++;
		return arr;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}