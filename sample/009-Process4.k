String f() {
    Process p0 = new Process();
    p0.SetArgument("ls");
    p0.SetArgument("-la");
    p0.Start();
    p0.WaitResult();
    String out = p0.GetOut();
    return out;
}

System.p(f());
