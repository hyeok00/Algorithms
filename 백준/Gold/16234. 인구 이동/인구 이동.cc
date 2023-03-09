#include<iostream>
#include<queue>
#include<vector>
using namespace std;

int N, L, R, group;
int map[51][51];
int isVisit[51][51];
int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

void init()
{
	group = 1;
	for (int i = 0; i < N; ++i)
		for (int j = 0; j < N; ++j)
			isVisit[i][j] = 0;
}

void bfs(int x, int y)
{
	queue<pair<int, int>> q;
	q.push({ x,y });
	isVisit[x][y] = group;
	vector<pair<int,int>> vec;
	vec.push_back({ x,y });
	int sum = map[x][y];
	while (!q.empty())
	{
		pair<int, int> p = q.front();
		q.pop();
		for (int d = 0; d < 4; ++d) {
			int nextX = p.first + dx[d];
			int nextY = p.second + dy[d];
			if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
				int value = map[p.first][p.second] - map[nextX][nextY];
				value = value < 0 ? value * -1 : value;
				if (isVisit[nextX][nextY] == 0 && L <= value && value <= R) {
					sum += map[nextX][nextY];
					vec.push_back({ nextX,nextY });
					q.push({ nextX,nextY });
					isVisit[nextX][nextY] = group;
				}
			}
		}
	}
	int reValue = sum / vec.size();
	for (pair<int, int> &p : vec) {
		map[p.first][p.second] = reValue;
	}
}

bool check(int x,int y) {
	for (int d = 0; d < 4; ++d) {
		int nextX = x + dx[d];
		int nextY = y + dy[d];
		if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
			int value = map[x][y] - map[nextX][nextY];
			value = value < 0 ? value * -1 : value;
			if (L <= value && value <= R) {
				return true;
			}
		}
	}
	return false;
}
int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> L >> R;
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < N; ++j) {
			cin >> map[i][j];
		}
	}

	int count = 0;
	while (true) {
		init();
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (isVisit[i][j] == 0 && check(i,j)) { 
					bfs(i, j);
					group++;
				}
			}
		}
		if (group == 1)
			break;
		count++;
	}
	cout << count << "\n";
	return 0;
}