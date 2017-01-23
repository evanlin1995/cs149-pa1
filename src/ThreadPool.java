import java.util.*;

public class ThreadPool {
	
	private Queue<Runnable> tasks;
	private PoolThread[] threads;
	private int numThreads;
	
	public ThreadPool() {
		this(1);
	}
	
	public ThreadPool(int numThreads) {
		tasks = new LinkedList<Runnable>();
		this.numThreads = numThreads;
		threads = new PoolThread[numThreads];
		
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new PoolThread();
			threads[i].start();
		}
	}
	
	public class PoolThread extends Thread {
		
		public PoolThread() {
			super();
		}
		
		public void run() {
			
			Runnable task;
			
			while (true) {			
				synchronized(tasks) {
					while (tasks.isEmpty()) {
						try {
							tasks.wait();
						} catch (InterruptedException e) {
							System.err.println(e);
						}
					}
					task = tasks.poll();
				}
				
				task.run();						
			}
		}
	}
	
	public void runTask(Runnable task) {
		synchronized(tasks) {
			tasks.add(task);
			tasks.notify();
		}
	}
	
}