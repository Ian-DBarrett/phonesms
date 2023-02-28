package com.example.phonesms;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;
import android.telephony.TelephonyManager;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    //new
    TextView textView;
    private static final int PERMISSION_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Phonecheck = checkpermission();
       if(!Phonecheck)
         requestPhonePermission();

        TelephonyManager telephonyManager;
        telephonyManager = (TelephonyManager) getSystemService(Service.TELEPHONY_SERVICE);


        TextView pinfo = findViewById(R.id.textView3);



      //new code
        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) !=
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE}, PERMISSION_REQUEST_CODE);
        } else {
            textView.setText("IMEI No. "+telephonyManager.getImei());
        }
    }




    public void requestPhonePermission()
    {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);

    }


boolean Phonecheck;
    public boolean checkpermission()
    {
        Phonecheck = (checkSelfPermission(READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)&&
                (checkSelfPermission(READ_PHONE_NUMBERS)== PackageManager.PERMISSION_GRANTED);

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