#include<iostream>
using namespace std;

int arr[11] = { 0,1,2,4, };
int sol(int n)
{
	if (n <= 3)
		return arr[n];
	if (arr[n])
		return arr[n];
	arr[n] = sol(n - 3) + sol(n - 2) + sol(n - 1);
	return arr[n];
}
int main()
{
	ios::sync_with_stdio(false);
	int testcase;
	cin >> testcase;
	for (int i = 0; i < testcase; i++)
	{
		int n;
		cin >> n;
		cout << sol(n) << "\n";
	}
	return 0;
}