package engine;

public class DComputeEngine implements ComputeEngine {

	public <T> T execute(TaskDescriptor<T> td) {
		return td.getTask().execute();
	}

}
