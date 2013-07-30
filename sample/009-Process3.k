String f() {
    ProcessMonitor monitor = new ProcessMonitor();
    Process p0 = new Process();
    p0.SetArgument("ls");
    monitor.SetProcess(p0);
    p0.SetArgument("-la");
    p0.Start();
    Process p1 = new Process();
    p1.SetArgument("grep");
    monitor.SetProcess(p1);
    p1.SetArgument(".txt");
    p1.Start();
    p0.Pipe(p1);
    p1.WaitResult();
    String out = p1.GetOut();
    monitor.ThrowException();
    return out;
}

System.p(f());
