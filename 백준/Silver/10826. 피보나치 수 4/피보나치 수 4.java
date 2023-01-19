import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		BigInteger first = new BigInteger("0");
		BigInteger second = new BigInteger("1");
		if (N == 1)
			System.out.println("1");
		else if (N == 2)
			System.out.println("1");
		else if (N == 0)
			System.out.println("0");
		else {
			for (int i = 2; i <= N; ++i) {
				BigInteger temp = first.add(second);
				first = second;
				second = temp;
			}
			System.out.println(second);
		}
	}
}