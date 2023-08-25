import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N, K;
	static boolean[] check;
	static int len;

	static class Data {
		int[] arr;

		Data() {
		}

		Data(int value) {
			init(Integer.toString(value));
		}

		public void init(String n) {
			int size = n.length();
			arr = new int[size];
			for (int i = 0; i < size; ++i)
				arr[i] = n.charAt(i) - '0';
		}

		public int getValue() {
			int result = 0;
			for (int i = 0; i < arr.length; ++i) {
				result += arr[i];
				result *= 10;
			}
			return result / 10;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedLine = in.readLine().split(" ");

		N = stoi(splitedLine[0]);
		K = stoi(splitedLine[1]);
		len = splitedLine[0].length();

		Data start = new Data();
		start.init(splitedLine[0]);

		Queue<Data> q = new ArrayDeque<>();
		q.add(start);

		int depth = 1;
		while (!q.isEmpty()) {
			check = new boolean[1000001];
			if (depth > K)
				break;
			int size = q.size();
			while (size-- > 0) {
				Data d = q.poll();

				// 버블소트처럼 하나씩 비교하며 바꿔본다.
				for (int i = 0; i < len - 1; i++) {
					for (int j = i + 1; j < len; j++) {
						int value = change(d.getValue(), i, j);
						// 앞자리가 0이 되거나, 해당 depth에서 이미 만들었다면 생략한다.
						if (value != -1 && !check[value]) {
							check[value] = true;
							q.add(new Data(value));
						}
					}
				}
			}
			depth++;
		}

		// 최종적으로 만들어진 값 중에 최대값을 찾는다.
		int result = -1;
		while (!q.isEmpty()) {
			Data d = q.poll();
			result = Math.max(result, d.getValue());
		}
		System.out.println(result);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	private static int change(int value, int i, int j) {
		StringBuilder sb = new StringBuilder();
		sb.append(value);

		// 첫자리는 0이 될 수 없다.
		if (i == 0 && sb.charAt(j) == '0')
			return -1;

		char c = sb.charAt(i);
		sb.setCharAt(i, sb.charAt(j));
		sb.setCharAt(j, c);
		return Integer.parseInt(sb.toString());
	}
}