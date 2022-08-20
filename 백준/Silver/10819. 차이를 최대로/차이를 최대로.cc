#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main()
{
	int n;
	scanf("%d", &n);
	vector<int> arr;
	vector<int> val;
	for (int i = 0; i < n; i++)
	{
		arr.push_back(i);
		int temp;
		scanf("%d", &temp);
		val.push_back(temp);
	}
	int max = 0;
	do {
		int temp = 0;
		for (int i = 0; i < arr.size() - 1; i++)
		{
			int a, b;
			a = val[arr[i]];
			b = val[arr[i + 1]];
			if(a-b<0)
				temp = temp + (a - b)*-1;
			else
				temp = temp + a - b;
		}
		if (temp > max)
			max = temp;
	} while (next_permutation(arr.begin(), arr.end()));

	printf("%d", max);

	return 0;
}