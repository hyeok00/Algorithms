class Solution {
	int MOD = 20170805;
	final int TOP = 0;
	final int LEFT = 1;

	int M, N;
	int[][] arr, map;
	public int solution(int m, int n, int[][] cityMap) {
		M = m;
		N = n;
		map = cityMap;
		arr = new int[M][N];

		arr[0][0] = 1;

		for(int i = 1 ; i < N; ++i)
			if(cityMap[0][i] != 1) // 통행금지가 아니라면
				arr[0][i] = arr[0][i - 1];

		for(int i = 1 ; i < M; ++i)
			if(cityMap[i][0] != 1) // 통행금지가 아니라면
				arr[i][0] = arr[i - 1][0];

		for(int x = 1 ; x < M; ++x){ // 세로
			for(int y = 1 ; y < N; ++y){ // 가로
				// (x,y)지점에 가기위한 경우의 수는 위에서 오는 경우와 아래에서 오는 경우를 합친 수이다.
				if(map[x][y] == 0)
					arr[x][y] = (getCount(x, y, LEFT) + getCount(x, y, TOP)) % MOD;
				else
					arr[x][y] = 0;
			}
		}
		return arr[M - 1][N - 1];
	}

	int[] dx = {-1, 0};
	int[] dy = {0, -1};
	public int getCount(int x, int y, int d){
		int fixedX = x + dx[d];
		int fixedY = y + dy[d];

		// 범위 외부인경우 해당 방향에서 오는 경우가 없는것이다.
		if(!isInRange(fixedX, fixedY))
			return 0;

		// 범위 내부인경우
		// 해당 위치에서 자유롭게 이동가능한 경우
		if(map[fixedX][fixedY] == 0)
			return arr[fixedX][fixedY];

		// 해당 위치가 통행금지인 경우
		if(map[fixedX][fixedY] == 1)
			return 0;

		// 해당 위치에 회전제한이 있는 경우
		if(map[fixedX][fixedY] == 2){
			return getCount(fixedX, fixedY, d);
		}
		return 0;
	}

	public boolean isInRange(int x, int y){
		if(0 <= x && x < M && 0 <= y && y < N)
			return true;
		return false;
	}
}