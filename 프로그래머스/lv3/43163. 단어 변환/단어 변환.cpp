#include <string>
#include <vector>
#include <map>
#include <queue>
using namespace std;

map<string, int> checkArr;
bool checkWord(const string& in1, const string& in2)
{
    int length = in1.length();
    int count = 0;
    for (int i = 0; i < length; ++i)
    {
        if (in1[i] != in2[i])
        {
            ++count;
        }
    }

    if (1 == count) // 한 글자만 다른 경우, 변환 가능함.
    {
        return true;
    }
    else // 변환 할 수 없는 경우.
    {
        return false;
    }
}

int search(string begin, string target, vector<string> words)
{
    queue<string> myqueue;
    myqueue.push(begin);

    int nextDepthCount = 0;
    int depthCount = 1;
    int curCount = 0;
    int depth = 0;
    while (!myqueue.empty())
    {

        if (curCount >= depthCount)
        {
            depth++;
            curCount = 0;
            depthCount = nextDepthCount;
            nextDepthCount = 0;
        }

        curCount++;

        const string str = myqueue.front();
        checkArr[str] = 1;
        myqueue.pop();

        if (str == target)
        {
            break;
        }

        for (auto it : words)
        {
            if ((true == checkWord(str, it)) && (0 == checkArr[it])) // 변환 가능한 경우
            {
                myqueue.push(it);
                nextDepthCount++;
            }
        }
    }
    return depth;
}

int solution(string begin, string target, vector<string> words) {
    int answer = 0;

    bool noCase = true;
    for (auto i : words) // 찾고자 하는 단어로 변환이 불가능한 경우 체크
    {
        if (i == target)
        {
            noCase = false;
        }
        checkArr.insert({ i,0 });
    }
    if (noCase)
    {
        return 0;
    }
    else
    {
        return search(begin, target, words);
    }
    return answer;
}