#include<iostream>
#include<string>
using namespace std;

int T, N, value, target, current, cnt;
int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cin >> T;
	target = (1 << 10) - 1;
	for (int tc = 0; tc < T; ++tc)
	{
		cin >> N;
		cnt = 1;
		current = 0;
		while (true)
		{
			string str = to_string(cnt * N);
			for (int i = 0; i < str.length(); ++i) {
				int num = str[i] - '0';
				current = current | (1 << num);
			}

			if (target == current)
			{
				break;
			}
			cnt++;

		}
		cout << "#" << tc+1 << " " << cnt*N << "\n";
	}
	return 0;
}
