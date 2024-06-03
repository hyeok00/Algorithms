import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] arr = new int[10001];
	static int count = -1;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));

		String s;
		while ((s = in.readLine()) != null) {
			int value = stoi(s);
			count++;
			arr[count] = value;
		}
		print(0, count + 1);
		System.out.println(sb);
	}

	/**
	 * V -> L -> R 의 입력 순서를 L -> R -> V 순서로 변경
	 */
	private static void print(int start, int end) {
		if (start >= end)
			return;

		int v = arr[start];
		int boundary = start + 1;
		for (int i = start + 1; i <= end; ++i) {
			boundary = i;
			if (arr[i] > v)
				break;
		}
		print(start + 1, boundary);
		print(boundary, end);
		sb.append(v).append("\n");
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}