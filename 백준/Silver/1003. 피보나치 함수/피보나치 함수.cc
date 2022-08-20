#include<stdio.h>
#include<vector>
#include<algorithm>
using namespace std;

int count1[41] = {1,0,};
int count2[41] = {0,1,};
int fibo[41];

int fibonacci(int n)
{
	if (n == 0) {
		return 0;
	}
	else if (n == 1) {
		return 1;
	}
	else {
		if (!fibo[n])
		{
			fibo[n] = fibonacci(n - 1) + fibonacci(n - 2);
			count1[n] = count1[n - 1] + count1[n - 2];
			count2[n] = count2[n - 1] + count2[n - 2];
		}
		return fibo[n];
	}
}

int main()
{
	int t,n;
	scanf("%d", &t);
	for (int i = 0; i < t; i++)
	{
		scanf("%d", &n);
		fibonacci(n);
		printf("%d %d\n", count1[n], count2[n]);
	}
	return 0;
}