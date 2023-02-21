#include<iostream>
#include<string>
using namespace std;

#define DIVIDER 20171109;

int T;
int dp[1001][1001];
string first, second;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;
	for (int tc = 1; tc <= T; ++tc)
	{
		cin >> first >> second;
		int length1 = first.length();
		int length2 = second.length();

		for (int i = 1; i <= length1; ++i) {
			for (int j = 1; j <= length2; ++j) {
				if (first[i - 1] == second[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				else {
					dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
				}
			}
		}
		cout << "#" << tc << " " << dp[length1][length2] << "\n";
	}
	return 0;
}