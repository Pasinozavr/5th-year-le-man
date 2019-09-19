package engine;

import java.util.Calendar;

public class TComputeEngine extends ComputeEngineNotifier {

	public TComputeEngine() {
		super();
	}

	@SuppressWarnings("unchecked")
	public void run() {
		while(true) {
			try {
				Thread.sleep(20);
				
				if (!tds.isEmpty()) {
					TaskDescriptor td = tds.peek();
					int diff = td.getTime().get(Calendar.MINUTE) - Calendar.getInstance().get(Calendar.MINUTE);
					if (Math.abs(diff) <= 1) {
						td = tds.poll();
						td.setResult(td.getTask().execute());
						tn.addTaskObserver(td);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
