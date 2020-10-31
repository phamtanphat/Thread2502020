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
        myFlag = new MyFlag(0);

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 1; i <= 10;) {
                        if (myFlag.position == 0){
                            a = i++;
                            Log.d("BBB", "Giá trị A : " + a);
                            myFlag.position = 1;
                            myFlag.notifyAll();
                        }else{
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 1; i <= 10;) {
                        if (myFlag.position == 1){
                            b = i++;
                            Log.d("BBB", "Giá trị B : " + b);
                            myFlag.position = 2;
                            myFlag.notifyAll();
                        }else{
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 1; i <= 10; ) {
                        if (myFlag.position == 2){
                            c = a + b;
                            Log.d("BBB", "Giá trị C : " + c);
                            myFlag.position = 0;
                            myFlag.notifyAll();
                            i++;
                        }else{
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }
}