#include<iostream>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    int n;
    cin>>n;
    int val = n/4;
    for(int i=0; i<val; ++i){
        cout<<"long ";
    }
    cout<<"int";
    return 0;
}