package compute;

public interface ITask<T> {
    public T execute();
    public ITaskObserver getTaskObserver();
    public int getNumber();
}
