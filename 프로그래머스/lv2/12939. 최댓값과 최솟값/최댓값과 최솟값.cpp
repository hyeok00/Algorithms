#include <string>
#include <vector>
#include <sstream>
#include <algorithm>
using namespace std;

string solution(string s) {
    string answer = "";
    stringstream sstr(s);
    vector<int> data;
    
    string str;
    int temp;
    while(getline(sstr,str,' '))
    {
        data.push_back(stoi(str));
    }
    sort(data.begin(), data.end());
    answer = to_string(data[0]) +" "+ to_string(data[data.size()-1]);
    
    return answer;
}