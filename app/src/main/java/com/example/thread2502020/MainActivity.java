package com.example.thread2502020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    MyHandler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHandler = new MyHandler();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putInt("threada",5);
                Message message = myHandler.obtainMessage();
                message.setData(bundle);
                message.what = 1;
                myHandler.sendMessage(message);
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putInt("threadb",10);
                Message message = myHandler.obtainMessage();
                message.setData(bundle);
                message.what = 2;
                myHandler.sendMessage(message);
            }
        });

        threadA.start();
        threadB.start();
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            switch (msg.what){
                case 1 :
                    if (bundle.getInt("threada",-1) != -1){
                    Toast.makeText(MainActivity.this,bundle.getInt("threada",-1) + "", Toast.LENGTH_SHORT).show();
                }
                break;
                case 2 :
                    if (bundle.getInt("threadb",-1) != -1){
                        Toast.makeText(MainActivity.this,bundle.getInt("threadb",-1) + "", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }
}