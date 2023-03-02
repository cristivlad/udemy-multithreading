package org.example;

import javax.swing.plaf.TableHeaderUI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                //code that will run in a new thread
                System.out.println("We are now in thread: " + Thread.currentThread().getName());
                System.out.println("Current thread priority is: " + Thread.currentThread().getPriority());
            }
        });

        thread.setName("New Worker thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        /**
         * unchecked exceptions bring down the entire thread unless we catch the exceptions and handle them
         */
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName() + " error is: " + e.getMessage());
            }
        });

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");

        Thread.sleep(5000);
        // ---------------------------------------------------
        Thread newThread = new NewThread();
        newThread.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            // we can use this instead of Thread.currentThread()
            System.out.println("Hello from: " + this.getName());
        }
    }
}
