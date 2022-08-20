#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

vector<int> arr[50001];
int visit[20001] = { 0, };

int solution(int n, vector<vector<int>> edge)
{
	int answer = 0;

	size_t len = edge.size();
	for (size_t edge_idx = 0; edge_idx < len; ++edge_idx)
	{
		arr[edge[edge_idx][0]].push_back(edge[edge_idx][1]);  // a -> b
		arr[edge[edge_idx][1]].push_back(edge[edge_idx][0]);  // b -> a
	}

	for (size_t edge_idx = 0; edge_idx < len; ++edge_idx)
	{
		sort(arr[edge_idx].begin(), arr[edge_idx].end());
	}

	queue<int> bfs_queue;
	bfs_queue.push(1);
	visit[1] = 1;
	while (!bfs_queue.empty())
	{
		int cur = bfs_queue.front();
		bfs_queue.pop();

		for (const auto it : arr[cur])
		{
			if (visit[it] == 0)
			{
				bfs_queue.push(it);
				visit[it] += visit[cur]+1;
			}
		}
	}
	
	int maxVal = *max_element(visit + 1, visit + n);
	for (int idx = n; idx >= 1; --idx)
	{
		if (visit[idx] == maxVal)
			answer++;
	}
    
	return answer;
}