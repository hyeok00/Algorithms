import java.util.Stack;

class Solution {
	final int BREAD = 1;
	final int VEGETABLE = 2;
	final int MEAT = 3;

	Stack<Integer> s = new Stack<>();
	int[] temp = new int[4];

	public int solution(int[] ingredient) {
		int answer = 0;

		for (int current : ingredient) {
			s.add(current);
			if (current == BREAD && check()) {
				answer++;
			}
		}
		if (check())
			answer++;
		return answer;
	}

	private boolean check() {
		if (s.size() < 4)
			return false;

		boolean flag = true;
		for (int i = 0; i < 4; ++i)
			temp[i] = s.pop();

		if (temp[0] != BREAD)
			flag = false;
		if (temp[0] != temp[3])
			flag = false;
		if (temp[1] != MEAT)
			flag = false;
		if (temp[2] != VEGETABLE)
			flag = false;

		if (!flag)
			for (int i = 3; i >= 0; --i)
				s.push(temp[i]);

		return flag;
	}
}