
NameSpace.ImportLibrary("KonohaProcess");

void f() {
    System.p("HI");
    Process p0 = new Process();
    p0.SetArgument("ls");
    p0.SetArgument("-la");
    p0.Start();
    p0.WaitResult();
    System.p(p0.GetOut());
}

f();
