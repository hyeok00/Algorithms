#include<iostream>
#include<queue>
using namespace std;

int map[101][101];
int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

int N, RESULT;

int max(int a, int b) {
	return a > b ? a : b;
}

int find(int depth) {

	int myMap[101][101];
	for (int i = 0; i < N; ++i)
	{
		for (int j = 0; j < N; ++j)
		{
			if (map[i][j] <= depth)
				myMap[i][j] = 0;
			else
				myMap[i][j] = map[i][j];
		}
	}

	int count = 0;
	for (int i = 0; i < N; ++i)
	{
		for (int j = 0; j < N; ++j)
		{
			if (myMap[i][j] > 0)
			{
				count++;
				queue<pair<int, int>> q;
				q.push({ i,j });
				myMap[i][j] = 0;
				while (!q.empty())
				{
					pair<int, int> current = q.front();
					q.pop();
					int x = current.first;
					int y = current.second;
					for (int d = 0; d < 4; ++d)
					{
						int nextX = x + dx[d];
						int nextY = y + dy[d];
						if (nextX >= 0 && nextX < N && nextY>=0 && nextY < N)
						{
							if (myMap[nextX][nextY] > 0)
							{
								myMap[nextX][nextY] = 0;
								q.push({ nextX,nextY });
							}
						}
					}

				}
			}
		}
	}
	return count;
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;
	int maxDepth = -1;
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < N; ++j) {
			cin >> map[i][j];
			maxDepth = max(map[i][j], maxDepth);
		}
	}

	RESULT = 0;
	for (int depth = 0; depth < maxDepth; ++depth) {
		RESULT = max(RESULT, find(depth));
	}
	cout << RESULT;
	return 0;
}