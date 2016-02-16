package com.example.abon2.clockdial;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends Activity {

    private ClockDialView clockDial;
    private static MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clockDial = (ClockDialView) findViewById(R.id.clockDial);
        if(handler == null){handler = new MyHandler();}
        //send initial message to update time
        handler.sendMessage(handler.obtainMessage(234, this));
    }

    protected void timeToUpdateClock(){
        GregorianCalendar cal = new GregorianCalendar();
        int hour = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);

        clockDial.setTime(hour, min, sec);

        //send message to handler to update time, delay by 1 sec
        Message msg = handler.obtainMessage(234,this);
        handler.sendMessageDelayed(msg, 1000);
    }
    private static class MyHandler extends Handler{
        @Override
        //handle message from Handler.sendMessage()
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 234:
                    MainActivity act = (MainActivity) msg.obj;
                    act.timeToUpdateClock();
                    break;
            }
        }
    }
}


