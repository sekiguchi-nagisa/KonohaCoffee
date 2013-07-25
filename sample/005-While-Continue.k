void f(int a) {
    int i = 0;
    while(i < a) {
        i = i + 1;
        if(i == 5) {
            continue;
        }
    }
}

f(10);
