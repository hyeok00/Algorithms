#include<iostream>
using namespace std;

int fee[4] = { 0, };
int plan[12 + 1] = { 0, };
int result;

void simulation(int startMonth, int accSum) {
	if (startMonth > 12) {
		if (accSum < result)
			result = accSum;
		return;
	}
	int sum = 0;
	sum += fee[3];
	if (accSum + sum < result)
		simulation(startMonth + 12, accSum + sum);

	sum = 0;
	sum += fee[2];
	if (accSum + sum < result)
		simulation(startMonth + 3, accSum + sum);

	sum = 0;
	sum = fee[1];
	if (accSum + sum < result)
		simulation(startMonth + 1, accSum + sum);

	sum = plan[startMonth] * fee[0];
	if (accSum + sum < result)
		simulation(startMonth + 1, accSum + sum);
}

int main() {
	ios::sync_with_stdio(false);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; ++tc) {
		for (int i = 0; i < 4; ++i) {
			cin >> fee[i];
		}

		for (int i = 1; i <= 12; ++i) {
			cin >> plan[i];
		}

		result = INT32_MAX;
		simulation(1, 0);
		printf("#%d %d\n", tc, result);
	}
}