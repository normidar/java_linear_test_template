package com.example.java_linear_test_template.linear_test.platform;


import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * input and output
 * @param <I> input type
 * @param <T> output type
 */
public abstract class TFilter<I, T> extends TModule implements OutputAble<T>, InputAble<I> {
    abstract public void put(I value);

    @SuppressLint("NewApi")
    public void callAllListener(T value) {
        listeners.forEach(tVoidFunction -> tVoidFunction.apply(value));
    }

    public void addListener(Function<T,Void> listener){
        listeners.add(listener);
    }

    public List<Function<T, Void>> listeners = new ArrayList<>();
}
