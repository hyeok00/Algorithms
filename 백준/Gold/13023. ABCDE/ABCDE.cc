#include<iostream>
#include<vector>
using namespace std;

int visit[2001] = {};
vector<int> arr[2001];
int flag = 0;
void init()
{
	for (int i = 0; i < 2001; i++)
	{
		visit[i] = 0;
	}
}
int search(int start, int n)
{
	if (n >= 4) 
	{
		flag = 1;
		return 0;
	}
	if (!visit[start])
	{
		visit[start] = 1;
	}
	for (int i = 0; i < arr[start].size(); i++)
	{
		int temp = arr[start][i];
		if (!visit[temp])
		{
			search(temp, n+1);
		}
		if (flag)
			return 0;
	}
    visit[start]=0;
	return 0;
}
int main()
{
	ios::sync_with_stdio(false);

	int n, m;
	cin >> n >> m;

	for (int i = 0; i < m; i++)
	{
		int a, b;
		cin >> a >> b;
		arr[a].push_back(b);
		arr[b].push_back(a);
	}

	for (int i = 0; i < n; i++)
	{
		flag = 0;
		search(i, 0);
		if (flag)
		{
			cout << 1;
			return 0;
		}
		init();
	}
	cout << 0;
	return 0;
}