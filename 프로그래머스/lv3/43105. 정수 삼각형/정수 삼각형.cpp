#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> triangle) {
    int answer = 0;
    
    int size = triangle.size();
    for(int i = size-1; i>0; --i)
    {
        int inner = triangle[i].size();
        for(int j=0; j< inner-1; ++j)
        {
            // 덧셈은 큰 값을 더할수록 더 큰 값이다.
            if(triangle[i][j] > triangle[i][j+1])
            {
                triangle[i-1][j] +=triangle[i][j];
            }
            else
            {
                triangle[i-1][j] +=triangle[i][j+1];
            }
        }
    }
    answer = triangle[0][0];
    return answer;
}