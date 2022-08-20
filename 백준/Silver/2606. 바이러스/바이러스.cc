#include<iostream>
#include<vector>
#include<queue>
using namespace std;

int visit[101];
vector<int> arr[101];

void bfs(int start, int num)
{
	queue<int> q;
	q.push(start);
	visit[start] = 1;

	while (!q.empty())
	{
		int temp = q.front();
		q.pop();

		for (int i = 0; i < arr[temp].size(); i++)
		{
			if (visit[arr[temp][i]] == 0)
			{
				visit[arr[temp][i]] = 1;
				q.push(arr[temp][i]);
				num++;
			}
		}
	}
	cout << num;
}

int main()
{
	ios::sync_with_stdio(false);

	int size, count;
	cin >> size >> count;
	for (int i = 0; i < count; i++)
	{
		int a, b;
		cin >> a >> b;
		arr[a].push_back(b);
		arr[b].push_back(a);
	}
	int num = 0;
	bfs(1, num);

	return 0;
}