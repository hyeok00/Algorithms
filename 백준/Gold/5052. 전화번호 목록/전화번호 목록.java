import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static int T, N;

	static class TrieNode {
		private Map<Character, TrieNode> childNodes = new HashMap<>();
		private boolean isLast;

		public Map<Character, TrieNode> getChildNodes() {
			return childNodes;
		}

		public boolean isLast() {
			return isLast;
		}

		public void setLast(boolean last) {
			isLast = last;
		}
	}

	static class Trie {
		private TrieNode root;

		Trie() {
			root = new TrieNode();
		}

		boolean insert(String word) {
			boolean flag = true;
			TrieNode node = this.root;
			for (int i = 0; i < word.length(); i++) {
				node = node.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
				if (node.isLast())
					flag = false;
			}
			node.setLast(true);
			return flag;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));
		T = stoi(in.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; ++tc) {
			Trie trie = new Trie();
			N = stoi(in.readLine());
			boolean flag = true;
			List<String> list = new ArrayList<>();

			for (int i = 0; i < N; ++i)
				list.add(in.readLine());
			Collections.sort(list);
			
			for (String s : list)
				if (!trie.insert(s))
					flag = false;

			if (flag)
				sb.append("YES");
			else
				sb.append("NO");
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}