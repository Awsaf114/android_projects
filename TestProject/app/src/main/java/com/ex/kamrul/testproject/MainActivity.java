package com.ex.kamrul.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String tag="testapp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag,"Oncreate is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag,"onPauseIsCalled");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag,"onStopCalled");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag,"onDestroyCalled");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag,"onResumeCalled");
    }
}
