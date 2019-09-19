package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import compute.ITask;
import compute.ITaskObserver;

public class TaskObserverImpl implements ITaskObserver {
	
	private ITask task; 
	
	public TaskObserverImpl() throws RemoteException {
		UnicastRemoteObject.exportObject(this, 0);
	}
	
	public void taskEnded(Object result) throws RemoteException {
		System.out.println("Résultat de la tâche [" + task.getNumber() +  "] : " + result);
	}

	public void setTask(ITask task) {
		this.task = task;
	}
	
}
