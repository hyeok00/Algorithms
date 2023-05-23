#include <string>
#include <vector>
#include <map>
using namespace std;

map<string, int> myMap;
map<string, int> countMap;
int solution(vector<string> want, vector<int> number, vector<string> discount) {
    int answer = 0;
    
    int size = want.size();
    for(int i=0; i<size; ++i)
    {
        myMap.insert({want[i],number[i]});
        countMap.insert({want[i],0});
    }
    
    for(int i=0; i<10; ++i)
        countMap[discount[i]]++;
    
    int dSize = discount.size();
    
    bool flag = true;
    for(int j=0; j<size; ++j)
    {
        string key = want[j];
        if((myMap[key] != countMap[key]))
        {
            flag=false;
            break;
        }
    }
    if(flag)
        answer++;
    
    for(int i=0; i<dSize-10; ++i)
    {   
        countMap[discount[i]]--;
        countMap[discount[i+10]]++;
        
        flag = true;
        for(int j=0; j<size; ++j)
        {
            string key = want[j];
            if((myMap[key] != countMap[key]))
            {
                flag=false;
                break;
            }
        }
        if(flag)
            answer++;
    }
    
    return answer;
}
