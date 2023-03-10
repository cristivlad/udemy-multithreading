package org.example;

import java.math.BigInteger;

public class ThreadTerminationAndDemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new BlockingTask());

//        thread.start();
//        thread.interrupt();

        Thread newThread = new Thread(new LongComputationTask(new BigInteger("20000"), new BigInteger("100000000")));

        newThread.start();
        newThread.interrupt();
    }

    private static class BlockingTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }

    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }
        private BigInteger pow(BigInteger base, BigInteger power) {
            var result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}
