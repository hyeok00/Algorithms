#include<iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int x1, x2, x3, y1, y2, y3;
    cin >> x1 >> y1;
	cin >> x2 >> y2;
    cin >> x3 >> y3;
    cout << (x1 ^ x2 ^ x3) << " " << (y1 ^ y2 ^ y3);
    return 0;
}