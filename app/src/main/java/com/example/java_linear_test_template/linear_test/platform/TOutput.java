package com.example.java_linear_test_template.linear_test.platform;


import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class TOutput<T> extends TModule implements OutputAble<T> {
    public abstract void start();

    public abstract void stop();

    @SuppressLint("NewApi")
    public void callAllListener(T value) {
        listeners.forEach(tVoidFunction -> tVoidFunction.apply(value));
    }

    public void addListener(Function<T,Void> listener){
        listeners.add(listener);
    }

    public List<Function<T, Void>> listeners = new ArrayList<>();
}
