#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> answers) {
	vector<int> answer;

	int player1[] = { 1,2,3,4,5 };
	int player2[] = { 2,1,2,3,2,4,2,5 };
	int player3[] = { 3,3,1,1,2,2,4,4,5,5 };
	int score[3] = { 0,0,0 };
	for (int i = 0; i < answers.size(); i++)
	{
		if (player1[i % 5] == answers[i])
			score[0]++;
		if (player2[i % 8] == answers[i])
			score[1]++;
		if (player3[i % 10] == answers[i])
			score[2]++;
	}
	int max = 0;
	for (int i = 0; i < 3; i++)
	{
		if (score[i] >= max)
			max = score[i];
	}
	for (int i = 0; i < 3; i++)
	{
		if (score[i] == max)
			answer.push_back(i+1);
	}
	return answer;
}