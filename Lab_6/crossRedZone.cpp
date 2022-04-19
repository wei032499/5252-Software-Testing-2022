#include <iostream>

int main()
{
    char a[8] = {0};
    char b[8] = {0};

    a[64] = 0;           // write out of bound
    char access = a[64]; // read out of bound

    return 0;
}