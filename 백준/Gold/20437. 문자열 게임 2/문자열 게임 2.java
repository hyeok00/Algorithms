import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = stoi(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			String W = in.readLine();
			int K = stoi(in.readLine());
			if (K == 1) {
				sb.append(1).append(" ").append(1).append("\n");
			} else {
				int len = W.length();
				int min = Integer.MAX_VALUE;
				int max = -1;
				for (int j = 0; j < len; j++) {
					int count = 1;
					for (int l = j + 1; l < len; l++) {
						if (W.charAt(j) == W.charAt(l))
							count++;
						if (count == K) {
							min = Math.min(min, l - j + 1);
							max = Math.max(max, l - j + 1);
							break;
						}
					}
				}
				if (min == Integer.MAX_VALUE || max == -1)
					sb.append(-1).append("\n");
				else
					sb.append(min).append(" ").append(max).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}