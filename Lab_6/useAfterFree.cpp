#include <iostream>

int main()
{
    char *array = new char[5];

    delete[] array;

    array[0] = 0;           // write after free
    char access = array[0]; // read after free

    return 0;
}