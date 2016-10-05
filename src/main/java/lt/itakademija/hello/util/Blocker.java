package lt.itakademija.hello.util;

public class Blocker {

	private final Object lock = new Object();

	public void block() {
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void unblock() {
		synchronized (lock) {
			lock.notifyAll();
		}
	}

}
