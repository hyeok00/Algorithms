#include<iostream>
#include<algorithm>
using namespace std;

class Student {
public:
	string name;
	int kor;
	int eng;
	int math;
};

Student *arr[100000];

bool comp(const Student* a, const Student* b)
{
	if (a->kor == b->kor) {
		if (a->eng == b->eng) {
			if (a->math == b->math) {
				return a->name < b->name;
			}
			return a->math > b->math;
		}
		return a->eng < b->eng;
	}
	return a->kor > b->kor;
}
int main()
{
	ios::sync_with_stdio(false);
	int num;
	cin >> num;
	for (int i = 0; i < num; i++)
	{
		arr[i] = new Student();
		cin >> arr[i]->name;
		cin >> arr[i]->kor;
		cin >> arr[i]->eng;
		cin >> arr[i]->math;
	}

	sort(arr, arr + num, comp);

	for (int i = 0; i < num; i++)
	{
		cout << arr[i]->name << "\n";
	}
	return 0;
}