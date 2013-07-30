void f() {
    Process p0 = Process.New();
    p0.SetArgument("ls");
    p0.SetArgument("-la");
    p0.Start();
    p0.WaitResult();
    System.p(p0.GetOut());
}

f();
