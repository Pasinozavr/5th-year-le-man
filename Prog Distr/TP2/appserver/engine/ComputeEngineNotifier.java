package engine;

import java.util.concurrent.PriorityBlockingQueue;

public abstract class ComputeEngineNotifier implements ComputeEngine, Runnable {

	protected PriorityBlockingQueue<TaskDescriptor> tds;
	protected TaskNotifier tn;
	
	public ComputeEngineNotifier() {
		tds = new PriorityBlockingQueue<TaskDescriptor>(20, new TaskComparator<TaskDescriptor>());		
		tn = new TaskNotifier();
        new Thread(tn).start();
	}
	
	public synchronized <T> T execute(TaskDescriptor<T> td) {
		tds.add(td);
		return null;
	}	
}
