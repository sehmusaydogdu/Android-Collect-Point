package com.sehmusaydogdu.collectpoints;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class HomePage extends Activity {


    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pref=getSharedPreferences("LoginRecord",MODE_PRIVATE);

        HomePage.this.setTitle(pref.getString("USERNAME","Hata"));

    }

    public void onbtnStart(View view) {

        Intent intent=new Intent(getApplicationContext(),GamePage.class);
        startActivity(intent);
        finish();
    }

    public void onbtnExit(View view) {

        AlertDialog alertMessage = new AlertDialog.Builder(this).create();
        alertMessage.setTitle("Exit");
        alertMessage.setMessage("Are you sure to check out?");

        alertMessage.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });

        alertMessage.setButton(AlertDialog.BUTTON_NEGATIVE,"CANCEL", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertMessage.show();
    }

    public void onbtnHelp(View view) {

        AlertDialog alertMessage = new AlertDialog.Builder(this).create();
        alertMessage.setTitle("Help");
        alertMessage.setMessage("Random buttons will be generated." +
                                " When you click on each button, you earn as much points as the button.");

        alertMessage.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertMessage.show();
    }

    public void onSignOut(View view) {
        SharedPreferences.Editor prefEditor=pref.edit();
        prefEditor.putString("USERNAME","");
        prefEditor.putString("PASSWORD","");
        prefEditor.apply();

        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
        finish();
    }

    public void onChange(View view) {
        Intent intent=new Intent(this,ChangePassword.class);
        startActivity(intent);
        finish();
    }
}
