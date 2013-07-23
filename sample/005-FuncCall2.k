int f(int a) {
    return g(a + 10);
}

int g(int a) {
    return a + 100;
}

System.p(f(1));
