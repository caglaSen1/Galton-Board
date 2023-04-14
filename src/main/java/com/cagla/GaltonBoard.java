package com.cagla;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class GaltonBoard {

    private final int numOfBins;
    private final int numOfThread;
    private final AtomicIntegerArray results;
    private final ArrayList<Thread> threadList;

    public GaltonBoard(int numOfBins, int numOfThread) {
        this.numOfBins = numOfBins;
        this.numOfThread = numOfThread;
        this.results = new AtomicIntegerArray(numOfBins);
        this.threadList = new ArrayList<>();
    }

    public void run() {
        for (int i = 0; i < numOfThread; i++) {
            threadList.add(new Thread(() -> {
                int value = numOfBins + 1;
                Random rand = new Random();
                float float_random;

                for (int j = 0; j < numOfBins - 1; j++) {
                    float_random = rand.nextFloat();
                    if (float_random > 0.5) {
                        value += 1;
                    } else {
                        value -= 1;
                    }
                }

                results.incrementAndGet((value / 2) - 1);
            }));
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int sum() {
        int sum = 0;

        for (int i = 0; i < results.length(); i++) {
            sum += results.get(i);
        }

        return sum;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        int sizeOfColumn = 10;

        for (int i = 0; i < numOfBins; i++) {
            String iAsString = Integer.toString(i);

            output.append(iAsString);
            output.append(" ".repeat(sizeOfColumn - iAsString.length()));
            output.append(Integer.toString(results.get(i)));
            output.append("\n");
        }

        int sum = sum();
        String infos = String.format("Number of requested thread: %d\nSum of Bin values: %d\n", numOfThread, sum);

        if (numOfThread == sum) {
            infos += "Nice work! Both of them are equal";
        }

        output.append(infos);

        return output.toString();
    }
}