package engine;

import java.util.List;
import java.util.LinkedList;

public class TaskNotifier implements Runnable {

	protected List<TaskDescriptor> ts;
	
	public TaskNotifier() {
		ts = new LinkedList<TaskDescriptor>();
	}
	
	public synchronized void addTaskObserver(TaskDescriptor observer) {		
		ts.add(observer);
	}

	public void notifyTaskObservers() {				
		if (!ts.isEmpty())
			ts.remove(ts.size() - 1).taskEnded(null);
	}

	public synchronized void removeTaskObserver(TaskDescriptor observer) {		
		ts.remove(observer);		
	}

	public void run() {
		for (;;) {
			try {
				Thread.sleep(20);
				notifyTaskObservers();
			}
			catch(InterruptedException ie) {};
		}		
	}
}
