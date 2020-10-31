package com.example.thread2502020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    MyFlag myFlag;
    int a, b, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = b = c = 0;

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 1; i <= 10; i++) {
                        a = i;
                        Log.d("BBB", "Giá trị A : " + a);
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 1; i <= 10; i++) {
                        b = i;
                        Log.d("BBB", "Giá trị B : " + b);
                    }
                }
            }
        });
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 1; i <= 10; i++) {
                        c = a + b;
                        Log.d("BBB", "Giá trị C : " + c);
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}