#include <string>
#include <vector>
#include <algorithm>
#include <math.h>
#include <set>
using namespace std;

bool isPrime(const int& x) {

	if (x == 1 || x == 0)
		return false;

	int count = 0;

	for (int i = 2; i <= sqrt(x); i++) {

		if (x % i == 0)
		{
			count++;
		}
	}

	if (count == 0)
		return true;
	else
		return false;
}

int solution(string numbers)
{
    
	int answer = 0;
	vector<int> vec;
	for (size_t it = 0; it<numbers.length(); ++it)
	{
		vec.push_back((numbers[it])-'0');
	}
	sort(vec.begin(), vec.end());
	numbers = "";
	for (const auto& it : vec)
	{
		numbers += to_string(it);
	}
	set<int> myset;
	do
	{
		for (size_t it = 1; it <= numbers.length(); ++it)
		{
			string temp = numbers;
			myset.insert(stoi(temp.substr(0, 0 + it)));
		}
	} while (std::next_permutation(numbers.begin(), numbers.end()));

	for (const auto& it : myset)
	{
		if (true == isPrime(it))
			answer++;
	}
	return answer;
}