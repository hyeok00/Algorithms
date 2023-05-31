#include <string>
#include <vector>
#include<map>
using namespace std;

vector<int> solution(vector<string> name, vector<int> yearning, vector<vector<string>> photo) {
    map<string,int> scoreMap;
    for(int i=0; i<name.size(); ++i){
        scoreMap.insert({name[i],yearning[i]});
    }
    vector<int> answer;
    for(auto &it : photo){
        int sum = 0;
        for(int i=0; i<it.size(); ++i){
            sum+=scoreMap[it[i]];
        }
        answer.push_back(sum);
    }
    return answer;
}