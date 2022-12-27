package com.example.java_linear_test_template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

public class PermissionsActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        checkForPermission();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkForPermission() {
        int permissionCheck = checkSelfPermission(Manifest.permission.CAMERA);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            jumpToNextActivity();
        } else {
            ActivityCompat.
                    requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CONTACT);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CONTACT) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                jumpToNextActivity();
            }
        }
    }

    private void jumpToNextActivity() {
        // write you code here

//        Example:
        Intent intent = new Intent(this, CameraxActivity.class);
        startActivity(intent);
    }
}