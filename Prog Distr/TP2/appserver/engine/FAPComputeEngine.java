package engine;

public class FAPComputeEngine extends ComputeEngineNotifier {

	public FAPComputeEngine() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public void run() {
		while(true) {
			try {
				Thread.sleep(20);
				
				if (!tds.isEmpty()) {
					TaskDescriptor td = tds.poll();
					td.setResult(td.getTask().execute());
					tn.addTaskObserver(td);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
