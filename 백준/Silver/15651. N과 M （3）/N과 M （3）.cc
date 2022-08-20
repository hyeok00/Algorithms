#include<iostream>
using namespace std;
int arr[100];
void func(int depth, int num, int k)
{
	if (depth == num)
	{
		for (int i = 0; i < num; i++)
		{
			printf("%d ", arr[i]);
		}
		printf("\n");
		return;
	}
	for (int i = 1; i <= k; i++)
	{
		arr[depth] = i;
		func(depth + 1, num,k);
	}
}
int main()
{
	ios::sync_with_stdio(false);
	int N,M;
	scanf("%d%d", &N, &M);
	func(0, M,N);
	return 0;
}