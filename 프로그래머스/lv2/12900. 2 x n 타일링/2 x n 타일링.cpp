#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int arr[60001]={1,1};
    for(int i=2; i<=n; i++)
    {
        arr[i] = (arr[i-2]%1000000007 + arr[i-1] %1000000007) %1000000007;   
    }
	return arr[n];
}