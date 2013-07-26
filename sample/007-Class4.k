class A {
    int f(int b) {
        return b + 10;
    }
}

class B extends A {
    int f(int b) {
        return b + 20;
    }
}
int f() {
    A a = new A();
    A b = new B();
    System.p(a.f(10));
    System.p(b.f(10));
}

f();
