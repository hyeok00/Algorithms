import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
	static class Trie {
		private TrieNode root;

		Trie() {
			root = new TrieNode();
		}

		public void insert(String[] words) {
			TrieNode node = root;
			for (int i = 1; i < words.length; ++i)
				node = node.getChilds().computeIfAbsent(words[i], w -> new TrieNode());
		}

		public void print() {
			StringBuilder sb = new StringBuilder();
			for (String word : root.childs.keySet()) {
				sb.append(word).append("\n");
				if (!root.getChilds().get(word).getChilds().isEmpty()) {
					print(sb, root.getChilds().get(word).getChilds(), "--");
				}
			}

			System.out.println(sb);
		}

		private void print(StringBuilder sb, Map<String, TrieNode> childs, String s) {
			for (String word : childs.keySet()) {
				sb.append(s).append(word).append("\n");
				if (!childs.get(word).getChilds().isEmpty()) {
					print(sb, childs.get(word).getChilds(), s + "--");
				}
			}
		}
	}

	static class TrieNode {
		private Map<String, TrieNode> childs = new TreeMap<>();

		public Map<String, TrieNode> getChilds() {
			return childs;
		}
	}

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());

		Trie trie = new Trie();
		for (int i = 0; i < N; ++i) {
			String[] inputs = in.readLine().split(" ");
			trie.insert(inputs);
		}
		trie.print();
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}