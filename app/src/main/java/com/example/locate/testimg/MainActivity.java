package com.example.locate.testimg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button viewBtn;
    private Button recordBtn;
    private Button mp4Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewBtn = findViewById(R.id.id_view_btn);
        recordBtn = findViewById(R.id.id_record_btn);
        mp4Btn = findViewById(R.id.id_mp4_btn);
        mp4Btn.setOnClickListener(this);
        viewBtn.setOnClickListener(this);
        recordBtn.setOnClickListener(this);
        applyPermision();
    }

    private void applyPermision() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

        }else {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_view_btn:
                Intent intent = new Intent(MainActivity.this,ViewActivity.class);
                startActivity(intent);
                break;
            case R.id.id_record_btn:
                Intent intent1 = new Intent(MainActivity.this,CameraTextureViewActivity.class);
                startActivity(intent1);
                break;
            case R.id.id_mp4_btn:
                Intent intent2 = new Intent(MainActivity.this,Mp4Activity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}
