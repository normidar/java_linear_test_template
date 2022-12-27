package com.example.java_linear_test_template.linear_test.util.camerax;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import androidx.camera.core.ImageProxy;


import com.example.java_linear_test_template.linear_test.platform.TFilter;


/**
 * <ImageProxy, Bitmap>
 */
public class ImageProxyToBitmap extends TFilter<MCImageProxy, Bitmap> {
    @Override
    public void put(MCImageProxy value) {
        Bitmap bitmap = ImageConverter.imageProxyToBitmap(value.imageProxy);
        bitmap = rotate(bitmap, value.imageProxy.getImageInfo().getRotationDegrees());
        callAllListener(bitmap);
        value.close();
    }

    private Bitmap rotate(final Bitmap beforeBmp, int degrees) {
        final int w = beforeBmp.getWidth();
        final int h = beforeBmp.getHeight();

        final Matrix m = new Matrix();
        m.setRotate(degrees);

        final Matrix m2 = new Matrix();
        m2.setScale(-2,2);

        final Bitmap afterBmp = Bitmap.createBitmap(
                Bitmap.createBitmap(beforeBmp, 0, 0, w, h, m, false),
                                                0,0, h, w, m2, false);
        return afterBmp;
    }
}
