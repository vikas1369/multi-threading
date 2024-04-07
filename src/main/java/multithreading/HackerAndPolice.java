package main.java.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HackerAndPolice {
	public static int MAX_PASSWORD = 9999;
	public static void main(String args[]) {
		List<Thread> list= new ArrayList<>();
		Random r = new Random();
		Vault v = new Vault(r.nextInt(MAX_PASSWORD));
		list.add(new DescendingHackerThread("Hacker-Thread-1",v));
		list.add(new AscendingHackerThread("Hacker-Thread-2",v));
		list.add(new PoliceThread("Police Thread"));
		for(Thread thread:list) {
			thread.start();
		}
	}
	static class DescendingHackerThread extends Thread{
		Vault v;
		DescendingHackerThread(String name,Vault v) {
			this.setName(name);
			this.v = v;
			this.setPriority(MAX_PRIORITY);
		}
		@Override
		public void run() {
			for(int i = MAX_PASSWORD;i>=0;i--) {
				if(v.isHacked(i)) {
					System.out.println("Correct Password is "+i+" broke by"+this.getName());
					System.exit(0);
				}
			}
		}
	}
	static class AscendingHackerThread extends Thread{
		Vault v;
		public AscendingHackerThread(String name,Vault v) {
			this.setName(name);
			this.v = v;
			this.setPriority(MAX_PRIORITY);
		}
		@Override
		public void run() {
			for(int i = 0;i<=MAX_PRIORITY;i++) {
				if(v.isHacked(i)) {
					System.out.println("Correct Password is "+i+" broke by"+this.getName());
					System.exit(0);
				}
			}
		}
		
	}
	static class PoliceThread extends Thread{
		PoliceThread(String name){
			this.setName(name);
		}
		@Override
		public void run() {
			try {
				for(int i =0;i<10;i++) {
					System.out.println(this.getName()+" Countdown "+(10-i));
					this.sleep(1000);
				}
			}catch(InterruptedException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Game Over!!!!");
			System.exit(0);
		}
	}
	static class Vault{
		int password;
		Vault(int password){
			this.password = password;
		}
		boolean isHacked(int guess) {
			try {
				Thread.currentThread().sleep(5);
			}catch(InterruptedException e) {
				System.out.println(e.getMessage());
			}
			return guess==password;
		}
	}
}
