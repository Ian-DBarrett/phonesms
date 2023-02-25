package com.example.phonesms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;
import android.telephony.TelephonyManager;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Phonecheck = checkpermission();
       if(!Phonecheck)
         requestPhonePermission();

        TelephonyManager telephonyManager;
        telephonyManager = (TelephonyManager) getSystemService(Service.TELEPHONY_SERVICE);


        TextView pinfo = findViewById(R.id.textView);
    }
    public void requestPhonePermission()
    {

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);


    }

boolean Phonecheck;
    public boolean checkpermission()
    {
        Phonecheck = (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)&&
                (checkSelfPermission(Manifest.permission.READ_PHONE_NUMBERS)== PackageManager.PERMISSION_GRANTED);
                return Phonecheck;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantedResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantedResults);
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantedResults.length > 0 && grantedResults[0] == PackageManager.PERMISSION_GRANTED) {
// permission granted :) do stuff
                } else {
// permission denied :( don't do stuff
                }
                return;
            }
// check for other permissions
        }
    }

}