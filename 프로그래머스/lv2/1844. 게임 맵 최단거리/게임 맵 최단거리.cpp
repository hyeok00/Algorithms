#include<vector>
#include<queue>
using namespace std;
int solution(vector<vector<int>> maps)
{
	int visit[102][102] = { 0, };

	int row = maps.size();
	int col = maps[0].size();

	queue<pair<int, int>> myQ;
	myQ.push({ 1,1 });
	visit[1][1] = 1;
	while (!myQ.empty())
	{
		pair<int, int> pos = myQ.front();
		myQ.pop();
		int x = pos.first;
		int y = pos.second;

		//상하좌우
		if ((x > 1) && (maps[x - 2][y - 1] == 1) && (visit[x - 1][y] == 0))
		{
			myQ.push({ x - 1, y });
			visit[x - 1][y] = visit[x][y] + 1;
		}
		if ((x < row) && (maps[x][y - 1] == 1) && (visit[x + 1][y] == 0))
		{
			myQ.push({ x + 1,y });
			visit[x + 1][y] = visit[x][y] + 1;
		}
		if ((y > 1) && (maps[x - 1][y - 2] == 1) && (visit[x][y - 1] == 0))
		{
			myQ.push({ x, y - 1 });
			visit[x][y - 1] = visit[x][y] + 1;
		}
		if ((y < col) && (maps[x - 1][y] == 1) && (visit[x][y + 1] == 0))
		{
			myQ.push({ x, y + 1 });
			visit[x][y + 1] = visit[x][y] + 1;
		}
	}

	int answer = 0;
	if (0 == visit[row][col])
		answer = -1;
	else
		answer = visit[row][col];

	return answer;
}