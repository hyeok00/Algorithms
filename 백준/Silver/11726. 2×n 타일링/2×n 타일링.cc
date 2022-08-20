#include<iostream>
using namespace std;

int arr[1001] = {1,1,};
int sol(int n)
{
	if (n == 0 || n == 1)
		return 1;
	if (arr[n])
		return arr[n];
	arr[n] = sol(n - 2)%10007 + sol(n - 1) % 10007;
	return arr[n]%10007;
}
int main()
{
	ios::sync_with_stdio(false);
	int n;
	cin >> n;

	cout << sol(n);

	return 0;
}