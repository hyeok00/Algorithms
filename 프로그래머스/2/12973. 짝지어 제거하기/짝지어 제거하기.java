import java.util.*;

class Solution {
	public int solution(String s) {
		ArrayDeque<Character> data = new ArrayDeque();
		for (int i = 0; i < s.length(); ++i)
			data.addLast(s.charAt(i));

		int prevSize = 0;
		int curSize = data.size();
		while (prevSize != curSize) {
			prevSize = curSize;
			ArrayDeque<Character> stack = new ArrayDeque();
			for (char c : data) {
                if (!stack.isEmpty() && stack.peekLast() == c) {
                    stack.pollLast();
					continue;
				}
				stack.addLast(c);
			}
			data = stack;
			curSize = data.size();
		}
		if (data.size() == 0)
			return 1;
		return 0;
	}
}