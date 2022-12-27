package com.example.java_linear_test_template.linear_test.util.camerax;

import android.graphics.Bitmap;

import androidx.camera.core.ImageProxy;


import com.example.java_linear_test_template.linear_test.platform.TFilter;


/**
 * <ImageProxy, Bitmap>
 */
public class ImageProxyToBitmap extends TFilter<MCImageProxy, Bitmap> {
    @Override
    public void put(MCImageProxy value) {
        Bitmap bitmap = ImageConverter.imageProxyToBitmap(value.imageProxy);
        callAllListener(bitmap);
        value.close();
    }
}
