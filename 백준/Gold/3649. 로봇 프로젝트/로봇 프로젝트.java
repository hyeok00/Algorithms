import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		int[] arr;
		StringBuilder sb = new StringBuilder();
		while ((s = in.readLine()) != null) {
			int x = stoi(s) * 10000000;
			int N = stoi(in.readLine());
			arr = new int[N];
			for (int i = 0; i < N; ++i)
				arr[i] = stoi(in.readLine());

			Arrays.sort(arr);

			// 투 포인터로 탐색
			if (N >= 2) {
				int l1 = 0;
				int l2 = N - 1;
				boolean flag = false;
				while (l1 < l2) {
					int sum = arr[l1] + arr[l2];
					if (sum == x) {
						sb.append("yes ").append(arr[l1]).append(" ").append(arr[l2]).append("\n");
						flag = true;
						break;
					} else if (sum < x) {
						l1++;
					} else {
						l2--;
					}
				}
				if (!flag)
					sb.append("danger\n");
			} else
				sb.append("danger\n");
		}
		System.out.println(sb);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}