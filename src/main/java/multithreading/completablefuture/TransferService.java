package main.java.multithreading.completablefuture;

public class TransferService{
    public TransferService(){

    }
    public Integer getRate(int i) throws InterruptedException {
        System.out.println("Current i "+i);
        Thread.sleep(i);
        return i;
    }
}
