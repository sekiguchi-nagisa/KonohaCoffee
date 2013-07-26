void f(int a) {
    int i = 0;
    while(i < a) {
        i = i + 1;
        System.p(i);
        if(i == 5) {
            continue;
        }
        System.p(i);
    }
}

f(10);
