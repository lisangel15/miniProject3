package com.example.teamscorecounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShareActivity extends AppCompatActivity {
    private Button buttonMakeCall, buttonSendMsg, buttonSearch;
    private String callNum, sendNum, winningTeam;
    private EditText etPhoneNum;
    private int wonBy;

    public static final int REQUEST_CALL_PHONE = 1;
    public static final int REQUEST_MSG_PHONE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
                                                                  
        etPhoneNum = (EditText)findViewById(R.id.editText_getPhoneNum);
        buttonMakeCall = (Button)findViewById(R.id.button_makeCall);
        buttonSendMsg = (Button)findViewById(R.id.button_sendMsg);
        buttonSearch = (Button)findViewById(R.id.button_searchBB);

        Intent shareIntent = getIntent();                                  
        int wonBy = shareIntent.getIntExtra(WinnerActivity.KEY_WON_BY, 0);
        String winningTeam = shareIntent.getStringExtra(WinnerActivity.KEY_WINNING_TEAM);



        buttonMakeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               makeAcall();
            }
        });

        buttonSendMsg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendMessage();
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                nearbyBasketball();
            }
        });
    }


     private void makeAcall() {
         callNum = "tel:" + etPhoneNum.getText().toString();
         Intent callIntent = new Intent(Intent.ACTION_CALL);                           
         callIntent.setData(Uri.parse(callNum));                                          
         
         if (callIntent.resolveActivity(getPackageManager()) != null) {
             if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                 return;
             }

             else{
                 startActivity(callIntent);
             }
         }
    }



    private void sendMessage() {
        Log.d("shareActivity", "inside sendMessage method");
        sendNum = "sms:" + etPhoneNum.getText().toString();
       String message = winningTeam + " won by: " + wonBy;
                                                                                           
        Log.d("shareActivity", "will send message " + message);
        Intent messageIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(sendNum));
        messageIntent.putExtra("sms_body", message);
        startActivity(messageIntent);
        
        if (messageIntent.resolveActivity(getPackageManager()) != null);{
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
               return;
            }

            else{
                startActivity(messageIntent);
            }
        }

    }

    private void nearbyBasketball() {
        Uri search = Uri.parse("geo:0,0?q=basketball near me");
        Intent searchIntent = new Intent(Intent.ACTION_VIEW, search);
        startActivity(searchIntent);
    }

}
