#include<iostream>
#include<algorithm>
#include<string>
using namespace std;

bool comp(const string* a, const string* b)
{
	if (a->length() != b->length())
		return a->length() < b->length();
	else
		return *a < *b;
}

string* arr[20000];

int main()
{
	ios::sync_with_stdio(false);
	int num;
	cin >> num;
	for (int i = 0; i < num; i++)
	{
		arr[i] = new string();
		cin >> *arr[i];
	}

	sort(arr, arr + num, comp);

	for (int i = 0; i < num; i++)
	{
		if (i > 0)
			if (*arr[i] == *arr[i - 1])
				continue;

		cout << *arr[i] << "\n";
	}
	return 0;
}