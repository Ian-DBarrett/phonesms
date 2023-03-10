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
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity  {

    TelephonyManager telephonyManager;
    private static final int REQUEST_CODE = 1;

    MyPhoneStateListener myPhoneStateListener;
    CallStateChanged callStateChanged;
     int msigstrength = 0;
     int idil = 0;
    class MyPhoneStateListener extends PhoneStateListener {

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            msigstrength = signalStrength.getGsmSignalStrength();
            msigstrength = (2 * msigstrength) - 113; // -> dBm
            TextView text = findViewById(R.id.textView);
            text.setText(""+msigstrength);
        }
    }
    TextView textView;
    private static final int PERMISSION_REQUEST_CODE = 100;

    class CallStateChanged extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            super.onCallStateChanged(state, phoneNumber);

            //idil = callStateChanged.onCallStateChanged();

            getIntent().getStringExtra(String.valueOf((TelephonyManager.CALL_STATE_IDLE)));
            TextView text = findViewById(R.id.textView2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //int check1 = checkSelfPermission(com.example.phonesms.Manifest.permission.READ_CONTACTS);




        myPhoneStateListener = new MyPhoneStateListener();
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);



        callStateChanged = new CallStateChanged();
        telephonyManager = (TelephonyManager)  getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(callStateChanged,PhoneStateListener.LISTEN_SERVICE_STATE);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // onSignalStrengthChanged(str);

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //String opId = telephonyManager.getNetworkOperator();

       // PhoneStateListener.LISTEN_SIGNAL_STRENGTHS = telephonyManager.getPhoneType(PhoneStateListener.LISTEN_DATA_CONNECTION_STATE)

    }

    public void onSignalStrengthChanged(SignalStrength str) {


    }
}