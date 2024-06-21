import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));
		StringBuilder sb = new StringBuilder();

		BigInteger[] arr = new BigInteger[31];
		arr[0] = BigInteger.valueOf(1);
		arr[1] = BigInteger.valueOf(1);
		arr[2] = BigInteger.valueOf(2);
		for (int i = 3; i <= 30; ++i) {
			arr[i] = BigInteger.valueOf(0);
			for (int a = 0; a < i; ++a) 
				arr[i] = arr[i].add(arr[a].multiply(arr[i - a - 1]));
		}
		
		while (true) {
			int N = stoi(in.readLine());
			if (N == 0)
				break;
			sb.append(arr[N]).append("\n");
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}