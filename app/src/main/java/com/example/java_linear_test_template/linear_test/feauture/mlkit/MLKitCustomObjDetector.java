package com.example.java_linear_test_template.linear_test.feauture.mlkit;

import android.annotation.SuppressLint;
import android.media.Image;

import androidx.annotation.NonNull;

import com.example.java_linear_test_template.linear_test.platform.TFilter;
import com.example.java_linear_test_template.linear_test.util.camerax.MCImageProxy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.objects.DetectedObject;
import com.google.mlkit.vision.objects.ObjectDetection;
import com.google.mlkit.vision.objects.ObjectDetector;
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions;

import java.util.List;

public class MLKitCustomObjDetector extends TFilter<MCImageProxy, List<DetectedObject>> {

    LocalModel localModel =
            new LocalModel.Builder()
                    .setAssetFilePath("mask_detector.tflite")
                    // or .setAbsoluteFilePath(absolute file path to model file)
                    // or .setUri(URI to model file)
                    .build();

    // Live detection and tracking
    CustomObjectDetectorOptions customObjectDetectorOptions =
            new CustomObjectDetectorOptions.Builder(localModel)
                    .setDetectorMode(CustomObjectDetectorOptions.STREAM_MODE)
                    .enableClassification()
                    .setClassificationConfidenceThreshold(0.5f)
                    .setMaxPerObjectLabelCount(3)
                    .build();

    ObjectDetector objectDetector =
            ObjectDetection.getClient(customObjectDetectorOptions);

    @Override
    @SuppressLint("UnsafeOptInUsageError")
    public void put(MCImageProxy value) {
        Image mediaImage = value.imageProxy.getImage();
        if (mediaImage != null) {
            InputImage image = InputImage.fromMediaImage(mediaImage,
                    value.imageProxy.getImageInfo().getRotationDegrees());
            objectDetector
                    .process(image)
                    .addOnSuccessListener(results -> {
                        callAllListener(results);
                    })
                    .addOnCompleteListener(new OnCompleteListener<List<DetectedObject>>() {
                        @Override
                        public void onComplete(@NonNull Task<List<DetectedObject>> task) {
                            value.close();
                        }
                    });
        }
    }
}
