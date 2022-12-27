package com.example.java_linear_test_template.linear_test.platform;

import java.util.function.Function;

public interface OutputAble<T> {
    void addListener(Function<T, Void> listener);
}
