#include <string>
#include <vector>

using namespace std;

string solution(string new_id) {
    string answer = "";
    
    string my_step1 = new_id;
    int len = my_step1.length();
    for(int i=0; i<len; ++i)
    {
        if('A'<=my_step1[i] && my_step1[i]<='Z')     // step 1
            my_step1[i]=my_step1[i]-'A'+'a';
    }
    
    string my="";
    for(int i=0; i<len; ++i)
    {
        if (!(('a' <=my_step1[i] && my_step1[i]<='z') 
            || ('0' <=my_step1[i] && my_step1[i]<='9')
            || my_step1[i]=='-' 
            || my_step1[i]=='_'
            || my_step1[i]=='.')
           ) // step 2
        {
            //none
        }
        else
        {
            my +=my_step1[i];
        }
    }
    
    len = my.length();
    for(int i=0; i<len; ++i)
    {
        if(i>0 && my[i] == '.' && my[i-1] == '.')     // step 3
        {
            // none
        }
        else if ((i==0 && my[i] == '.') ||(i==len-1 && my[i] == '.')) // step 4
        {
            // none
        }
        else
        {
            answer=answer+my[i];
        }
    }
    
    //step 5
    if(true == answer.empty())
        answer = 'a';
    
    //step 6
    int new_len = answer.length();
    if(new_len > 15)
        answer = answer.substr(0,15);
    int end =answer.length();
    if(answer[end-1]=='.')
        answer.erase(end-1,end-1);
    
    //step 7
    end = answer.length();
    if(end<3)
    {
        char c = answer[end-1];
        while(answer.length()<3)
        {
            answer+=c;
        }
    }
    
    return answer;
}