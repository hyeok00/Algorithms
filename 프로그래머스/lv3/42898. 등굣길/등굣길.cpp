#include <string>
#include <vector>

using namespace std;

int myMap[101][101] = { 0, };

void fillPuddles(const int m, const int n, const vector<vector<int>>& puddles)
{
    for (auto it : puddles)
    {
        if(!it.empty())
           myMap[it[0]-1][it[1]-1] = -1; // 지나갈 수 없다면 -1로 기록해 두자.
    }
}
void fillBoundary(const int& m, const int& n)
{
    myMap[0][0] = 1;
    for (int i = 1; i < n; ++i)
    {
        if (myMap[0][i] == -1)
        {
            continue;
        }
        if (myMap[0][i-1] == -1)
        {
            myMap[0][i] = 0;
        }
        else
        {
            myMap[0][i] = myMap[0][i-1];
        }
    }
    for (int i = 1; i < m; ++i)
    {
        if (myMap[i][0] == -1)
        {
            continue;
        }
        if (myMap[i-1][0] == -1)
        {
            myMap[i][0] = 0;
        }
        else
        {
            myMap[i][0] = myMap[i-1][0];
        }
    }
}


int solution(int m, int n, vector<vector<int>> puddles) {
    int answer = 0;

    fillPuddles(m, n, puddles);
    fillBoundary(m, n);
    for (int i = 1; i < n; ++i)
    {
        for (int j = 1; j < m; ++j) // 행을 우선적으로 채운다.
        {
            if (-1 == myMap[j][i]) // 현재위치가 웅덩이라면 생략
            {
                continue;
            }
            if ((-1 == myMap[j][i - 1]) && (-1 == myMap[j - 1][i]))
            {
                myMap[j][i] = -1; // 왼쪽, 위 둘 다 웅덩이인 경우
            }
            else
            {
                if (-1 == myMap[j - 1][i]) // 왼쪽이 웅덩이일 경우
                {
                    myMap[j][i] = myMap[j][i - 1] % 1000000007;
                }
                else if (-1 == myMap[j][i - 1]) // 위쪽이 웅덩이일 경우
                {
                    myMap[j][i] = myMap[j - 1][i] % 1000000007;
                }
                else
                {
                    myMap[j][i] = (myMap[j - 1][i]% 1000000007 +
                                   myMap[j][i - 1]% 1000000007)
                        % 1000000007;
                }
            }
        }
    }
    if(myMap[m-1][n] == -1 && myMap[m][n-1] == -1)
        answer = 0;
    else
        answer = myMap[m - 1][n - 1];
    return answer;
}