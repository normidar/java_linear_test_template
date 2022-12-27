package com.example.java_linear_test_template;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.java_linear_test_template.linear_test.feauture.AccNumberOutput;
import com.example.java_linear_test_template.linear_test.feauture.mlkit.LabelListToString;
import com.example.java_linear_test_template.linear_test.feauture.mlkit.MLKitLabeling;
import com.example.java_linear_test_template.linear_test.platform.LinearWrapper;
import com.example.java_linear_test_template.linear_test.platform.Machine;
import com.example.java_linear_test_template.linear_test.util.LogStringToTextView;
import com.example.java_linear_test_template.linear_test.util.ShowBitmapToImageView;
import com.example.java_linear_test_template.linear_test.util.camerax.CameraxAnalysisOutput;
import com.example.java_linear_test_template.linear_test.util.camerax.ImageProxyToBitmap;

public class CameraxActivity extends AppCompatActivity {

    private ImageView imageView;

    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camerax);

        imageView = findViewById(R.id.imageView);
        textView1 = findViewById(R.id.logText1);

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runTestMachine();
            }
        });
    }

    Machine machine;
    private void runTestMachine() {
        machine = new Machine();

        machine.add(new CameraxAnalysisOutput(this, 2));
        // ↓ change next count when next module over 1 　　　　　　　　 ↑
        machine.add(
                new MLKitLabeling(),
                new LinearWrapper(
                        new ImageProxyToBitmap(),
                        new ShowBitmapToImageView(this, imageView)
                )
        );

        machine.add(new LabelListToString());
        machine.add(new LogStringToTextView(textView1));

        machine.run();
    }

}