package com.example.java_linear_test_template.linear_test.platform;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Machine {
    List<List<TModule>> elements = new ArrayList<>();

    public Machine() {}

    public void add(TModule module) {
        List<TModule> elementList = new ArrayList<>();
        elementList.add(module);
        elements.add(elementList);
    }

    @SuppressLint("NewApi")
    public void add(TModule... module) {
        assert module.length != 0;
        elements.add(Arrays.stream(module).collect(Collectors.toList()));
    }

    public void run() {
        bindListListener(elements);
        ((TOutput) elements.get(0).get(0)).start();
    }

    public void stop(){
        ((TOutput) elements.get(0).get(0)).stop();
    }

    private void bindListListener(List<List<TModule>> modules) {
        for (int i = 1; i < modules.size(); i++) {
            List<TModule> moduleGroup = modules.get(i);
            TModule element = moduleGroup.get(0);
            InputAble inputAble = (InputAble) element;
            OutputAble outputAble = (OutputAble) modules.get(i - 1).get(0);
            setListener(outputAble, inputAble);

            // 長さが1以上の場合
            for (int j = 1; j < moduleGroup.size(); j++) {
                Log.d("liu-16", "v: ");
                TModule branch = moduleGroup.get(j);
                InputAble branchInputAble = (InputAble) branch;

                setListener(outputAble, branchInputAble);
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


