package com.ex.kamrul.sqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText firstname,lastname,marks;
    Button addData,viewData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(MainActivity.this);
        firstname=(EditText) findViewById(R.id.firstname);
        lastname=(EditText) findViewById(R.id.surname);
        marks=(EditText)findViewById(R.id.mark);
        addData=(Button)findViewById(R.id.button);
        viewData=(Button)findViewById(R.id.button2);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                boolean isInserted=  mydb.insertData(firstname.getText().toString(),lastname.getText().toString(),marks.getText().toString());
                if(isInserted)
                    Toast.makeText(getBaseContext(),"Data is not inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getBaseContext(),"Data inserted",Toast.LENGTH_LONG).show();


            }
        });
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=mydb.getAllData();
                if(res.getCount()==0)
                {
                    showMessage("Error!","Nothing Found");
                  return;
                }
                else
                {
                    StringBuffer buff=new StringBuffer();
                    while(res.moveToNext())
                    {
                        buff.append("ID: "+res.getString(0))
                        .append("FirstName: "+res.getString(1))
                        .append("LAstName: "+res.getString(2))
                        .append("Mark: "+res.getString(3))
                        .append("\n");
                        showMessage("Data Founded!",buff.toString());
                    }

                }
            }
        });
    }


    public void showMessage(String title,String msg)
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle(title)
                .setCancelable(true)
                .setMessage(msg)
                .show();
    }

}