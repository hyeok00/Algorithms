#include<string>
#include <iostream>
#include<stack>
using namespace std;

bool solution(string s)
{
    bool answer = true;
    int count=0;
    stack<char> temp;
    temp.push('#');
    for(int i=0; i<s.length(); i++)
    {
        if(s[i]=='(')
        {
            count++;
            temp.push(s[i]);
        }
        else if(temp.top()=='#' && s[i]==')')
            answer = false;
        if(s[i]==')' && temp.top()=='(')
        {
            temp.pop();
            count--;
        }
    }
    if(count!=0)
        answer = false;

    // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    cout << "Hello Cpp" << endl;

    return answer;
}