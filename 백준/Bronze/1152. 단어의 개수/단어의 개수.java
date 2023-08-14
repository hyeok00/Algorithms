import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String str = in.readLine();
		String[] inputString = str.split(" ");
		sb = new StringBuilder();

		int res = 0;
		for (String s : inputString) {
			if (s.length() > 0)
				res++;
		}
		sb.append(res);
		System.out.println(sb);
	}
}