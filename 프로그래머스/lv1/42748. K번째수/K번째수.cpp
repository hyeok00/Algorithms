#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    size_t vec_size = commands.size();
    vector<int> answer;

    for (size_t i = 0; i < vec_size; ++i)
    {
        int start = commands[i][0]; // 시작
        int end = commands[i][1]; // 종료
        int n_num = commands[i][2]; // n번재 수

        vector<int> my_vec;
        for (int j = start - 1; j < end; ++j)
        {
            my_vec.push_back(array[j]);
        }

        sort(my_vec.begin(), my_vec.end());

        answer.push_back(my_vec[n_num - 1]);
    }

    return answer;
}