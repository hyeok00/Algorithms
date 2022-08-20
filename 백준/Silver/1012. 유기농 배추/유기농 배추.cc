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
	if (arr[x-1][y]==1)
	{
		bfs(x - 1, y);
	}
	if (arr[x+1][y]==1)
	{
		bfs(x + 1, y);
	}
	if (arr[x][y-1]==1)
	{
		bfs(x, y-1);
	}
	if (arr[x][y+1]==1)
	{
		bfs(x, y+1);
	}
}

int main()
{
	vector<int> answer;
	int testcase;
	scanf("%d", &testcase);
	for (int i = 0; i < testcase; i++)
	{
		init();
		int M, N, K;
		scanf("%d %d %d", &M, &N, &K);
		for (int j = 0; j < K; j++)
		{
			int a, b;
			scanf("%d %d", &a, &b);
			arr[a+1][b+1] = 1;
		}
		int count = 0;
		for (int p = 1; p < M + 1; p++)
		{
			for (int q = 1; q < N + 1; q++)
			{
				if (arr[p][q] == 1)
				{
					bfs(p, q);
					count++;
				}
			}
		}
		answer.push_back(count);
	}
	
	for (int i = 0; i < testcase; i++)
	{
		printf("%d\n", answer[i]);
	}
	return 0;
}