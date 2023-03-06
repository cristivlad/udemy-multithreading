package org.example;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    public static void main(String[] args) {
        // a java fx thread should be started and the updater runs in a different thread
        // in the fx thread we should use pricesContainer.getLockObject().tryLock() instead of lock() to make the app responsive
    }



    private static class PriceUpdater extends Thread {
        private PricesContainer pricesContainer;
        private Random random = new Random();

        public PriceUpdater(PricesContainer pricesContainer) {
            this.pricesContainer = pricesContainer;
        }

        @Override
        public void run() {
            while(true) {
                pricesContainer.getLockObject().lock();
                try {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    pricesContainer.setBitcoinPrice(random.nextInt(20000));
                    pricesContainer.setEtherPrice(random.nextInt(2000));
                    pricesContainer.setLitecoinPrice(random.nextInt(500));
                    pricesContainer.setBitcoinCashPrice(random.nextInt(5000));
                    pricesContainer.setRipplePrice(random.nextDouble());
                }
                finally {
                    pricesContainer.getLockObject().unlock();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
        }
    }
    private static class PricesContainer {
        public Lock lockObject = new ReentrantLock();
        private double bitcoinPrice;
        private double etherPrice;
        private double litecoinPrice;
        private double bitcoinCashPrice;
        private double ripplePrice;

        public Lock getLockObject() {
            return lockObject;
        }

        public double getBitcoinPrice() {
            return bitcoinPrice;
        }

        public void setBitcoinPrice(double bitcoinPrice) {
            this.bitcoinPrice = bitcoinPrice;
        }

        public double getEtherPrice() {
            return etherPrice;
        }

        public void setEtherPrice(double etherPrice) {
            this.etherPrice = etherPrice;
        }

        public double getLitecoinPrice() {
            return litecoinPrice;
        }

        public void setLitecoinPrice(double litecoinPrice) {
            this.litecoinPrice = litecoinPrice;
        }

        public double getBitcoinCashPrice() {
            return bitcoinCashPrice;
        }

        public void setBitcoinCashPrice(double bitcoinCashPrice) {
            this.bitcoinCashPrice = bitcoinCashPrice;
        }

        public double getRipplePrice() {
            return ripplePrice;
        }

        public void setRipplePrice(double ripplePrice) {
            this.ripplePrice = ripplePrice;
        }
    }
}
