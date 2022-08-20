#include<iostream>
#include<algorithm>
using namespace std;

class Student {
public:
	string name;
	int age;
	int count;
};

Student *arr[100000];

bool comp(const Student* a, const Student* b)
{
	if (a->age == b->age) 
	{
		return a->count > b->count;
	}
	return a->age < b->age;
}
int main()
{
	ios::sync_with_stdio(false);
	int num;
	cin >> num;
	for (int i = 0; i < num; i++)
	{
		arr[i] = new Student();
		cin >> arr[i]->age;
		cin >> arr[i]->name;
		arr[i]->count = 100000-i;
	}

	sort(arr, arr + num, comp);

	for (int i = 0; i < num; i++)
	{
		cout << arr[i]->age <<" "<<arr[i]->name << "\n";
	}
	return 0;
}