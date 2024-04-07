package main.java.multithreading;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.*;
public class CyclicBarrierDemo {
	   public static void main(String args[]) throws InterruptedException {
	      ExecutorService executors = Executors.newFixedThreadPool(4);
	      CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
	      executors.submit(new Service1(cyclicBarrier));
	      executors.submit(new Service1(cyclicBarrier));
	      executors.submit(new Service2(cyclicBarrier));
	      executors.submit(new Service2(cyclicBarrier));
	      executors.submit(new Service2(cyclicBarrier));
	      Thread.sleep(4000);
	      System.out.println("Done");
	   }
	}

	class Service1 implements Runnable {
	   CyclicBarrier cyclicBarrier;
	   public Service1(CyclicBarrier cyclicBarrier) {
	      super();
	      this.cyclicBarrier = cyclicBarrier;
	   }
	   @Override
	   public void run() {
	      System.out.println("Services1 " + cyclicBarrier.getNumberWaiting());
	      while (true) {
	         try {
	        	 System.out.println("Executing Further in Service 1");
	            cyclicBarrier.await();
	         } catch (InterruptedException | BrokenBarrierException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
	   }
	}
	class Service2 implements Runnable {
	   CyclicBarrier cyclicBarrier;
	   public Service2(CyclicBarrier cyclicBarrier) {
	      super();
	      this.cyclicBarrier = cyclicBarrier;
	   }
	   @Override
	   public void run() {
	      System.out.println("Services2 " + cyclicBarrier.getNumberWaiting());
	      while (true) {
	         try {
	        	 System.out.println("Executing Further in Service 2");
	            cyclicBarrier.await();
	         } catch (InterruptedException | BrokenBarrierException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
	   }
	}
