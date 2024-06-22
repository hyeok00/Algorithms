import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int[] parent;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));

		N = stoi(in.readLine());
		parent = new int[N + 1];
		for (int i = 1; i <= N; ++i)
			parent[i] = i;

		for (int i = 0; i < N - 2; ++i) {
			String[] inputs = in.readLine().split(" ");
			union(stoi(inputs[0]), stoi(inputs[1]));
		}
		
		for (int i = 1; i <= N; ++i)
			find(i);
		
		StringBuilder sb = new StringBuilder();
		int base = parent[1];
		int other = Arrays.stream(parent).filter(e -> e != base && e != 0).findFirst().getAsInt();
		sb.append("1").append(" ").append(other);
		System.out.println(sb);
	}

	public static int find(int n) {
		if (parent[n] == n)
			return n;
		return parent[n] = find(parent[n]);
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b)
			return;

		if (a <= b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}