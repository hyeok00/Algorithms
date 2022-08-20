#include<iostream>
#include<vector>
#include<queue>
using namespace std;

int visit[200002];

int bfs(int n, int k)
{
	queue<int> q;
	int ans = 0;

	q.push(n);

	int size=1;
	int nextsize = 0;
	int count = 0;

	while (!q.empty())
	{
		int temp = q.front();
		q.pop();

		visit[temp] = 1;
		if (temp == k)
		{
			return ans;
		}
		count++;

		if (temp > 0)
		{
			if (visit[temp - 1] == 0)
			{
				q.push(temp - 1);
				nextsize++;
			}
		}
		if (visit[temp * 2] == 0 && temp * 2 <= 100000)
		{
			q.push(temp * 2);
			nextsize++;
		}
		if (visit[temp + 1] == 0 && temp + 1 <= 100000)
		{
			q.push(temp + 1);
			nextsize++;
		}

		if (size == count)
		{
			ans++;
			size = nextsize;
			count = 0;
			nextsize = 0;
		}
	}
	return -1;
}
int main()
{
	ios::sync_with_stdio(false);
	int n, k;
	cin >> n >> k;
	cout << bfs(n, k);
	return 0;
}
