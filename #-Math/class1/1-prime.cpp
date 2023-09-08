#include<iostream>

using namespace std;

// 尝除法：判断是否是质数
bool is_prime(int n) {
    if (n <= 1) return false;
    for (int i = 2; i <= n / i; i++) {
        if (n % i == 0) return false;
    }
    return true;
}


int main() {
    return 0;
}