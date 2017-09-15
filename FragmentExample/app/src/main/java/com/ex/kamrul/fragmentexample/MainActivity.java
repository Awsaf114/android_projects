package com.ex.kamrul.fragmentexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import layout.FragmentOne;
import layout.FragmentThree;
import layout.FragmentTwo;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    String[] country = {
            "Afghanistan",
            "Albania",
            "Algeria",
            "Andorra",
            "Angola",
            "Antigua and Barbuda",
            "Argentina",
            "Armenia",
            "Aruba",
            "Australia",
            "Austria",
            "Azerbaijan"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public void changeFragment(View v)
    {
        Fragment fragment;

        if(v==findViewById(R.id.fragmentone))
        {
            fragment= new FragmentOne();
            FragmentManager fragmentManager=getFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmemt,fragment);
            fragmentTransaction.commit();
        }
        if(v==findViewById(R.id.fragmenttwo))
        {
            fragment= new FragmentTwo();
            FragmentManager fragmentManager=getFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmemt,fragment);
            fragmentTransaction.commit();
        }
    }
}
