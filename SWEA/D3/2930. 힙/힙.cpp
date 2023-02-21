#include<iostream>
#include<queue>
using namespace std;

int T, N, temp;
priority_queue<int> pq;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;
	for (int tc = 1; tc <= T; ++tc)
	{
		cin >> N;
		pq = priority_queue<int>();

		cout << "#" << tc;
		for (int i = 0; i < N; ++i)
		{
			cin >> temp;
			if (temp == 1)
			{
				cin >> temp;
				pq.push(temp);
			}
			else
			{
				if (pq.empty())
					cout << " -1";
				else
				{
					cout << " " << pq.top();
					pq.pop();
				}
			}
		}
		cout << "\n";
	}
	return 0;
}
