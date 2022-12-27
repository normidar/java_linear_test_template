package com.example.java_linear_test_template.linear_test.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.example.java_linear_test_template.linear_test.platform.TFinal;


public class ShowBitmapToImageView extends TFinal<Bitmap> {
    public ShowBitmapToImageView(Activity context, ImageView imageView) {
        this.context = context;
        this.imageView = imageView;
    }
    final private Activity context;
    final private ImageView imageView;

    @Override
    public void put(Bitmap value) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(value);
            }
        });
    }
}