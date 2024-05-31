import java.util.Arrays;

class Solution {
	int SIZE = 3;
	char[][] arr = new char[SIZE][SIZE];

	public int solution(String[] board) {
		for (int i = 0; i < SIZE; ++i)
			arr[i] = board[i].toCharArray();

		// Step1. 순서가 어긋난 경우.
		int numP1 = getCount('O');
		int numP2 = getCount('X');
		if (!((numP1 == numP2) || (numP1 == numP2 + 1)))
			return 0;

		// Step2. 종료되었음에도 계속 진행된 경우
		boolean p1CanWin = canWin('O');
		boolean p2CanWin = canWin('X');

		// Step2-1. 둘 다 승리할 수 있게 두는 경우는 나오지 않는다.
		if (p1CanWin && p2CanWin)
			return 0;

		// Step2-2. p1가 승리한다면 반드시 하나 더 표시했다.
		if (p1CanWin && !(numP1 == numP2 + 1))
			return 0;

		// Step2-3. p2가 승리한다면 반드시 같은 수를 표시했다.
		if (p2CanWin && !(numP1 == numP2))
			return 0;

		return 1;
	}

	private boolean canWin(char c) {
		for (int i = 0; i < SIZE; ++i) {
			// 가로 체크
			if ((arr[i][0] == arr[i][1]) && (arr[i][0] == arr[i][2]) && (arr[i][0] == c))
				return true;
			// 세로 체크
			if ((arr[0][i] == arr[1][i]) && (arr[0][i] == arr[2][i]) && (arr[0][i] == c))
				return true;
		}
		// 좌상-우하 대각
		if ((arr[0][0] == arr[1][1]) && (arr[0][0] == arr[2][2]) && (arr[0][0] == c))
			return true;
		// 좌하-우상 대각
		if ((arr[0][2] == arr[1][1]) && (arr[0][2] == arr[2][0]) && (arr[0][2] == c))
			return true;

		return false;
	}

	public int getCount(char target) {
		return (int)Arrays.stream(arr)
			.flatMapToInt(chars -> new String(chars).chars())
			.filter(c -> c == target)
			.count();
	}
}