package com.example.locate.testimg;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;

public class ViewActivity extends AppCompatActivity {
    private static final String TAG = "TestMain";
    private ImageView imageView;
    private SurfaceView surfaceView;
    private RelativeLayout relativeLayout;
    private CustomView customView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initView();
        applyPermision();
    }

    private void initView() {
        imageView = findViewById(R.id.id_iv);
        surfaceView = findViewById(R.id.id_surface_view);
        relativeLayout = findViewById(R.id.id_rl);

        customView = new CustomView(this);
        relativeLayout.addView(customView);
    }

    private void applyPermision() {
        if (ContextCompat.checkSelfPermission(ViewActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "applyPermision: 已有权限");
            //1、imageview画图
//            imageDraw();
            //2、surfaceview画图
            surfaceDraw();

        }else {
            ActivityCompat.requestPermissions(ViewActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //1、imageview画图
//            imageDraw();
                //2、surfaceview画图
                surfaceDraw();
            }else {
                Toast.makeText(this, "已被拒绝权限", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * image画图
     */
    private void imageDraw(){
        if (imageView != null){
            Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath() + File.separator + "hh.jpg");
            imageView.setImageBitmap(bitmap);
        }
    }

    /**
     * surfaceview画图
     */
    private void surfaceDraw(){
        if (surfaceView != null){
            surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    if(holder == null){
                        return;
                    }

                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setStyle(Paint.Style.STROKE);

                    Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath() + File.separator + "hh.jpg");
                    Canvas canvas = holder.lockCanvas();// 先锁定当前surfaceView的画布
                    canvas.drawBitmap(bitmap,0,0,paint);
                    holder.unlockCanvasAndPost(canvas); // 解除锁定并显示在界面上
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });
        }
    }
}
