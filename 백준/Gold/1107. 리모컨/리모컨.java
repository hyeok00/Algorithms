import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static boolean[] malfunction;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		M = stoi(in.readLine());
		malfunction = new boolean[10];
		if (M != 0) {
			String[] splitedLine = in.readLine().split(" ");
			for (int i = 0; i < M; ++i)
				malfunction[stoi(splitedLine[i])] = true;
		}

		int count = Integer.MAX_VALUE;
		for (int i = 0; i <= 999999; ++i) {
			if (isAvailable(i)) {
				int useButton = Math.abs(i - N) + getLength(i);
				count = Math.min(count, useButton);
			}

		}
		int onlyMove = Math.abs(N - 100);
		count = Math.min(count, onlyMove);
		System.out.println(count);

	}

	private static int getLength(int i) {
		if(i==0)
			return 1;

		int result = 0;
		while (i != 0) {
			i = i / 10;
			result++;
		}
		return result;
	}

	private static boolean isAvailable(int i) {
		if(i==0)
			return !malfunction[i];

		while (i != 0) {
			if (malfunction[i % 10] == true)
				return false;
			i = i / 10;
		}
		return true;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}