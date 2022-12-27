package com.example.java_linear_test_template.linear_test.feauture;

import com.example.java_linear_test_template.linear_test.platform.TOutput;

public class AccNumberOutput extends TOutput<Long> {
    private long counter = 0;
    @Override
    public void start() {
        isRunning = true;
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while(true) {
                        sleep(100);
                        callAllListener(counter++);
                        if(!isRunning) return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    private boolean isRunning = true;
    @Override
    public void stop() {
        isRunning = false;
    }
}
