#include<iostream>
using namespace std;

#define MOD 1234567891

int main() {
	ios::sync_with_stdio(false);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; ++tc) {
		int N, K;
		cin >> N >> K;

		long long answer = 1;
		for (int i = K + 1; i <= N; ++i) {
			answer = (answer * i) % MOD;
		}
		long long temp = 1;
		for (int i = 1; i <= N - K; ++i) {
			temp = temp * i % MOD;
		}
		long long k = MOD-2;
		while (k > 0) {
			if (k % 2) {
				answer = (answer * temp) % MOD;
			}
			k /= 2;
			temp = (temp * temp) % MOD;
		}
		printf("#%d %lld\n", tc, answer);
	}
}