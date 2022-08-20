#include<queue>
#include<stdio.h>
#include<vector>
using namespace std;

int arr[52][52];
int visit[52][52];

void init()
{
	for (int i = 0; i < 52; i++)
	{
		for (int j = 0; j < 52; j++)
		{
			arr[i][j] = 0;
			visit[i][j] = 0;
		}
	}
}

void bfs(int x, int y)
{
	arr[x][y] = 0;
	if (arr[x - 1][y] == 1)
	{
		bfs(x - 1, y);
	}
	if (arr[x + 1][y] == 1)
	{
		bfs(x + 1, y);
	}
	if (arr[x][y - 1] == 1)
	{
		bfs(x, y - 1);
	}
	if (arr[x][y + 1] == 1)
	{
		bfs(x, y + 1);
	}

	if (arr[x - 1][y - 1] == 1)
	{
		bfs(x - 1, y - 1);
	}
	if (arr[x + 1][y + 1] == 1)
	{
		bfs(x + 1, y + 1);
	}
	if (arr[x + 1][y - 1] == 1)
	{
		bfs(x + 1, y - 1);
	}
	if (arr[x - 1][y + 1] == 1)
	{
		bfs(x - 1, y + 1);
	}
}

int main()
{
	vector<int> answer;

	while (1)
	{
		init();
		int w, h;

		scanf("%d %d", &w, &h);
		if (w == 0 && h == 0)
			return 0;

		for (int i = 1; i < h + 1; i++)
		{
			for (int j = 1; j < w + 1; j++)
			{
				int temp;
				scanf("%d", &temp);
				arr[i][j] = temp;
			}
		}

		int count = 0;
		for (int i = 1; i < h + 1; i++)
		{
			for (int j = 1; j < w + 1; j++)
			{
				if (arr[i][j] == 1)
				{
					bfs(i, j);
					count++;
				}
			}
		}
		printf("%d\n", count);
	}
	return 0;
}