#include<iostream>
using namespace std;

int arr[100001] = {0,3,7,};
int main()
{
	ios::sync_with_stdio(false);
	
	int n;
	cin >> n;
	if (n == 1)
		cout << arr[1];
	else if (n == 2)
		cout << arr[2];
	else
	{
		for (int i = 3; i <= n; i++)
		{
			arr[i] = (arr[i - 2] % 9901 + 2 * arr[i - 1] % 9901) % 9901;
		}
		cout << arr[n];
	}
	return 0;
}