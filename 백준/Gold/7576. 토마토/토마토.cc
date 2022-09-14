#include<vector>
#include<iostream>
using namespace std;


int arr[1000][1000];
int visit[1000][1000];

int rear = -1;
int front = 0;
struct point
{
	int x;
	int y;
};

point pointqueue[1000000];

void enqueue(point p)
{
	rear++;
	pointqueue[rear] = p;
}
point dequeue()
{
	return pointqueue[front++];
}
bool isempty()
{
	if (rear < front)
		return true;
	else
		return false;
}

int bfs(int m, int n)
{
	while (!isempty())
	{
		point p;
		p = dequeue();

		if (visit[p.x + 1][p.y] == 0 && p.x + 1 < n)
		{
			point pt = { p.x + 1,p.y };
			visit[p.x + 1][p.y] = visit[p.x][p.y] + 1;
			enqueue(pt);
		}

		if (visit[p.x - 1][p.y] == 0 && p.x > 0)
		{
			point pt = { p.x - 1,p.y };
			visit[p.x - 1][p.y] = visit[p.x][p.y] + 1;
			enqueue(pt);
		}

		if (visit[p.x][p.y + 1] == 0 && p.y + 1 < m)
		{
			point pt = { p.x,p.y + 1 };
			visit[p.x][p.y + 1] = visit[p.x][p.y] + 1;
			enqueue(pt);
		}

		if (visit[p.x][p.y - 1] == 0 && p.y > 0)
		{
			point pt = { p.x,p.y - 1 };
			visit[p.x][p.y - 1] = visit[p.x][p.y] + 1;
			enqueue(pt);
		}
	}

	int max = 0;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			if (visit[i][j] == 0)
				return -1;
			else
			{
				if (max < visit[i][j])
					max = visit[i][j];
			}
		}
	}
	return max - 1;
}


int main()
{
	ios::sync_with_stdio(false);
	int m, n;
	cin >> m >> n;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			int state;
			cin >> state;
			arr[i][j] = state;
			if (state == 1)
			{
				point p = { i,j };
				visit[i][j] = 1;
				enqueue(p);
			}
			if (state == -1)
				visit[i][j] = -1;
		}
	}
	cout << bfs(m, n) << "\n";
	
	return 0;
}