package com.example.java_linear_test_template;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.java_linear_test_template.linear_test.platform.Machine;
import com.example.java_linear_test_template.linear_test.feauture.AccNumberOutput;
import com.example.java_linear_test_template.linear_test.util.LogStringToTextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.logText1);
        textView2 = findViewById(R.id.logText2);
        textView3 = findViewById(R.id.logText3);
        textView4 = findViewById(R.id.logText4);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);


        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runTestMachine();
            }
        });

        findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (machine != null) {
                    machine.stop();
                }
            }
        });
    }

    Machine machine;
    private void runTestMachine() {
        machine = new Machine();

        machine.add(new AccNumberOutput());
        machine.add(new LogStringToTextView(textView1));
        machine.run();
    }


}