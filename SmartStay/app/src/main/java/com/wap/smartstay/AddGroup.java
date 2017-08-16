package com.wap.smartstay;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.Socket;

public class AddGroup extends AppCompatActivity {
    EditText inputId;
    TextView addGroupInfo;

    Context cont;
    Socket client;
    String ip = "210.114.91.91";
    int port = 9010;
    Thread thread;
    ClientThread clientThread;
    Handler handler;

    static int idCheck = 0;
    public static String groupId, groupName, groupPnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgroup);

        inputId = (EditText) findViewById(R.id.addgroupInputId);
        addGroupInfo = (TextView) findViewById(R.id.addGroupInfo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("동숙객 추가");

        Button addGroupCheckIdBtn = (Button) findViewById(R.id.addgroupCheckIdBtn);
        final Button addGroupSaveBtn = (Button) findViewById(R.id.addgroupSaveBtn);
        addGroupSaveBtn.setEnabled(false);

        addGroupCheckIdBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jo = new JSONObject();
                try {
                    jo.put("head", "SelectUser");
                    jo.put("ID", inputId.getText().toString());
                } catch (Exception e) {

                }

                String data = jo.toString();
                clientThread.send(data);

                if (idCheck == 1) {
                    Toast.makeText(AddGroup.this, "존재하지 않는 ID입니다.", Toast.LENGTH_SHORT).show();
                    inputId.setText("");
                    addGroupSaveBtn.setEnabled(false);
                } else if (idCheck == 2) {
                    Toast.makeText(AddGroup.this, "확인되었습니다.", Toast.LENGTH_SHORT).show();
                    addGroupInfo.setText("* " + groupId + " " + groupName + " " + groupPnum);

                    addGroupSaveBtn.setEnabled(true);

                    addGroupSaveBtn.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ListView listview;
                            AddGroupListViewAdapter adapter;

                            adapter = new AddGroupListViewAdapter();

                            listview = (ListView) findViewById(R.id.listview_addgroup);
                            listview.setAdapter(adapter);

                            adapter.addItem(groupName, groupId, groupPnum);

                            idCheck = 0;
                        }
                    });

                }
            }
        });


    }
}
