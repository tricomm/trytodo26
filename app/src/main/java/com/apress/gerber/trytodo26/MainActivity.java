package com.apress.gerber.trytodo26;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button bn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView);
        final Handler handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String ss = msg.obj.toString();
                tv.setText(ss);
            }
        };



        final Runnable mywork =new Runnable() {

            String strdate;
            public void run() {
                while (true)
                {
                    Date date = new Date();
                    DateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    strdate = format.format(date);
                    Message msg = handler.obtainMessage();
                    msg.what = 10;
                    msg.obj = strdate;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        bn = (Button) findViewById(R.id.button);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, mywork, "WorkThread");
                workThread.start();
            }
        });



    }
}
