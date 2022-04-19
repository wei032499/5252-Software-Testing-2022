#include <iostream>

int main()
{
    char *array = new char[5];

    array[10] = 0;           // write out of bound
    char access = array[10]; // read out of bound

    delete[] array;

    return 0;
}