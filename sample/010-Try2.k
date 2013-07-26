class Exception1 extends Exception {
}

class Exception2 extends Exception {
}

void g() {
    System.p("top-level start");
    try {
        System.p("try-begin");
        throw new Exception2();
        System.p("try-end");
    } catch(Exception1 e) {
        System.p("catched");
    } finally {
        System.p("final");
    }
    System.p("top-level end");
}
void f() {
    System.p("top-level start");
    try {
        System.p("try-begin");
        g();
        System.p("try-end");
    } catch(Exception2 e) {
        System.p("catched");
    } finally {
        System.p("final");
    }
    System.p("top-level end");
}

f();
