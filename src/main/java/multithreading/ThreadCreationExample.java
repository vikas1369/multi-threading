package main.java.multithreading;

public class ThreadCreationExample {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("We are now in thread "+Thread.currentThread().getName());
				System.out.println("Curent Thread Priority "+Thread.currentThread().getPriority());
				throw new RuntimeException("Run Time Excetpion");
			}
			
		});
		t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				// TODO Auto-generated method stub
				System.out.println("Critical error occured in "+t.getName()+"Error occured is "+e.getMessage());
			}
		});
		t.setName("New Worker Thread");
		t.setPriority(Thread.MAX_PRIORITY);
		System.out.println("We are in thread "+Thread.currentThread().getName()+ " before starting a new thread");
		t.start();
		Thread.sleep(1000);
		ThreadDemo th = new ThreadDemo();
		th.start();
	}
}

class ThreadDemo extends Thread{
	@Override
	public void run() {
		System.out.println("Running the thread");
	}
}
