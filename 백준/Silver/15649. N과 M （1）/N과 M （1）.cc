#include<iostream>
using namespace std;
int arr[100];
void func(int depth, int num, int k)
{
	if (depth == num)
	{
		for (int i = 0; i < num; i++)
		{
			cout << arr[i]<<" ";
		}
		cout << "\n";
		return;
	}
	for (int i = 1; i <= k; i++)
	{
		int count = 0;
		for (int j = 0; j < depth; j++)
		{
			if (arr[j] == i)
			{
				count = 1;
				break;
			}
		}
		if (count)
			continue;

		arr[depth] = i;
		func(depth + 1, num,k);
	}
}
int main()
{
	ios::sync_with_stdio(false);
	int N,M;
	cin >> N>>M;
	func(0, M,N);
	return 0;
}