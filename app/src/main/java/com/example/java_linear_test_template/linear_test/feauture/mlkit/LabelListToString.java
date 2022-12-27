package com.example.java_linear_test_template.linear_test.feauture.mlkit;

import com.example.java_linear_test_template.linear_test.platform.TFilter;
import com.google.mlkit.vision.label.ImageLabel;

import java.util.List;

public class LabelListToString extends TFilter<List<ImageLabel>, String> {

    @Override
    public void put(List<ImageLabel> value) {
        StringBuilder rt = new StringBuilder();
        for (ImageLabel l: value) {
            rt.append(l.getText()).append(" : ").append(l.getConfidence()).append("\n");
        }
        callAllListener(rt.toString());
    }
}
