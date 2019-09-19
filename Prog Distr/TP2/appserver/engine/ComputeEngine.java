package engine;

public interface ComputeEngine {
	public <T> T execute(TaskDescriptor<T> td);
}
