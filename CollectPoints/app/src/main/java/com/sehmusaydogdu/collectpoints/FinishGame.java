package com.sehmusaydogdu.collectpoints;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinishGame extends Activity {

    TextView txtResultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);

        txtResultView=(TextView)findViewById(R.id.txtResult) ;
        Intent intent=getIntent();
        txtResultView.setText(intent.getStringExtra("Message"));
    }

    public void onGameStart(View view) {
        Intent intent=new Intent(this,HomePage.class);
        startActivity(intent);
        finish();
    }

    public void onGameExit(View view) {
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
}
