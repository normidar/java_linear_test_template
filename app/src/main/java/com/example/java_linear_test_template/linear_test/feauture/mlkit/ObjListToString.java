package com.example.java_linear_test_template.linear_test.feauture.mlkit;

import com.example.java_linear_test_template.linear_test.platform.TFilter;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.objects.DetectedObject;

import java.util.List;

public class ObjListToString extends TFilter<List<DetectedObject>, String> {

    @Override
    public void put(List<DetectedObject> value) {
        StringBuilder rt = new StringBuilder();
        for (DetectedObject l: value) {
            rt.append(l.getLabels().get(0).getText()).append(" : ").append(l.getLabels().get(0).getConfidence()).append("\n");
        }
        callAllListener(rt.toString());
    }
}
