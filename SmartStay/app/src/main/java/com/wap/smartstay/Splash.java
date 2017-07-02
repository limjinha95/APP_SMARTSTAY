package com.wap.smartstay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by limjinha on 2017. 6. 27..
 */

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(getBaseContext(), Main.class);  // Intent 선언
//                startActivity(intent);   // Intent 시작
                finish();
            }
        }, 1000);  // 로딩화면 시간
    }



}


//public class Splash extends AppCompatActivity {
//
//    private static int SPLASH_TIME_OUT = 1000; //1초
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                Intent i = new Intent(Splash.this, Main.class);
//                startActivity(i);
//
//                finish();
//            }
//        }, SPLASH_TIME_OUT);
//    }
//}

