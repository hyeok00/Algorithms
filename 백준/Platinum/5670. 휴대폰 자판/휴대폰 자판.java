import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static class TrieNode {
		public Map<Character, TrieNode> childNodes = new HashMap(); // 하위 문자 관리
		public int count; // 하위 단어들의 수
		public boolean isLast; // 마지막 문자인가?
	}

	static class Trie {
		TrieNode root;

		Trie() {
			root = new TrieNode();
		}

		void insert(String word) {
			TrieNode node = this.root;

			// 첫번째 문자부터 하위로 가면서, 파생단어의 수와 마지막 단어임을 기록
			for (int i = 0; i < word.length(); i++) {
				node.count++;
				node = node.childNodes.computeIfAbsent(word.charAt(i), c -> new TrieNode());
			}
			node.count++;
			node.isLast = true;
		}

		int getMinTypingCount(String word) {
			TrieNode node = this.root;

			int length = word.length();
			int count = 0;
			if (node.childNodes.size() == 1)
				count++;

			for (int i = 0; i < length; i++) {
				// 남은 하위 단어가 1개라면, 현재 입력으로 결정된다
				if (node.count == 1)
					break;

				if (node.childNodes.size() > 1 || node.isLast)
					count++;

				node = node.childNodes.get(word.charAt(i));

			}
			return count;
		}

	}

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String input = "";
		while ((input = in.readLine()) != null) {
			N = stoi(input);
			Trie myTrie = new Trie();
			List<String> list = new ArrayList<>();
			for (int i = 0; i < N; ++i) {
				String s = in.readLine();
				myTrie.insert(s);
				list.add(s);
			}

			int answer = 0;
			for (String str : list)
				answer += myTrie.getMinTypingCount(str);
			
			System.out.printf("%.2f\n", Math.round(((double)answer / (double)list.size()) * 100) * 0.01);
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}