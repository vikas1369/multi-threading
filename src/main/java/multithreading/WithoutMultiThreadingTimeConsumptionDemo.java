package main.java.multithreading;

import java.math.BigInteger;
import java.time.LocalTime;

public class WithoutMultiThreadingTimeConsumptionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Before TIme");
		System.out.println(LocalTime.now());
		for(int i = 1;i<=6000;i++) {
			//System.out.println("Factorial of "+i+" "+factorial(i));
			factorial(i);
		}
		System.out.println(LocalTime.now());
		System.out.println("After time");
	}
	static BigInteger factorial(int number) {
		BigInteger total = BigInteger.valueOf(1);
		for(int i = 1;i<=number;i++) {
			total =total.multiply(BigInteger.valueOf(i));
		}
		return total;
	}

}
