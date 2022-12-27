package com.example.java_linear_test_template.linear_test.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.java_linear_test_template.linear_test.platform.TFilter;


/**
 * Bitmap, Bitmap
 */
public class BitmapRotate extends TFilter<Bitmap, Bitmap> {
    BitmapRotate(int degrees) {
        this.degrees = degrees;
    }
    final private int degrees;

    @Override
    public void put(Bitmap value) {
        callAllListener(rotate(value));
    }

    Bitmap rotate(final Bitmap beforeBmp) {
        final int w = beforeBmp.getWidth();
        final int h = beforeBmp.getHeight();

        final Matrix m = new Matrix();
        m.setRotate(degrees);

        final Bitmap afterBmp = Bitmap.createBitmap(beforeBmp, 0, 0, w, h, m, false);
        return afterBmp;
    }
}
