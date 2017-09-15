package com.ex.kamrul.bluetoothcontroller;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.swtich);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBluetoothAdapter==null)
                    Toast.makeText(getBaseContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT);
                else if(mBluetoothAdapter.isEnabled())
                {
                    btn.setText("OFF");
                    Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(intent);

                    IntentFilter intentFilter=new IntentFilter(BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED);
                }
                else if(mBluetoothAdapter.)
            }
        });
    }
}
