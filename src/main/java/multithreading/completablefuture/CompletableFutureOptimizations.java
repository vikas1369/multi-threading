package main.java.multithreading.completablefuture;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CompletableFutureOptimizations {

    public static void main(String args[]) {
        Executor executor = Executors.newFixedThreadPool(5);
        TransferService transferService = new TransferService();
        List<Integer> list = IntStream.range(1, 501).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        long start = System.currentTimeMillis();
        List<CompletableFuture<Integer>> futures = list.stream().map(i -> supplyAsync(() -> {
            try {
                return transferService.getRate(i);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
                return null;
            }
        }, executor)).collect(Collectors.toList());

        futures.parallelStream().forEach(CompletableFuture::join);

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
