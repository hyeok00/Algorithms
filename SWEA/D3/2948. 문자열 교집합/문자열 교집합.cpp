#include<iostream>
#include<string>
#include<unordered_set>
using namespace std;

int T, N, M;
string temp;
int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;
	for (int tc = 1; tc <= T; ++tc)
	{
		cin >> N >> M;
		unordered_set<string> mySet;
		
		for (int i = 0; i < N; ++i)
		{
			cin >> temp;
			mySet.insert(temp);
		}
		for (int i = 0; i < M; ++i)
		{
			cin >> temp;
			mySet.insert(temp);
		}

		cout << "#" << tc << " " << N + M - mySet.size() << "\n";
	}
	return 0;
}