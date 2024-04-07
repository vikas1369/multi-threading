package main.java.multithreading;

import java.math.BigInteger;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadingTimeConsumptionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Thread> threads = new ArrayList<>();
		threads.add(new FactorialThread(1,2000));
		threads.add(new FactorialThread(2001,4000));
		threads.add(new FactorialThread(4001,6000));
		System.out.println("Printing start time");
		System.out.println(LocalTime.now());
		for(Thread t:threads) {
			t.start();
		}
		for(Thread t:threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		System.out.println(LocalTime.now());
		System.out.println("Printing end time");
	}
	static class FactorialThread extends Thread{
		int start,end;
		FactorialThread(int start, int end){
			this.start = start;
			this.end = end;
		}
		public void run() {
			for(int i = start;i<=end;i++) {
				//System.out.println("Factorial of "+i+" is "+factorial(i));
				factorial(i);
			}
		}
	}
	static BigInteger factorial(int number) {
		BigInteger total = BigInteger.valueOf(1);
		for(int i = 1;i<=number;i++) {
			total =total.multiply(BigInteger.valueOf(i));
		}
		return total;
	}
}
