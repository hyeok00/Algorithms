import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static int N;
	static StringBuilder sb;
	static char[] operator = {' ', '+', '-'};
	static char[] choice;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = stoi(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			N = stoi(in.readLine());
			choice = new char[N - 1]; // 연산자는 N-1개 존재한다.
			int depth = 0;
			for (int i = 0; i < 3; ++i) {
				choice[depth] = operator[i];
				simulation(depth + 1);
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

	private static void simulation(int depth) {
		if (depth >= N - 1) {
			List<Integer> list = new ArrayList<>();
			for (int i = 1; i <= N; ++i)
				list.add(i);

			StringBuilder expBuilder = new StringBuilder();
			for (int i = 0; i < N - 1; ++i) {
				expBuilder.append(list.get(i)).append(choice[i]);
			}
			expBuilder.append(N);

			String exp = expBuilder.toString();
			String valueExp = exp.replaceAll(" ", "");
			String[] nums = valueExp.split("[\\-\\+]");

			int result = stoi(nums[0]);
			int index = 1;
			for (int i = 0; i < N - 1; ++i) {
				if (choice[i] == '+') {
					result += stoi(nums[index]);
					index++;
				}
				if (choice[i] == '-') {
					result -= stoi(nums[index]);
					index++;
				}
			}

			if (result == 0)
				sb.append(exp).append("\n");

			return;
		}
		for (int i = 0; i < 3; ++i) {
			choice[depth] = operator[i];
			simulation(depth + 1);
		}
	}

	private static boolean isNumber(char c) {
		if ('1' <= c && c <= '9')
			return true;
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}