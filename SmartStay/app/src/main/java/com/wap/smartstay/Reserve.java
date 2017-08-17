package com.wap.smartstay;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.net.Socket;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Reserve extends AppCompatActivity implements View.OnClickListener {
    Socket client;
    String ip = ServerInformation.serverIP;
    int port = ServerInformation.port;

    Thread thread;
    ClientThread clientThread;
    Handler handler;

    Button btnFromPicker, btnToPicker, btnPayment;
    TextView textFrom, textTo, textPeriod;
    private int from_Year, from_Month, from_Day;
    private int to_Year, to_Month, to_Day;

    private int start_Year, start_Month, start_Day;
    private int end_Year, end_Month, end_Day;
    String str_startDate, str_endDate;
    Date start;
    Date end;
    Long diffDate;
    String period;
    int rNum;

    public static String officeCode, inform;

    TextView information,tvAccom;

    String[] roomType={"스위트룸","스탠다드","슈페리얼"};
    int pic[] = {R.drawable.one, R.drawable.two, R.drawable.three };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve);

        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        btnFromPicker = (Button) findViewById(R.id.setFromBtn);
        btnToPicker = (Button) findViewById(R.id.setToBtn);
        btnPayment = (Button) findViewById(R.id.paymentBtn);

        textFrom = (TextView) findViewById(R.id.textFrom);
        textTo = (TextView) findViewById(R.id.textTo);
        textPeriod = (TextView) findViewById(R.id.textPeriod);

        information = (TextView) findViewById(R.id.information);
        tvAccom = (TextView) findViewById(R.id.tvAccom);

        btnFromPicker.setOnClickListener(this);
        btnToPicker.setOnClickListener(this);
        btnPayment.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("예약");
        setSupportActionBar(toolbar);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setPrompt("방을 고르세요");

        ArrayAdapter<String> spinAdap = new ArrayAdapter<String>(this, R.layout.reserve_spinner_item, roomType);
        spin.setAdapter(spinAdap);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), roomType[i], Toast.LENGTH_LONG).show();
                if(i == 0){
                    rNum = 001;
                    tvAccom.setText("2인");
                }else if(i == 1){
                    rNum = 002;
                    tvAccom.setText("2~3인");
                }else{
                    rNum = 003;
                    tvAccom.setText("4인 이상");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        connect();

        JSONObject jo = new JSONObject();

        try{
            jo.put("head","Reserve");
            jo.put("ID",  Login.Id);
            jo.put("STARTDATE",start);
            jo.put("ENDDATE",end);
            jo.put("RNUM",rNum);
        }catch (Exception e){

        }
        String data = jo.toString();

        clientThread.send(data);
        Log.i("test","대기");
    }

    @Override
    public void onClick(View view) {

        if (view == btnFromPicker) { // startDate

            final Calendar startCal = Calendar.getInstance();

            from_Year = startCal.get(Calendar.YEAR);
            from_Month = startCal.get(Calendar.MONTH);
            from_Day = startCal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dp1 = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            textFrom.setText(year + "." + (monthOfYear + 1) + "." + dayOfMonth + " ~"); //N월 N일 ~ N월 N
                            start_Year = year;
                            start_Month = monthOfYear+1;
                            start_Day = dayOfMonth;
                            str_startDate = "";

                            if (start_Month < 10) {
                                str_startDate+="0" + start_Month + "/";
                                if (start_Day < 10) {
                                    str_startDate+="0" + start_Day + "/" + start_Year;
                                }
                                else
                                    str_startDate+=start_Day + "/" + start_Year;
                            } else {
                                str_startDate+=start_Month + "/";
                                if (start_Day < 10) {
                                    str_startDate+="0" + start_Day + "/" + start_Year;
                                }
                                else
                                    str_startDate+=start_Day + "/" + start_Year;
                            }

                            Log.i("start", str_startDate);
                        }
                    }, from_Year, from_Month, from_Day);

            dp1.getDatePicker().setMinDate(startCal.getTimeInMillis());
            dp1.show();
        }

        if (view == btnToPicker) {  // endDate

            final Calendar endCal = Calendar.getInstance();
            to_Year = endCal.get(Calendar.YEAR);
            to_Month = endCal.get(Calendar.MONTH);
            to_Day = endCal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dp2 = new DatePickerDialog(this,

                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            textTo.setText(year + "."+ (monthOfYear + 1) + "." + dayOfMonth ); //N월 N일 ~ N월 N일
                            end_Year = year;
                            end_Month = monthOfYear+1;
                            end_Day = dayOfMonth;
                            str_endDate = "";

                            if(to_Month < 10){
                                str_endDate += "0" + end_Month + "/";
                                if(to_Day < 10){
                                    str_endDate += "0" + end_Day + "/" + end_Year;
                                }
                                else
                                    str_endDate += end_Day + "/" + end_Year;
                            }else {
                                str_endDate+=end_Month + "/";
                                if(to_Day < 10){
                                    str_endDate += "0" + end_Day + "/" + end_Year;
                                }
                                else
                                    str_endDate += end_Day + "/" + end_Year;
                            }

                            Log.i("end",str_endDate);
                            try{
                                SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");

                                start = date.parse(str_startDate);
                                end = date.parse(str_endDate);

                                long diff = Math.abs((int)(start.getTime()) - (int)(end.getTime()) );
                                diffDate = diff / (24 * 60 * 60 * 1000);

                                period = Long.toString(diffDate);

                                if(diffDate == 0){
                                    textPeriod.setText("1박");
                                }else if(diffDate > 0){
                                    textPeriod.setText(diffDate + "박" + (diffDate + 1) + "일");
                                }else{
                                    Toast.makeText(getApplicationContext(),"잘못된 날짜입니다",Toast.LENGTH_SHORT).show();
                                }

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }, to_Year, to_Month, to_Day);

            dp2.getDatePicker().setMinDate(endCal.getTimeInMillis());
            dp2.show();
        }

        if (view == btnPayment) {
            Intent Intent = new Intent(this,Payment.class);
            startActivity(Intent);
        }
    }


    public void connect(){
        thread = new Thread(){
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);
                    clientThread = new ClientThread(client,handler,Login.class);
                    clientThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ClientThread.setRunningState(false);
        thread.interrupt();
    }
}