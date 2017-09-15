package com.ex.kamrul.loginapp;


import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static android.R.attr.y;

public class DatabaseConnector implements Runnable {
    PreparedStatement myStmt=null;
    ResultSet myRs;
    public static Connection myConn=null;
    DatabaseConnector()
    {
        Thread t=new Thread(this);
        t.start();
    }
    boolean connectDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting Database");
            myConn = DriverManager.getConnection("jdbc:mysql://192.168.0.101:3306/loginapp", "root", "");
            System.out.println("Connection successful");
            System.out.println("Creating statement");
        }
        catch (Exception e)
        {
            Log.e("Exception DB",e.toString());
        }
        return  true;
    }
    @Override
    public void run() {
       connectDatabase();
    }
}
