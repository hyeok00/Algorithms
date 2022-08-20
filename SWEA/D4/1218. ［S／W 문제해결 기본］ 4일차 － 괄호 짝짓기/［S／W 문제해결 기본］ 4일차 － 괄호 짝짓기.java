import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		final int T = 10;

		for (int tc = 1; tc <= T; ++tc) {
			int res = 1;
			Stack<Character> s = new Stack<>();
			int length = Integer.parseInt(in.readLine());
			String input = in.readLine();
			for (int i = 0; i < length; ++i) {
				char c = input.charAt(i);
				boolean isOpen = getOpenchar(c);

				if (true == isOpen) {
					s.push(c);
				} else {
					char topVlaue = s.peek();
					char another = getAnotherPair(c);
					if (another == topVlaue) {
						s.pop();
					} else {
						res = 0;
						break;
					}
				}
			}
			if(false == s.empty()) {
				res = 0;
			}
			sb.append("#" + tc + " " + res + "\n");
		}
		System.out.println(sb);
	}

	public static boolean getOpenchar(char c) {
		boolean res = false;
		switch (c) {
		case '[':
		case '(':
		case '<':
		case '{':
			res = true;
			break;
		default:
			break;
		}
		return res;
	}

	public static char getAnotherPair(char c) {
		char res = '0';
		switch (c) {
		case ']':
			res = '[';
			break;
		case ')':
			res = '(';
			break;
		case '>':
			res = '<';
			break;
		case '}':
			res = '{';
			break;
		default:
			break;
		}
		return res;
	}
}
