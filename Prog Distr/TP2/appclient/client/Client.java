package client;

import compute.ICompute;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;

public class Client implements Runnable {
	
	public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
        	
        	new Thread(new Client()).start();
        	
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            ICompute comp = (ICompute) registry.lookup(name);
            
            // Tache immediate
            TaskObserverImpl to1 = new TaskObserverImpl();
            Pi task1 = new Pi(Integer.parseInt(args[1]), to1);
            to1.setTask(task1);            
            BigDecimal pi = comp.executeTask(task1);
            System.out.println("Resultat de la tache [" + task1.getNumber()  + "] : " + pi);
                                            
            // Taches à priorité datée : la 5 avant la 4
            TaskObserverImpl to4 = new TaskObserverImpl();
            TaskObserverImpl to5 = new TaskObserverImpl();
            Pi task4 = new Pi(Integer.parseInt(args[4]), to4);
            Pi task5 = new Pi(Integer.parseInt(args[5]), to5);
            to4.setTask(task4);
            to5.setTask(task5);
            
            Calendar cal = Calendar.getInstance();            
            cal.add(Calendar.MINUTE, 2); // Déclenchement dans 2 minutes
            
            pi = comp.executeTask(task4, 5, cal);
            pi = comp.executeTask(task5, 10, cal);
                                    
            // Taches à priorité : la 2 avant la 3
            TaskObserverImpl to2 = new TaskObserverImpl();
            Pi task2 = new Pi(Integer.parseInt(args[2]), to2);
            to2.setTask(task2);
            
            TaskObserverImpl to3 = new TaskObserverImpl();
            Pi task3 = new Pi(Integer.parseInt(args[3]), to3);
            to3.setTask(task3);
            
            pi = comp.executeTask(task2, 10);
            pi = comp.executeTask(task3, 5);    
            
            
        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }
    }

    
	public void run() {
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}   
}
