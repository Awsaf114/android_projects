package com.ex.kamrul.timepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int TIME_DIALOG_ID =0 ;
    private static final int DATE_DIALOG_ID =1212121;
    int hour_x,min_x;
    int year_x,month_x,day_x;
    private TimePickerDialog.OnTimeSetListener timePickerListener=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            hour_x=i;
            min_x=i1;
            Toast.makeText(MainActivity.this,"Hour:"+hour_x+" min:"+min_x,Toast.LENGTH_SHORT).show();
        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            year_x=year;
            month_x=month;
            day_x=day;
            Toast.makeText(MainActivity.this,"Today is "+day_x+"/"+month_x+"/"+year_x,Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showTimePickerDialogue();
    }

   Button timepicker,datepicker,notify;
    public void showTimePickerDialogue()
    {
        timepicker=(Button)findViewById(R.id.timePicker);
        datepicker=(Button)findViewById(R.id.datePicker);
        notify=(Button)findViewById(R.id.notify);
        timepicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(TIME_DIALOG_ID);
                    }
                }
        );
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        notify.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent();
                        PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,0);
                        Notification notification= new Notification.Builder(MainActivity.this)
                                .setTicker("Ticker Title")
                                .setContentTitle("Content Title")
                                .setContentText("Content Text")
                                .setSmallIcon(R.drawable.ic_action_name)
                                .addAction(R.drawable.ic_action_name,"Action1",pendingIntent)
                                .addAction(R.drawable.ic_action_name,"Action2",pendingIntent)
                                .setContentIntent(pendingIntent).getNotification();
                        notification.flags=Notification.FLAG_AUTO_CANCEL;
                        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(0,notification);

                    }
                }
        );
    }
    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id==TIME_DIALOG_ID)
        {
            return new TimePickerDialog(MainActivity.this,timePickerListener,hour_x,min_x,false);
        }
        else if(id==DATE_DIALOG_ID)
        {
            return new DatePickerDialog(MainActivity.this,datePickerListener,year_x,month_x,day_x);
        }
        return  null;
    }



}
