package main.java.multithreading.completablefuture;
/*
Author: Vikas Yadav
Date: 13/03/22
Place: Glasgow, Scotland
*/

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/*
About the program: This program demonstrates how to
create a completable future and collect its result
Here we use default fork join pool.
If we don't supply any executor thread with supply async method then
it is executed on default fork join pool worker thread
 */
public class CompletableFutureExample {
    public static void main(String args[]){
        List<Integer> ints = new ArrayList<>();
        ints.add(10);
        ints.add(20);
        List<CompletableFuture<Integer>> futures= ints.stream().map(number->findFactorial(number)).collect(Collectors.toList());
        //List<Integer> results= ints.stream().map(number->findFactorial(number)).map(CompletableFuture::join).collect(Collectors.toList());  *Two threaded and blocking*
        //Stream is a lazy operation and it is not evaluated unless we call some terminal operation on it
        //Hence in the above step what it does is that it spawns the task for all of the completable future task as collect was called
        //But unless we call get or join, we don't get the final result
        //In the above step it will take the task and submit it in fork join pool, take the next task and submit
        //it in fork join pool, hence tasks are getting executed parallelly
        //If call join in above step only then the program become blocking in the sense that there only
        //two thread being used one main thread and other one thread from the Fork Join pool
        //What it will do in that case is it will call findFactorial and then on the join call it will wait for
        //the result and then call the next findFactorial and then again wait for the result
        List<Integer> result = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        //And at the above step we collect the result
        //Here the CompletableFuture is of the type the result we are expecting, Since we expect the factorial
        //to be integer hence we are returning Integer here
        //Both get and join can be used to get the result of the future but get throws checked exception hence
        //it can be used in the stream chain and join throws unchecked exceptions hence join is preferable
        System.out.println(result);
    }

    public static CompletableFuture<Integer> findFactorial(int n){
        return supplyAsync(()-> {
            System.out.println("Started evaluation for "+n);
            System.out.println(Thread.currentThread());
            int factorial = 1;
            for (int i = 1; i < n; i++) {
                factorial = factorial * i;
            }
            return factorial;
        });
    }
}





