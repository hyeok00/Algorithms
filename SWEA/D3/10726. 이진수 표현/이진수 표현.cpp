#include<iostream>
using namespace std;

int T, N, M, current, target;
int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cin >> T;
	
	for (int tc = 0; tc < T; ++tc)
	{
		cin >> N >> M;
		current = 0;
		target = (1<<N)-1;
		if((M & target) == target)
			cout << "#" << tc + 1 << " ON" << "\n";
		else
			cout << "#" << tc + 1 << " OFF" << "\n";
	}
	return 0;
}
