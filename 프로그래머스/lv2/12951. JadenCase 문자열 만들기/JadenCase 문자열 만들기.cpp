#include <string>
#include <vector>

using namespace std;

string solution(string s) {
    bool b = true;
    for(int i=0; i<s.length(); i++)
    {
        if(s[i]==32)
        {
            b=true;
            continue;
        }
        if(b==true)
        {
            if(s[i]>='a' && s[i]<='z')
            {
                s[i]=s[i]+'A'-'a';
                b=false;
            }
            else
                b=false;
        }
        else
        {
            if(s[i]>='A' && s[i]<='Z')
                s[i]=s[i]+('a'-'A');
        }
    }
    string answer = s;
    return answer;
}