#include <string>
#include <vector>

using namespace std;

int visit[201] = { 0, };

void dfs(int start, vector<vector<int>> computers)
{
	// 방문하지 않았다면 해당 Node 방문처리 후, 연결된 Node 탐색
	if (0 == visit[start])
	{
		visit[start] = 1;
		for (int i = 0; i < computers.size(); ++i)
		{
			if (0 == visit[i] && start != i && 1 == computers[start][i])
			{
				dfs(i, computers);
			}
		}
	}
	else
	{
		// nothing
	}
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;

    for (int i = 0; i < n; ++i)
    {
        if (0 == visit[i])
        {
            dfs(i, computers);
            answer++;
        }
    }
    return answer;
}