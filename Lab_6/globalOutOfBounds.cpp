#include <iostream>

char global_array[5] = {0};

int main()
{

    global_array[10] = 0;           // write out of bound
    char access = global_array[10]; // read out of bound

    return 0;
}