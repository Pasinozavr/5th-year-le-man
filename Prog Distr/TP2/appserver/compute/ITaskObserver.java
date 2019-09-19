package compute;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITaskObserver extends Remote {
	public void taskEnded(Object result) throws RemoteException;
}
