package engine;

import java.rmi.RemoteException;
import java.util.Calendar;

import compute.ITask;
import compute.ITaskObserver;

public class TaskDescriptor<T> implements ITaskObserver {

	private ITask<T> task;
	private int salience;
	private Calendar time;
	private T result;
	
	public TaskDescriptor(ITask<T> task) {
		this.task = task;
		this.salience = 0;
		this.time = null;
	}
	
	public TaskDescriptor(ITask<T> task, int salience) {
		this.task = task;
		this.salience = salience;
		this.time = null;
	}
	
	public TaskDescriptor(ITask<T> task, int salience, Calendar time) {
		this.task = task;
		this.salience = salience;
		this.time = time;
	}

	public int getSalience() {
		return salience;
	}

	public ITask<T> getTask() {
		return task;
	}

	public Calendar getTime() {
		return time;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public void taskEnded(Object result) {
		try {
			task.getTaskObserver().taskEnded(this.result);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
