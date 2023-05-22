#include<iostream>
#include<string>
#include<queue>
using namespace std;

#define SIZE 20001
int T, N;
bool arr[SIZE];

void init()
{
	for (int i = 0; i <= N; ++i)
		arr[i] = false;
}

void func()
{
	queue<pair<int, string>> q;
	q.push({ 1,"1" });
	arr[1] = 1;
	while (!q.empty())
	{
		int mod = q.front().first;
		string result = q.front().second;
		q.pop();

		if (result.length() >= 100)
			break;

		if (mod % N == 0)
		{
			cout << result << endl;
			return;
		}

		int nextZero = (mod * 10) % N;
		string nextZeroStr = result + "0";

		if (!arr[nextZero])
		{
			arr[nextZero] = true;
			q.push({ nextZero, nextZeroStr });
		}

		int nextOne = (mod * 10 + 1) % N;
		string nextOneStr = result + "1";

		if (!arr[nextOne])
		{
			arr[nextOne] = true;
			q.push({ nextOne, nextOneStr });
		}

	}
		cout << "BRAK" << endl << endl;
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;

	while (T--)
	{
		cin >> N;
		init();
		func();
	}
	return 0;
}