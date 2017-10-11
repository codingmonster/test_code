package testtttt;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class zzz {

	private static int MAXIMUN_NUM_OF_THREAD = 100;
    private static ExecutorService executorService = Executors.newFixedThreadPool(MAXIMUN_NUM_OF_THREAD);
    
	public static void main(String[] args) {
		Future<?>[] futureTasks = new Future<?>[1];
        for (int i = 0; i < 1; ++i) {
            futureTasks[i] = executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int a = 1/0;
				}
			});
        }
        
        // Waiting for the tasks to complete
        for (int i = 0; i < futureTasks.length; ++i) {            
            try {
            	System.out.println(futureTasks[i]);
                futureTasks[i].get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

	}

}
