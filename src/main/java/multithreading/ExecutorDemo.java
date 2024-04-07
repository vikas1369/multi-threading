package main.java.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"Executing thread using executor");
			}
		});
		
		Callable<String> c = new Callable<String>() {
			@Override
			public String call() throws Exception{
				System.out.println("Callable Method");
				return "Call running";
			}
		};
		//Executor executor = Executors.newSingleThreadExecutor();
		//executor.execute(thread);
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		executor.execute(thread);
		Future<String> future = executor.submit(c);
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}


}
