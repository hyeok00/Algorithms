import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] bitset = new int[1 << 20 + 1];
		StringBuilder sb = new StringBuilder();
		String[] inputs = in.readLine().split(" ");
		for (String s : inputs) {
			int value = stoi(s);
			int idx = value / 32;
			int pos = value % 32;
			int bit = 1 << pos;
			if ((bitset[idx] & bit) == 0) {
				bitset[idx] = bitset[idx] | bit;
				sb.append(value).append(" ");
			}
		}
		System.out.println(sb);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}