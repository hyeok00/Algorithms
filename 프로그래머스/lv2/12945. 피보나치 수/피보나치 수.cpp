#include <string>
#include <vector>

using namespace std;

int arr[100001]={0,1,};

long long solution(int n) {
  if(n==0)
  {
      return 0;
  }
  else if(n==1)
  {
      return 1;
  }
    else
    {
        if(arr[n] != 0 )
        {
            return arr[n];
        }
        else
        {
            return arr[n]=(solution(n-1)+solution(n-2)) % 1234567;
        }
    }
}