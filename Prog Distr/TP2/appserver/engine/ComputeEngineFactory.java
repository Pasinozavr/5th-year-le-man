package engine;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

import compute.ICompute;
import compute.ITask;

/**
 * Le serveur qui gère les demandes d'exécution de tâche
 * @author lemeunie
 *
 */
public class ComputeEngineFactory implements ICompute {
	
	private static DComputeEngine dce;
	private static TComputeEngine tce;
	private static FAPComputeEngine fapce;
	
    public ComputeEngineFactory() {}

    /**
     * Exécution d'une tâche immédiate
     */
    public <T> T executeTask(ITask<T> t) {
    	if (dce == null)
    		dce = new DComputeEngine();
    	
    	return dce.execute(new TaskDescriptor<T>(t));
    }
    
    /**
     * Exécution d'une tâche à priorité
     */
	public <T> T executeTask(ITask<T> t, int salience) throws RemoteException {
		if (fapce == null) {
			fapce = new FAPComputeEngine();
			new Thread(fapce).start();
		}
				
		return fapce.execute(new TaskDescriptor<T>(t, salience));
	}

	/**
	 * Exécution d'une tâche datée à priorité 
	 */
	public <T> T executeTask(ITask<T> t, int salience, Calendar time) throws RemoteException {
		if (tce == null) {
			tce = new TComputeEngine();
			new Thread(tce).start();
		}		
		
		return tce.execute(new TaskDescriptor<T>(t, salience, time));
	}

	/**
	 * Methode de demarrage du serveur
	 * @param args
	 */
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            ICompute engine = new ComputeEngineFactory();
            ICompute stub = (ICompute) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }

}
