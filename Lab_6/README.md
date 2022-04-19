# Software Testing - Lab 6
網路工程研究所 310552020 陳威融

## Compiler
gcc version 7.5.0 (Ubuntu 7.5.0-3ubuntu1~18.04)

## Problem 1

### Heap out-of-bounds

#### code
```C++=
#include <iostream>

int main()
{
    char *array = new char[5];

    array[10] = 0;           // write out of bound
    char access = array[10]; // read out of bound

    delete[] array;

    return 0;
}
```


#### ASan report
![](https://i.imgur.com/4GvKQNs.png)


#### valgrind report
![](https://i.imgur.com/87kxI73.png)


#### Result
ASan **能**檢測出異常
valgrind **能**檢測出異常




### Stack out-of-bounds

#### code
```C++=
#include <iostream>

int main()
{
    char array[5] = {0};

    array[10] = 0;           // write out of bound
    char access = array[10]; // read out of bound

    return 0;
}
```


#### ASan report
![](https://i.imgur.com/wZP0mJe.png)




#### valgrind report
![](https://i.imgur.com/GPT7fPP.png)



#### Result
ASan **能**檢測出異常
valgrind **不能**檢測出異常



### Global out-of-bounds

#### code
```C++=
#include <iostream>

char global_array[5] = {0};

int main()
{

    global_array[10] = 0;           // write out of bound
    char access = global_array[10]; // read out of bound

    return 0;
}
```


#### ASan report
![](https://i.imgur.com/EWbpdMU.png)


#### valgrind report
![](https://i.imgur.com/eybvbnG.png)


#### Result
ASan **能**檢測出異常
valgrind **不能**檢測出異常




### Use-after-free

#### code
```C++=
#include <iostream>

int main()
{
    char *array = new char[5];

    delete[] array;

    array[0] = 0;           // write after free
    char access = array[0]; // read after free

    return 0;
}
```


#### ASan report
![](https://i.imgur.com/fGNQLq4.png)


#### valgrind report
![](https://i.imgur.com/nwxx10F.png)


#### Result
ASan **能**檢測出異常
valgrind **能**檢測出異常



### Use-after-return

#### code
```C++=
// export ASAN_OPTIONS=detect_stack_use_after_return=1

char *x;

void foo()
{
    char stack_buffer[42];
    x = &stack_buffer[13];
}

int main()
{
    foo();
    *x = 42; // Boom!

    return 0;
}
```


#### ASan report
![](https://i.imgur.com/6J3vpxY.png)


#### valgrind report
![](https://i.imgur.com/31hDIa8.png)


#### Result
ASan **能**檢測出異常
valgrind **不能**檢測出異常



## Problem 2
寫一個簡單程式 with ASan，Stack buffer overflow 剛好越過redzone(並沒有對 redzone 做讀寫)，並說明 ASan 能否找的出來？

#### code
```C++=
#include <iostream>

int main()
{
    char a[8] = {0};
    char b[8] = {0};

    a[10] = 0;           // write out of bound
    char access = a[10]; // read out of bound

    return 0;
}
```

#### Result
![](https://i.imgur.com/vOX5G4k.png)


:::info
由上述實驗可知，`a`的記憶體位置為\[32, 40)、`b`的記憶體位置為\[96, 104)，因此若將`a`的起始位置+64即可跨越readzone，實驗結果如下：
:::

```C++=
#include <iostream>

int main()
{
    char a[8] = {0};
    char b[8] = {0};

    a[64] = 0;           // write out of bound
    char access = a[64]; // read out of bound

    return 0;
}
```

#### Result
![](https://i.imgur.com/LcHbe3d.png)


### Conclusion
ASan無法找出剛好跨越readzone的錯誤