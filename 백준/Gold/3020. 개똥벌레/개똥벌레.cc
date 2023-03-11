#include<iostream>
using namespace std;

int N, H;
int bot[500001] = { 0, };
int top[500001] = { 0, };

int min(int a, int b) {
	return a > b ? b : a;
}
int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	int value;
	cin >> N >> H;
	for (int i = 1; i <= N; ++i) {
		cin >> value;
		if (i & 1)
			bot[value]++;
		else
			top[H - value + 1]++;
	}
	for (int i = 1; i <= H; i++) {
		top[i] += top[i - 1];
		bot[H - i] += bot[H - i + 1];
	}
	int ans = INT32_MAX;
	int cnt = 0;
	for (int i = 1; i <= H; i++) {
		if (top[i] + bot[i] < ans) {
			cnt = 1;
			ans = top[i] + bot[i];
		}
		else if (top[i] + bot[i] == ans) {
			cnt++;
		}
	}
	cout << ans << " " << cnt;
	return 0;
}