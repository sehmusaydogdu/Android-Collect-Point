package com.sehmusaydogdu.collectpoints;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.Random;


public class GamePage extends Activity {

    private ProgressBar progressBar;

    private Handler handler = new Handler();
    private Random rnd=new Random();
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12;
    private TextView txtSure,txtHighSkor,txtSkor;
    private int progressStatus =0,btnprogressStatus=-1;
    private int txtSkorInt=0,txtHighSkorInt=0;

    SharedPreferences pref;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(1);

    }

    /*Nesneleri yakalamak için kullanılan metottur*/
    private void CaptureObjects(){
        btn1 =(Button)findViewById(R.id.btn1);
        btn2 =(Button)findViewById(R.id.btn2);
        btn3 =(Button)findViewById(R.id.btn3);
        btn4 =(Button)findViewById(R.id.btn4);
        btn5 =(Button)findViewById(R.id.btn5);
        btn6 =(Button)findViewById(R.id.btn6);
        btn7 =(Button)findViewById(R.id.btn7);
        btn8 =(Button)findViewById(R.id.btn8);
        btn9 =(Button)findViewById(R.id.btn9);
        btn10=(Button)findViewById(R.id.btn10);
        btn11=(Button)findViewById(R.id.btn11);
        btn12=(Button)findViewById(R.id.btn12);

        txtHighSkor=(TextView)findViewById(R.id.txtHighSkor);
        txtSkor=(TextView)findViewById(R.id.txtSkor);
        txtSure = (TextView) findViewById(R.id.txtSure);

        txtSkorInt=Integer.parseInt(txtSkor.getText().toString());
        txtHighSkorInt=Integer.parseInt(txtHighSkor.getText().toString());

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        pref=getSharedPreferences("ScorRecord",MODE_PRIVATE);
    }

    /*Oyunun süresini gösteren metot. Progressbar' ın değerini gösterir.*/
    private void timeStart(){
        // Start long running operation in a background thread
       new Thread(new Runnable() {

            public void run() {
                   while (progressStatus < 60) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            txtSure.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 500 milliseconds.
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /*Oyunu başlatan metot. Bu metotta ekranda 3 saniyede bir rastgele olarak butonları ve değerlerini gösteren metottur.*/
    private void btnStart(){
        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (btnprogressStatus<60){
                    btnprogressStatus++;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {

                            /* Her 3 sn bir buttonlarının yerini,değerini değiştiriyor. (GameStart())*/
                            if(btnprogressStatus%3==0)
                               GameStart();
                            /*Süreyi kontol ediyor.*/
                            if (btnprogressStatus==60)
                                GameStop();

                        }
                    });
                    try {
                        // Sleep for 500 milliseconds.
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }


        }).start();
    }

    private void GameStop(){

        String message;
        if (txtSkorInt>=txtHighSkorInt)
        {
            message="Congratulations. Your new score : "+String.valueOf(txtSkorInt);
            txtHighSkorInt=txtSkorInt;
            txtHighSkor.setText(String.valueOf(txtHighSkorInt));

            SharedPreferences.Editor prefEditor=pref.edit();
            prefEditor.putString("HighSkor",String.valueOf(txtHighSkorInt));
            prefEditor.apply();
        }

        else{
            message="Sorry. You did not pass the score : "+String.valueOf(txtSkorInt);
        }
        Intent intent=new Intent(this,FinishGame.class);
        intent.putExtra("Message",message);
        startActivity(intent);
        finish();

       /* txtSkorInt=0;
        txtSkor.setText("0");

        AlertDialog alertMessage = new AlertDialog.Builder(this).create();
        alertMessage.setTitle("Time Out");
        alertMessage.setMessage(message);
        alertMessage.setButton(AlertDialog.BUTTON_POSITIVE,"Try Again", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                progressStatus=0;
                btnprogressStatus=-1;
                timeStart();
                btnStart();
            }
        });
        alertMessage.setButton(AlertDialog.BUTTON_NEGATIVE,"Exit", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        alertMessage.show();*/
    }


    /*Ekrandaki bütün butonları gizleyen metottur*/
    private void btnInVisible(){
        btn1.setVisibility(View.INVISIBLE);
        btn2.setVisibility(View.INVISIBLE);
        btn3.setVisibility(View.INVISIBLE);
        btn4.setVisibility(View.INVISIBLE);
        btn5.setVisibility(View.INVISIBLE);
        btn6.setVisibility(View.INVISIBLE);
        btn7.setVisibility(View.INVISIBLE);
        btn8.setVisibility(View.INVISIBLE);
        btn9.setVisibility(View.INVISIBLE);
        btn10.setVisibility(View.INVISIBLE);
        btn11.setVisibility(View.INVISIBLE);
        btn12.setVisibility(View.INVISIBLE);
    }

    /* Rastgele olarak ekranda butonları gösteren metottur*/
    private void btnVisible(int btnItem,int value){
        switch (btnItem){
            case 1: btn1.setVisibility(View.VISIBLE);btn1.setText(String.valueOf(value)); break;
            case 2: btn2.setVisibility(View.VISIBLE);btn2.setText(String.valueOf(value));break;
            case 3: btn3.setVisibility(View.VISIBLE);btn3.setText(String.valueOf(value));break;
            case 4: btn4.setVisibility(View.VISIBLE);btn4.setText(String.valueOf(value));break;
            case 5: btn5.setVisibility(View.VISIBLE);btn5.setText(String.valueOf(value));break;
            case 6: btn6.setVisibility(View.VISIBLE);btn6.setText(String.valueOf(value));break;
            case 7: btn7.setVisibility(View.VISIBLE);btn7.setText(String.valueOf(value));break;
            case 8: btn8.setVisibility(View.VISIBLE);btn8.setText(String.valueOf(value));break;
            case 9: btn9.setVisibility(View.VISIBLE);btn9.setText(String.valueOf(value));break;
            case 10:btn10.setVisibility(View.VISIBLE);btn10.setText(String.valueOf(value));break;
            case 11:btn11.setVisibility(View.VISIBLE);btn11.setText(String.valueOf(value));break;
            case 12:btn12.setVisibility(View.VISIBLE);btn12.setText(String.valueOf(value));break;
        }
    }  //Rastgele butonları ekranda göster

    /*Ekranda rastgele olarak buttonların gösterilmesini sağlar.*/
    private void btnRandomVisible(){

           for (int i=0;i<4;i++){
                int btnItem=1+rnd.nextInt(12);  //Hangi butonlar ekranda gösterilsin
                int value=(1+rnd.nextInt(10));  //Butonlara değer atama yapılıyor.
                int sign=(1+rnd.nextInt(2));   //Sayının işareti belirleniyor
                value=(sign==1)?value*10:value*(-10); // 1 : Positive 2:Negative
                btnVisible(btnItem,value);  //Ekranda butonu gösteriyor.
           }
    }

    private void GameStart(){
            txtHighSkor.setText(pref.getString("HighSkor", String.valueOf(txtHighSkorInt)));
            txtHighSkorInt=Integer.parseInt(pref.getString("HighSkor","0"));
            btnInVisible();
            btnRandomVisible();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        CaptureObjects(); //Activity deki tüm nesneleri yakalayan fonksiyon.
        timeStart();  // Normal Süreyi Başlatıyor .. Progressbardaki süreyi gösteriyor.
        btnStart();  //Oyunu Başlatan fonksiyondur
    }

    public void onClicked(View view) {
        Button btn=(Button)view;
        txtSkorInt+=Integer.parseInt(btn.getText().toString());
        txtSkor.setText(String.valueOf(txtSkorInt));
        btn.setVisibility(View.INVISIBLE);
    }
}
