package com.example.java_linear_test_template.linear_test.util;

import android.widget.TextView;

import com.example.java_linear_test_template.linear_test.platform.TFinal;

public class LogStringToTextView extends TFinal<Object> {
    TextView textView;
    public LogStringToTextView(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void put(Object value) {
        textView.setText(value.toString());
    }
}
