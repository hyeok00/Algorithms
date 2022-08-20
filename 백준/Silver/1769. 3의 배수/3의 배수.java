import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static StringBuilder sb;
	public void solution() throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String inputString = input.readLine();
		sb = new StringBuilder();
		int cnt = 0;

		while (true) {
			if (inputString.length() == 1) {
				break;
			}
			cnt++;

			int sum = 0;

			for (int i = 0; i < inputString.length(); ++i) {
				sum += inputString.charAt(i)-'0';
			}
			inputString = Integer.toString(sum);
		}
		sb.append(cnt+"\n");
		if(Integer.parseInt(inputString) % 3 ==0) {
			sb.append("YES");
		}else {
			sb.append("NO");
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}