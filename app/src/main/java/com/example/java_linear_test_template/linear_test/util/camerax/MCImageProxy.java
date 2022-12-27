package com.example.java_linear_test_template.linear_test.util.camerax;

import android.annotation.SuppressLint;
import android.media.Image;

import androidx.camera.core.ImageProxy;



/**
 * Multi close able ImageProxy
 */
public class MCImageProxy {
    MCImageProxy(ImageProxy imageProxy, int closeCount) {
        this.imageProxy = imageProxy;
        this.closeCount = closeCount;
    }

    final public ImageProxy imageProxy;
    final private int closeCount;

    private int finishCloseCount = 0;
    public void close() {
        finishCloseCount += 1;
        if(finishCloseCount >= closeCount) {
            imageProxy.close();
        }
    }
}
