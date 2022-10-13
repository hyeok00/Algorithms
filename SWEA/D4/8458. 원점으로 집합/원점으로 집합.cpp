#include<iostream>
using namespace std;

int main() {
	ios::sync_with_stdio(false);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; ++tc) {
		int N;
		cin >> N;
		int oddCount = 0;
		int hasMaxDistanceIndex = -1;
		int maxDistance = 0;
		for (int i = 0; i < N; ++i) {
			int x, y;
			cin >> x >> y;
			x = x < 0 ? x * -1 : x;
			y = y < 0 ? y * -1 : y;
			int distance = x + y;
			if (distance % 2 == 1)
				oddCount++;
			if (maxDistance < distance) {
				maxDistance = distance;
				hasMaxDistanceIndex = i;
			}
		}
		if (N - oddCount != 0 && N - oddCount != N) {
			printf("#%d -1\n",tc);
		}
		else {
			int available = 0;
			int count = 0;
			while (true) {
				available += count;
				if (maxDistance <= available && (available - maxDistance) % 2 == 0)
					break;
				++count;
			}
			printf("#%d %d\n", tc,count);
		}
	}
}