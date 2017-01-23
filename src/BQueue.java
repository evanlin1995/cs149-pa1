import java.util.*;

public class BQueue<T> {
	
	private Queue<T> queue;
	
	public BQueue() {
		queue = new LinkedList<T>();
	}
	
	public void add(T entry) {
		queue.add(entry);
	}
	
	public T poll() {
		return queue.poll();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public int size() {
		return queue.size();
	}
	
}