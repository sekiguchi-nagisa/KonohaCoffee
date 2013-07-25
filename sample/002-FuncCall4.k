int g(int a) {
    return a + 100;
}

int f(int a) {
    return g(a + 10);
}

System.p(f(1));
