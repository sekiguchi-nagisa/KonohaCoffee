void f(int a) {
    int i = 0;
    while(a < 10) {
        System.p(i);
        if(a == 5) {
            break;
        }
        i = i + 1;
    }
    System.p(i);
}

f(10);
