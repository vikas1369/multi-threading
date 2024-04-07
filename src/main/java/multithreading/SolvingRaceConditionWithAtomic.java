package main.java.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class SolvingRaceConditionWithAtomic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InventoryCounter ic = new InventoryCounter();
		IncrementingThread it = new IncrementingThread(ic);
		DecrementingThread dt = new DecrementingThread(ic);
		it.start();
		dt.start();
		try {
			it.join();
			dt.join();
		}catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Final values is "+ic.counter);

	}
	public static class IncrementingThread extends Thread{
		private InventoryCounter ic;
		IncrementingThread(InventoryCounter ic){
			this.ic = ic;
		}
		@Override
		public void run() {
			IntStream.range(1, 100000).forEach(v->ic.incrementCounter());
		}
	}
	public static class DecrementingThread extends Thread{
		private InventoryCounter ic;
		DecrementingThread(InventoryCounter ic){
			this.ic = ic;
		}
		@Override
		public void run() {
			IntStream.range(1, 100000).forEach(v->ic.decrementCounter());
		}
	}
	
	public static class InventoryCounter{
		private AtomicInteger counter = new AtomicInteger();
		public void incrementCounter() {
			//this is equivalent to
			//synchronized(this)
			counter.incrementAndGet();
		}
		public void decrementCounter() {
			//this is equivalent to
			//synchronized(this)
			counter.decrementAndGet();
		}
	}

}
