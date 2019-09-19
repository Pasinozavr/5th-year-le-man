package engine;

import java.util.Comparator;

import compute.ITask;

public class TaskComparator<T> implements Comparator<TaskDescriptor> {

	@SuppressWarnings("unchecked")
	public int compare(TaskDescriptor td1, TaskDescriptor td2) {
		
		ITask<T> t1 = td1.getTask();
		ITask<T> t2 = td2.getTask();
						
		// Si les deux mêmes tâches
		if (t1.equals(t2))
			return 0;
		
		// S'il s'agit de deux tâches normales
		if (td1.getTime() == null && td2.getTime() == null) {
			
			// Même priorité
			if (td1.getSalience() == td2.getSalience())				
				return 0;
			
			// t1 plus prioritaire
			if (td1.getSalience() > td2.getSalience()) {		
				return -1;
			}
			
			// t2 plus prioritaire
			return 1;
		}
		
		// S'il s'agit de deux tâches datées
		if (td1.getTime() != null && td2.getTime() != null) {			
						
			int tps = td1.getTime().compareTo(td2.getTime());
			
			// Même moment
			if (tps == 0) {
				// Même priorité
				if (td1.getSalience() == td2.getSalience())				
					return 0;
				
				// t1 plus prioritaire
				if (td1.getSalience() > td2.getSalience())
					return -1;
				
				// t2 plus prioritaire
				return 1;	
			}
							
			// Temps différent
			return tps;
		}			
			
		return 0; // Dans les autres cas toutes les tâches sont de même priorité		
	}

}
