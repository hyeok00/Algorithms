import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static class Node {
		int left;
		int right;

		public Node() {
			left = -1;
			right = -1;
		}

		public void setLeft(char c) {
			if ('.' == c) {
				left = -1;
			} else {
				left = c - 'A';
			}
		}

		public void setRight(char c) {
			if ('.' == c) {
				right = -1;
			} else {
				right = c - 'A';
			}
		}
	}

	public static Node[] node;
	public static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		node = new Node[N];
		for (int i = 0; i < N; ++i) {
			String input = in.readLine();
			int idx = input.charAt(0) - 'A';
			node[idx] = new Node();
			node[idx].setLeft(input.charAt(2));
			node[idx].setRight(input.charAt(4));
		}
		sb = new StringBuilder();

		preOrder(0);
		System.out.println(sb);
		sb.setLength(0);

		inOrder(0);
		System.out.println(sb);
		sb.setLength(0);

		postOrder(0);
		System.out.println(sb);
	}

	private static void postOrder(int idx) {
		if (node[idx].left >= 0)
			postOrder(node[idx].left);

		if (node[idx].right >= 0)
			postOrder(node[idx].right);

		sb.append((char)(idx + 'A'));
	}

	private static void inOrder(int idx) {
		if (node[idx].left >= 0)
			inOrder(node[idx].left);

		sb.append((char)(idx + 'A'));

		if (node[idx].right >= 0)
			inOrder(node[idx].right);
	}

	private static void preOrder(int idx) {
		if (idx < 0)
			return;
		sb.append((char)(idx + 'A'));
		preOrder(node[idx].left);
		preOrder(node[idx].right);
	}
}