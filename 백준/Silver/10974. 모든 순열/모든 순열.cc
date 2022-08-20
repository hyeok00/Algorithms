#include<iostream>
using namespace std;

int visit[8] = {};
int arr[8] = {};
int comb[8] = {};

void perm(int depth,int n)
{
	if (depth == n)
	{
		for (int i = 0; i < n; i++)
		{
			printf("%d ", comb[i]);
		}
		printf("\n");
	}
	else
	{
		for (int i = 0; i < n; i++)
		{
			if (!visit[i])
			{
				visit[i] = 1;
				comb[depth] = arr[i];
				perm(depth + 1, n);
				visit[i] = 0;
			}
		}
	}
}

int main()
{
	int n;
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
	{
		arr[i] = i + 1;
	}
	perm(0, n);
	return 0;
}