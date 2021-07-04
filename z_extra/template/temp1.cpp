#include<iostream>
using namespace std;

int main(){
    int t;
    cin >> t;
    while(t-- > 0){
        int n ;
        cin >> n;
        int arr[n];
        int sumofnumbers = 0;

        for(int i = 0; i < n; i++){
            cin >> arr[i];
            sumofnumbers += arr[i];
        } 

        if(sumofnumbers == n){
            cout << 0 ;
            cout << endl;
        }

        if(sumofnumbers > n){
            cout << sumofnumbers - n;
        }
        else{
            cout << 1;
        }
        cout << endl;
    }
    
    return -1;
}