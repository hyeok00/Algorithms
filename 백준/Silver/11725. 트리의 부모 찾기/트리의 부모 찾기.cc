#include<iostream>
#include<vector>
#include<queue>
using namespace std;

int N;
vector<int> vec[100001];
bool isVisit[100001];
int arr[100001] = { 0, };

int main() {
	ios::sync_with_stdio(false);

	cin >> N;
	for (int i = 0; i < N-1; ++i) {
		int first, second;
		cin >> first >> second;
		vec[first].push_back(second);
		vec[second].push_back(first);
	}

	queue<int> q;
	isVisit[1] = true;
	q.push(1);
	while (!q.empty())
	{
		int cur = q.front();
		q.pop();
		for (int i = 0; i<vec[cur].size(); ++i) {
			int value = vec[cur][i];
			if (!isVisit[value]) {
				isVisit[value] = true;
				arr[value] = cur;
				q.push(value);
			}
		}
	}
	for (int i = 2; i <= N; ++i) {
		cout << arr[i] << "\n";
	}
	return 0;
}