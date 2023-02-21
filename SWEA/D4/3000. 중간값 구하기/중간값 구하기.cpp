#include<iostream>
#include<queue>
using namespace std;

#define DIVIDER 20171109;

int T, N, A, SUM;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;
	for (int tc = 1; tc <= T; ++tc)
	{
		SUM = 0;
		priority_queue<int> maxHeap;
		priority_queue<int, vector<int>, greater<int>> minHeap;

		cin >> N >> A;

		maxHeap.push(A);
		for (int i = 0; i < N; ++i)
		{
			for (int j = 0; j < 2; ++j) {
				cin >> A;
				if (maxHeap.top() > A)
					maxHeap.push(A);
				else
					minHeap.push(A);

				if (maxHeap.size() > minHeap.size() + 1)
				{
					minHeap.push(maxHeap.top());
					maxHeap.pop();
				}
				else if (minHeap.size() > maxHeap.size())
				{
					maxHeap.push(minHeap.top());
					minHeap.pop();
				}
				else
				{

				}
			}
			SUM = (SUM + maxHeap.top()) % DIVIDER;
		}
		cout << "#" << tc << " " << SUM << "\n";
	}
	return 0;
}