void f(int a) {
    int i = 0;
    while(i < a) {
        System.p(i);
        if(i == 5) {
            break;
        }
        i = i + 1;
    }
    System.p(i);
}

f(10);
