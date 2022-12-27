package com.example.java_linear_test_template.linear_test.util;

import com.example.java_linear_test_template.linear_test.platform.TFilter;

public class ToString<I> extends TFilter<I,String> {
    @Override
    public void put(I value) {
        callAllListener(value.toString());
    }
}
