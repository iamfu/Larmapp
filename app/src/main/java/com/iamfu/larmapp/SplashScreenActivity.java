package com.iamfu.larmapp;

/**
 * Created by nattha on 6/2/16 AD.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;


public class SplashScreenActivity extends Activity {
    private Handler handler;
    private Runnable runnable;
    private long delay_times;
    private long times =3000L;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashscreen);
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {// รันหน้า splashscreen
                Intent intent = new Intent(SplashScreenActivity.this,LoginscreenActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }
    //ขณะที่เปิดแอปอยู่
    public void onResume(){
        super.onResume();
        delay_times = times;
        handler.postDelayed(runnable,delay_times);
        times = System.currentTimeMillis();
    }
    //ออกจากหน้าแอปพลิเคชั่น กดปุ่ม home
    public void onPause(){
        super.onPause();
        handler.removeCallbacks(runnable);
        times = delay_times - (System.currentTimeMillis()-times);
    }
}
