#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>
using namespace std;

int arr[27][27];
int visit[27][27];
int num=0;

void bfs(int x, int y)
{
	visit[x][y] = 1;
	arr[x][y] = 0;
	if (arr[x - 1][y] == 1 && visit[x - 1][y] == 0)
	{
		bfs(x - 1, y);
		num++;
		arr[x - 1][y] = 0;
		visit[x - 1][y] = 1;
	}
	if (arr[x + 1][y] == 1 && visit[x + 1][y] == 0)
	{
		bfs(x + 1, y);
		num++;
		arr[x + 1][y] = 0;
		visit[x + 1][y] = 1;
	}
	if (arr[x][y - 1] == 1 && visit[x][y - 1] == 0)
	{
		bfs(x, y - 1);
		num++;
		arr[x][y - 1] = 0;
		visit[x][y - 1] = 1;
	}
	if (arr[x][y + 1] == 1 && visit[x][y + 1] == 0)
	{
		bfs(x, y + 1);
		num++;
		arr[x][y + 1] = 0;
		visit[x][y + 1] = 1;
	}
}

int main()
{

	int n;
	cin >> n;
	vector<int> answer;
	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			int state;
			scanf("%1d", &state);
			arr[i][j] = state;
		}
	}
	int count = 0;
	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			if (visit[i][j] == 0 && arr[i][j] == 1)
			{
				bfs(i, j);
				answer.push_back(num+1);
				num = 0;
				count++;
			}
		}
	}

	sort(answer.begin(), answer.end());
	printf("%d\n", count);
	for (int i = 0; i < answer.size(); i++)
	{
		printf("%d\n", answer[i]);
	}
	return 0;
}