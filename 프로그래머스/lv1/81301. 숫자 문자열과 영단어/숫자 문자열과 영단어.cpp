#include <string>
#include <vector>
#include <map>

using namespace std;

int solution(string s) {
    int answer = 0;
    
    map<string,int> dic=
    {{"zero",0},
    {"one",1},
    {"two",2},
    {"three",3},
    {"four",4},
    {"five",5},
    {"six",6},
    {"seven",7},
    {"eight",8},
    {"nine",9}};

    int len = s.length();
    
    string temp="";
    for (int i=0; i<len; i++)
    {
        if('0'<=s[i] && s[i]<='9')
        {
            answer =  (answer *10) + (s[i]-'0');
        }
        else
        {
            temp+=s[i];
            auto it = dic.find(temp);
            if(it == dic.end())
            {
                continue;// 미완
            }
            else
            {
                answer = (answer *10) + it->second;
                temp="";
            }
        }
    }
    
    return answer;
}