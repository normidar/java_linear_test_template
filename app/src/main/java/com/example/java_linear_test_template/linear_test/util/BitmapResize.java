package com.example.java_linear_test_template.linear_test.util;

import android.graphics.Bitmap;

import com.example.java_linear_test_template.linear_test.platform.TFilter;

public class BitmapResize extends TFilter<Bitmap, Bitmap> {
    public BitmapResize(int width){
        this.width = width;
    }
    int width;

    @Override
    public void put(Bitmap value) {
        int tWidth = width;
        int tHeight = (int)(((float)width / (float)value.getWidth()) * (float)value.getHeight());
        Bitmap b = Bitmap.createScaledBitmap(value, tWidth, tHeight, false);
        callAllListener(b);
    }
}