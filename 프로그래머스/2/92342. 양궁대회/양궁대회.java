import java.util.Arrays;

public class Solution {
	int[] apeach, ryan, answer;
	int N, maxScore;
	final int SIZE = 11;

	public int[] solution(int n, int[] info) {
		ryan = new int[SIZE];
		answer = new int[SIZE];
		N = n;
		apeach = info;
		maxScore = 0;
		perm(SIZE-1, 0);

		if (maxScore == 0)
			return new int[] {-1};
		return answer;
	}

	private void perm(int depth, int sum) {
		if (sum > N)
			return;

		if (depth == -1) {
			// 화살을 모두 소비하지 않는 경우는 생략
			if (sum != N)
				return;

			int ryanScore = 0;
			int apeachScore = 0;
			for (int i = 0; i < SIZE; ++i) {
				// 같은 수를 맞힌경우
				if (ryan[i] == apeach[i]) {
					// 어피치가 0발이 아니라면, 점수를 얻는다.
					if (apeach[i] != 0)
						apeachScore += (10 - i);
				} else if (ryan[i] > apeach[i]) {
					ryanScore += (10 - i);
				} else {
					apeachScore += (10 - i);
				}
			}

			int diff = ryanScore - apeachScore;
			if (diff > maxScore) {
				maxScore = diff;
				answer = Arrays.copyOf(ryan, SIZE);
			}
			return;
		}

		for (int i = N; i >= 0; --i) {
			ryan[depth] = i;
			perm(depth - 1, sum + i);
		}
	}
}
