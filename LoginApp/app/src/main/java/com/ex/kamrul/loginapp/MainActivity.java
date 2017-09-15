package com.ex.kamrul.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{



    DatabaseConnector dbconn=null;
    TextView txtview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCreateAccount(View view)
    {
        Intent createaccount=new Intent("com.ex.kamrul.loginapp.createnewaccount");
        startActivity(createaccount);
        txtview=(TextView)findViewById( R.id.connection );
        dbconn=new DatabaseConnector();
        while (DatabaseConnector.myConn==null);
        txtview.setText( "Connected" );
    }




}
