class A {
    int a;
    int f(int b) {
        return b + 10;
    }
}

int f() {
    A a = new A();
    System.p(a.f(10));
}

f();
