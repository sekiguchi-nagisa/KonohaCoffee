void f() {
    System.p("top-level start");
    try {
        System.p("try-begin");
        throw new Exception();
        System.p("try-end");
    } catch(Exception e) {
        System.p("catched");
    } finally {
        System.p("final");
    }
    System.p("top-level end");
}

f();
