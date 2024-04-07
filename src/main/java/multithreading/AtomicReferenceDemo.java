package main.java.multithreading;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String oldName = "old name";
		String newName = "new name";
		AtomicReference<String> atomicReference = new AtomicReference<>(oldName);
		atomicReference.set("something");
		if(atomicReference.compareAndSet(oldName, newName)) {
			System.out.println("New values is "+atomicReference.get());
		}else {
			System.out.println("No change");
		}
	}

}
