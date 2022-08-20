#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool solution(vector<string> phone_book)
{
	bool answer = true;

	sort(phone_book.begin(), phone_book.end());
	for (size_t phone_book_iter = 0; phone_book_iter < phone_book.size() - 1; ++phone_book_iter)
	{
		size_t len = phone_book[phone_book_iter].length();
		if (phone_book[phone_book_iter] == phone_book[phone_book_iter+1].substr(0, len))
		{
			return false;
		}
	}
	return answer;
}