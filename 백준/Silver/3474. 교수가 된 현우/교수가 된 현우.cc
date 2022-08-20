#include<iostream>
using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);

	int times;
	int num;
    int count;
	cin >> times;
	while (times--)
	{
		cin >> num;
        count = 0;
        for (int i = 5; i <= num; i*=5)
        {
            count += num / i;
        }
        cout<<count<<"\n";
	}
	return 0;
}
