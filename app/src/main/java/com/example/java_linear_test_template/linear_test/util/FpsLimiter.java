package com.example.java_linear_test_template.linear_test.util;

import com.example.java_linear_test_template.linear_test.platform.TFilter;

public class FpsLimiter<T> extends TFilter<T,T> {
    public FpsLimiter(long fps){
        faceTrackingMinimalFrameInterval = 1000 / fps;
    }
    private final long faceTrackingMinimalFrameInterval;
    private long lastFaceTrackingProcessed = 0;
    @Override
    public void put(T value) {
        long timestamp = System.currentTimeMillis();
        if (timestamp - lastFaceTrackingProcessed < faceTrackingMinimalFrameInterval) {
            return;
        } else {
            lastFaceTrackingProcessed = timestamp;
        }
        callAllListener(value);
    }
}
