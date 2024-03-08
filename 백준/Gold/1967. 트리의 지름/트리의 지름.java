import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static class Node {
		int to;
		int weight;

		Node(int a, int b) {
			to = a;
			weight = b;
		}
	}

	static int N, count;
	static List<Node>[] list;
	static boolean[] visit;
	static Node maxNode;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		list = new List[N + 1];
		for (int i = 0; i <= N; ++i)
			list[i] = new ArrayList<>();

		for (int i = 0; i < N - 1; ++i) {
			String[] inputs = in.readLine().split(" ");
			int a = stoi(inputs[0]);
			int b = stoi(inputs[1]);
			int c = stoi(inputs[2]);
			list[a].add(new Node(b, c));
			list[b].add(new Node(a, c));
		}

		maxNode = new Node(0, 0);
		visit = new boolean[N + 1];
		visit[1] = true;
		dfs(1, 0);
		int start = maxNode.to;

		maxNode.to = 0;
		maxNode.weight = 0;
		visit = new boolean[N + 1];
		visit[start] = true;
		dfs(start, 0);
		System.out.println(maxNode.weight);
	}

	private static void dfs(int start, int cost) {
		if (cost > maxNode.weight) {
			maxNode.weight = cost;
			maxNode.to = start;
		}

		for (int i = 0; i < list[start].size(); i++) {
			Node next = list[start].get(i);
			if (!visit[next.to]) {
				visit[next.to] = true;
				dfs(next.to, cost + next.weight);
			}
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}