#include <iostream>
#include <string>
#include <vector>
#include <queue>
#define MAX_PLAYER 100
using namespace std;

vector<int> winVec[MAX_PLAYER + 1];
vector<int> loseVec[MAX_PLAYER + 1];

int solution(int n, vector<vector<int>> results) {
	int answer = 0;

	for (const auto& it : results)
	{
		winVec[it[0]].push_back(it[1]);		// A가 B에게 이김
		loseVec[it[1]].push_back(it[0]);	// A가 B에게 짐
	}

	for (int cur_idx = 1; cur_idx <= n; ++cur_idx)
	{
		int win_visit[MAX_PLAYER + 1] = { 0, }; // 방문 정보 체크

		queue<int> bfs_queue;
		bfs_queue.push(cur_idx);
		win_visit[cur_idx] = 1;
		while (!bfs_queue.empty())
		{
			int top = bfs_queue.front();
			bfs_queue.pop();
			for (const auto it : winVec[top])
			{
				if (0 == win_visit[it])
				{
					win_visit[it]++;
					bfs_queue.push(it);
					// 방문한 적이 없다면 방문처리 후, 해당 위치에서 탐색
				}
			}
		}

		// 역방향 순회도 해보자.
		int lose_visit[MAX_PLAYER + 1] = { 0, }; // 방문 정보 체크

		bfs_queue.push(cur_idx);
		lose_visit[cur_idx] = 1;
		while (!bfs_queue.empty())
		{
			int top = bfs_queue.front();
			bfs_queue.pop();
			for (const auto it : loseVec[top])
			{
				if (0 == lose_visit[it])
				{
					lose_visit[it]++;
					bfs_queue.push(it);
					// 방문한 적이 없다면 방문처리 후, 해당 위치에서 탐색
				}
			}
		}

		int sum = 0;
		for (int visit_idx = 1; visit_idx <= n; ++visit_idx)
		{
			sum += win_visit[visit_idx] + lose_visit[visit_idx];
		}
		if (sum - 1 == n)
			answer++;
	}

	return answer;
}