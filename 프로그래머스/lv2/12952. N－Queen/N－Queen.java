class Solution {
	static int[][] map;
	static int[] dx = { -1, -1, 1, 1 };
	static int[] dy = { -1, 1, 1, -1 };
    static int count;
    public int solution(int n) {
        count = 0;
        
        map = new int[n][n];
		simulation(n, 0);
        
        return count;
    }
    
    private static void simulation(int N, int depth) {	
		if (N == depth) {
			count++;
			return;
		}
		
		for (int i = 0; i < N; ++i) {
			if (check(i, N, depth)) {
				map[depth][i] = 1;
				simulation(N, depth + 1);
				map[depth][i] = 0;
			}
		}
	}

	private static boolean check(int col, int size, int depth) {
		for (int i = 0; i < size; ++i) {
			if (map[i][col] == 1)
				return false;
			if (map[depth][i] == 1)
				return false;
		}

		for (int i = 0; i < 4; ++i) {
			int curX = depth;
			int curY = col;
			int nextX = curX;
			int nextY = curY;
			while (true) {
				nextX = nextX + dx[i];
				nextY = nextY + dy[i];
				if (0 <= nextX && nextX < size && 0 <= nextY && nextY < size) {
					if (map[nextX][nextY] == 1)
						return false;
				} else {
					break;
				}
			}
		}

		return true;
	}
}