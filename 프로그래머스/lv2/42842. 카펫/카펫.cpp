#include <string>
#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    int size = brown + yellow;
    for (int row = 1; row < size; row++)
    {
        int j = size % row;
        if (j == 0) // 나머지가 0인 경우만 직사각형으로 표현
        {
            int col = size / row; // 세로 개수 확인
            if (col <= row)  // row*col 에서 확인했기 때문에, col*row는 확인할 필요가 없음.
            {
                if ((row - 2) * (col - 2) == yellow)
                {
                    answer.push_back(row);
                    answer.push_back(col);
                }
            }
        }
    }
    
    
    return answer;
}