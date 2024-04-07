package main.java.multithreading;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProducingDeadLockScenario {

	public static void main(String[] args) {
		Intersection intersection = new Intersection();
		ReadWriteLock r = new ReentrantReadWriteLock();
		TrainA t1 = new TrainA(intersection);
		TrainB t2 = new TrainB(intersection);
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Trains have passed successfully");
		
	}
	public static class Intersection{
		Object roadA = new Object();
		Object roadB = new Object();
		public void takeRoadA() {
			synchronized(roadA) {
				System.out.println("Road A lock held by thread "+Thread.currentThread().getName());
				synchronized(roadB) {
					System.out.println("Train is passing through Road A");
				}
			}
		}
		public void takeRoadB() {	
			synchronized(roadB) {
				System.out.println("Road B lock held by thread "+Thread.currentThread().getName());
				synchronized(roadA) {
					System.out.println("Train is passing through Road B");
				}
			}
		}
	}
	public static class TrainA extends Thread{
		Intersection intersection;
		TrainA(Intersection intersection){
			this.intersection = intersection;
			this.currentThread().setName("Train A");
		}
		@Override
		public void run() {
			while(true) {
				intersection.takeRoadA();
			}
		}
	}
	public static class TrainB extends Thread{
		Intersection intersection;
		TrainB(Intersection intersection){
			this.intersection = intersection;
			this.currentThread().setName("Train B");
		}
		@Override
		public void run() {
			intersection.takeRoadB();
		}
	}
}
