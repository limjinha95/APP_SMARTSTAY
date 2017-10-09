package com.wap.smartstay;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.Switch;

public class AlarmSet extends AppCompatActivity {
    SharedPreferences pref;
    Switch[] sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

        sw = new Switch[5];
        pref = getSharedPreferences("Switch", Activity.MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("알림 설정");

        Switch.OnCheckedChangeListener onCheckedChangeListener = new Switch.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                SharedPreferences.Editor editor = pref.edit();
                switch(buttonView.getId()){
                    case R.id.appAlarmSwitch:
                        editor.putBoolean("sw0",isChecked);
                        break;
                    case R.id.appSlienceAlarmSwitch:
                        editor.putBoolean("sw1",isChecked);
                        break;
                    case R.id.appRunningSoundAlarmSwitch:
                        editor.putBoolean("sw2",isChecked);
                        break;
                    case R.id.appRunningSlienceAlarmSwitch:
                        editor.putBoolean("sw3",isChecked);
                        break;
                    case R.id.openDoorAlarmSwitch:
                        editor.putBoolean("sw4",isChecked);
                        break;
                }
                editor.commit();
            }
        };

        sw[0] = (Switch)findViewById(R.id.appAlarmSwitch);
        sw[0].setOnCheckedChangeListener(onCheckedChangeListener);
        sw[1] = (Switch)findViewById(R.id.appSlienceAlarmSwitch);
        sw[1].setOnCheckedChangeListener(onCheckedChangeListener);
        sw[2] = (Switch)findViewById(R.id.appRunningSoundAlarmSwitch);
        sw[2].setOnCheckedChangeListener(onCheckedChangeListener);
        sw[3] = (Switch)findViewById(R.id.appRunningSlienceAlarmSwitch);
        sw[3].setOnCheckedChangeListener(onCheckedChangeListener);
        sw[4] = (Switch)findViewById(R.id.openDoorAlarmSwitch);
        sw[4].setOnCheckedChangeListener(onCheckedChangeListener);
        switchInit();
    }

    public void switchInit(){
        for(int i = 0 ; i < 5; i++){
            if(pref.getBoolean("sw"+i+"", false))
                sw[i].setChecked(true);
        }
    }

}
