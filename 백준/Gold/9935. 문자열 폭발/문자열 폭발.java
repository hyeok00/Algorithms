import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static StringBuilder sb, target;
	static String[] splitedLine;
	static Stack<Character> stack;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder(in.readLine());
		target = new StringBuilder(in.readLine());

		target.reverse();
		char needCheck = target.charAt(0);
		stack = new Stack<>();
		for (int i = 0; i < sb.length(); ++i) {
			char curChar = sb.charAt(i);
			stack.add(sb.charAt(i));

			if (needCheck == curChar) {
				check();
			}
		}

		StringBuilder res = new StringBuilder();
		for (int i = stack.size(); i > 0; --i) {
			res.append(stack.pop());
		}
		if (res.length() == 0)
			res.append("FRULA");
		else
			res.reverse();
		System.out.println(res);
	}

	public static void check() {
		if (stack.size() < target.length())
			return;

		StringBuilder sb = new StringBuilder();
		int count = target.length();
		while (count-- > 0) {
			char c = stack.pop();
			sb.append(c);
		}
		if (sb.toString().equals(target.toString()))
			return;

		for (int i = sb.length()-1; i >=0; --i) {
			stack.add(sb.charAt(i));
		}
		return;
	}
}