package com.example.java_linear_test_template.linear_test.platform;

/**
 * データの終点
 * @param <T> アウトプットするタイプ
 */
public abstract class TFinal<T> extends TModule implements InputAble<T> {
    public abstract void put(T value);
}

