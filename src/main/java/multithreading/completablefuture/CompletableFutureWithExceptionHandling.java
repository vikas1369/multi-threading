package main.java.multithreading.completablefuture;
/*
Author: Vikas Yadav
Date: 13/03/22
Place: Glasgow, Scotland
*/

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/*
Note: Here in this case we want to catch the exception thrown the by method which does the computation
The problem is the error thrown by the supplyAsync is wrapped as a CompletionException
Hence in this case we can write the method of interest in try and catch and then use getCause()
to know the exact exception and throw it again
*/
public class CompletableFutureWithExceptionHandling {
    public static void main(String args[]) {
        try {
            methodSubmittingFutureTask();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void methodSubmittingFutureTask() throws Throwable {
        List<Integer> ints = new ArrayList<>();
        ints.add(10);
        ints.add(20);
        ints.add(30);
        try {
            List<CompletableFuture<Integer>> futures = ints.stream().map(number -> findFactorial(number)).collect(Collectors.toList());
            List<Integer> result = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        }catch (CompletionException e){
            //throw e.getCause() instanceof APIExecutionException ? new APIExecutionException("Error Occured"): e.getCause();
            throw e.getCause();
        }
    }

    public static CompletableFuture<Integer> findFactorial(int n){
        return supplyAsync(()-> {
            System.out.println("Started evaluation for "+n);
            System.out.println(Thread.currentThread());
            if(n==20){
                throw new RuntimeException("Throwing a runtimeexception");
            }
            int factorial = 1;
            for (int i = 1; i < n; i++) {
                factorial = factorial * i;
            }
            return factorial;
        });
    }
}
