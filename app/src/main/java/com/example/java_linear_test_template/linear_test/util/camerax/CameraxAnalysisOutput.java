package com.example.java_linear_test_template.linear_test.util.camerax;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.example.java_linear_test_template.linear_test.platform.TOutput;
import com.google.common.util.concurrent.ListenableFuture;


/**
 * output bitmap
 */
public class CameraxAnalysisOutput extends TOutput<MCImageProxy> {
    private Context context;
    private int nextCount = 1;

    public CameraxAnalysisOutput(Context context) {
        this.context = context;
    }

    public CameraxAnalysisOutput(Context context, int nextCount) {
        assert nextCount > 0;
        this.context = context;
        this.nextCount = nextCount;
    }


    private ProcessCameraProvider cameraProvider;
    @Override
    public void start() {
        ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture = ProcessCameraProvider.getInstance(context);
        cameraProviderListenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    cameraProvider = cameraProviderListenableFuture.get();

                    ImageAnalysis imageAnalysis = new ImageAnalysis.Builder().build();
                    imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(context),
                            new ImageAnalysis.Analyzer() {
                                @Override
                                public void analyze(@NonNull ImageProxy image) {
                                    callAllListener(new MCImageProxy(image, nextCount));
                                }
                            });

                    // Unbind use cases before rebinding
                    cameraProvider.unbindAll();

                    // Bind use cases to camera
                    cameraProvider.bindToLifecycle(
                            (LifecycleOwner) context, CameraSelector.DEFAULT_FRONT_CAMERA, imageAnalysis);

                } catch (Exception e) {
                }
            }
        }, ContextCompat.getMainExecutor(context));
    }

    @Override
    public void stop() {
        cameraProvider.unbindAll();
    }
}
