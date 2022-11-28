import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	static BigInteger arr[][];

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		arr = new BigInteger[101][101];
		String[] splitedLine = in.readLine().split(" ");
		int N = Integer.parseInt(splitedLine[0]);
		int M = Integer.parseInt(splitedLine[1]);

		arr[4][0] = new BigInteger("1");
		arr[4][1] = new BigInteger("4");
		arr[4][2] = new BigInteger("6");
		arr[4][3] = new BigInteger("4");
		arr[4][4] = new BigInteger("1");
		for (int i = 5; i <= N; ++i) {
			for (int j = 0; j <= i; ++j) {
				if (i == j || j == 0)
					arr[i][j] = new BigInteger("1");
				else
					arr[i][j] = arr[i - 1][j - 1].add(arr[i - 1][j]);
			}
		}
		System.out.println(arr[N][M]);
	}
}