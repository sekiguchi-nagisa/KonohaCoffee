void f() {
    Process p0 = new Process();
    p0.SetArgument("ls");
    p0.SetArgument("-la");
    p0.Start();
    System.p(p0.GetOut());
}

f();
