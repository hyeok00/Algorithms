#include <string>
#include <vector>

using namespace std;

int solution(vector<int> numbers) {
    int answer = 0;
    for (auto i : numbers)
    {
        answer+=i;
    }
    return 45-answer;
}