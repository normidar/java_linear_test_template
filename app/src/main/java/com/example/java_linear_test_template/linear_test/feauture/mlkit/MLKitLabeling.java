package com.example.java_linear_test_template.linear_test.feauture.mlkit;

import android.annotation.SuppressLint;
import android.media.Image;

import androidx.annotation.NonNull;

import com.example.java_linear_test_template.linear_test.platform.TFilter;
import com.example.java_linear_test_template.linear_test.util.camerax.MCImageProxy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.custom.CustomImageLabelerOptions;

import java.util.List;

public class MLKitLabeling extends TFilter<MCImageProxy, List<ImageLabel>> {

    private final LocalModel localModel =
            new LocalModel.Builder()
                    .setAssetFilePath("ssdlite_mobilenet_v2.tflite")
                    // or .setAbsoluteFilePath(absolute file path to model file)
                    // or .setUri(URI to model file)
                    .build();

    private final CustomImageLabelerOptions customImageLabelerOptions =
            new CustomImageLabelerOptions.Builder(localModel)
                    .setConfidenceThreshold(0.5f)
                    .setMaxResultCount(5)
                    .build();
    private final ImageLabeler labeler = ImageLabeling.getClient(customImageLabelerOptions);

    @Override
    @SuppressLint("UnsafeOptInUsageError")
    public void put(MCImageProxy value) {
        Image mediaImage = value.imageProxy.getImage();
        if (mediaImage != null) {
            InputImage image = InputImage.fromMediaImage(mediaImage,
                    value.imageProxy.getImageInfo().getRotationDegrees());
            labeler.process(image)
                    .addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
                        @Override
                        public void onSuccess(List<ImageLabel> imageLabels) {
                            callAllListener(imageLabels);
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<List<ImageLabel>>() {
                        @Override
                        public void onComplete(@NonNull Task<List<ImageLabel>> task) {
                            value.close();
                        }
                    });
        }
    }
}
