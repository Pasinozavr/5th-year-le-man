package compute;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;

public interface ICompute extends Remote {
    <T> T executeTask(ITask<T> t) throws RemoteException;
    <T> T executeTask(ITask<T> t, int salience) throws RemoteException;
    <T> T executeTask(ITask<T> t, int salience, Calendar time) throws RemoteException;
}
