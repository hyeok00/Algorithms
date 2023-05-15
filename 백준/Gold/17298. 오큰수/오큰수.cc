#include<algorithm>
#include<iostream>
#include<vector>
using namespace std;

int arr[1000001];
int stack[1000001];
int idx = -1;
int N;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;
	for (int i = 0; i < N; ++i)
		cin >> arr[i];

	for (int i = 0; i < N; ++i)
	{
		while (idx != -1 && arr[stack[idx]] < arr[i])
			arr[stack[idx--]] = arr[i];
		stack[++idx] = i;
	}
	while (idx != -1)
		arr[stack[idx--]] = -1;

	for (int i = 0; i < N; ++i)
		cout << arr[i] << " ";
    return 0;
}