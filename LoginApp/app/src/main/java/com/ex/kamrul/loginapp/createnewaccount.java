package com.ex.kamrul.loginapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.style.BackgroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class createnewaccount extends AppCompatActivity {

    private static final int BIRTH_DATE_DIALOG =0;
    Button birthdate,show;
    int year_x,month_x,day_x;
    String email,password;
    EditText passwordEdit,emailEdit;


    int defaultbgcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnewaccount);
        birthdate=(Button)findViewById(R.id.birthdate);
        show=(Button)findViewById(R.id.show);
        passwordEdit=(EditText)findViewById(R.id.password);
        emailEdit=(EditText)findViewById(R.id.email);
        defaultbgcolor=emailEdit.getDrawingCacheBackgroundColor();
        emailEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                email=emailEdit.getText().toString();
                int l1=email.indexOf('@');
                int l2=email.indexOf('.');
                if(!(l1<l2)&&!b&&!email.isEmpty())
                {
                    emailEdit.setBackgroundColor(Color.rgb(239, 83, 59));
                    emailEdit.setTextColor(Color.WHITE);
                }
                else
                {

                    emailEdit.setBackgroundColor(defaultbgcolor);
                    emailEdit.setTextColor(Color.BLACK);
                }

            }
        });
        passwordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                password= passwordEdit.getText().toString();
                Toast.makeText(getApplicationContext(),password,Toast.LENGTH_SHORT).show();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=show.getText().toString();
                if(text=="SHOW")
                {
                    passwordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    show.setText("HIDE");
                    passwordEdit.setSelection(passwordEdit.length());

                }
                else
                {
                    passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    show.setText("SHOW");
                    passwordEdit.setSelection(passwordEdit.length());
                }
            }
        });
        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(BIRTH_DATE_DIALOG);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id==BIRTH_DATE_DIALOG)
            return new DatePickerDialog(this,dpickerlistener,year_x,month_x,day_x);

        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerlistener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            year_x=year;
            month_x=month;
            day_x=day;
            birthdate.setText("Date Of Birth : "+day_x+"/"+month_x+"/"+year_x);
        }
    };
}
