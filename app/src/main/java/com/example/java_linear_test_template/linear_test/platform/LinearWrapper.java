package com.example.java_linear_test_template.linear_test.platform;

import android.annotation.SuppressLint;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LinearWrapper extends TFinal {

    final List<TModule> modules;
    @SuppressLint("NewApi")
    public LinearWrapper(TModule... modules){
        this.modules = Arrays.stream(modules).collect(Collectors.toList());
        bindListListener(this.modules);
    }


    @Override
    public void put(Object value) {
        if(!modules.isEmpty()){
            InputAble inputAble = (InputAble) modules.get(0);
            inputAble.put(value);
        }
    }

    private void bindListListener(List<TModule> modules) {
        for (int i = 1; i < modules.size(); i++) {
            TModule element = modules.get(i);
            if (element instanceof InputAble) {
                InputAble inputAble = (InputAble) element;
                OutputAble outputAble = (OutputAble) modules.get(i-1);
                setListener(outputAble, inputAble);
            }
        }
    }

    @SuppressLint("NewApi")
    private void setListener(OutputAble outputAble, InputAble inputAble) {
        outputAble.addListener(new Function() {
            @Override
            public Object apply(Object o) {
                inputAble.put(o);
                return null;
            }
        });
    }
}
