#include<iostream>
#include<vector>
#include<string>
using namespace std;

vector<int> makeTable(string pattern) {
	int pattenSize = pattern.length();
	vector<int> table(pattenSize, 0);
	int j = 0;
	for (int i = 1; i < pattenSize; ++i) {
		while (j > 0 && pattern[i] != pattern[j]) {
			j = table[j - 1];
		}
		if (pattern[i] == pattern[j]) {
			++j;
			table[i] = j;
		}
	}
	return table;
}

vector<int> kmp(string parent, string pattern) {
	vector<int> result;
	vector<int> table = makeTable(pattern);
	int parentSize = parent.length();
	int pattenSize = pattern.length();
	int j = 0;
	int cnt = 0;
	for (int i = 0; i < parentSize; ++i) {
		while (j > 0 && parent[i] != pattern[j]) {
			j = table[j - 1];
		}
		if (parent[i] == pattern[j]) {
			if (j == pattenSize - 1)
			{
				result.push_back(i - pattenSize + 2);
				j = table[j];
				cnt++;
			}
			else {
				++j;
			}
		}
	}
	cout << cnt << "\n";
	return result;
}

int main() {
	ios::sync_with_stdio(false);

	string parent;
	string pattern;
	getline(cin, parent);
	getline(cin, pattern);
	vector<int> result = kmp(parent, pattern);
	for (const auto& it : result) {
		cout << it << " ";
	}

	return 0;
}