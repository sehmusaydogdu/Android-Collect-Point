package com.sehmusaydogdu.collectpoints;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
//Login Page
    EditText editUserName,editPassword;
    SharedPreferences pref;
    private void init(){
        editUserName=(EditText)findViewById(R.id.txtKullaniciAdi);
        editPassword=(EditText)findViewById(R.id.txtKullaniciSifre);
        pref=getSharedPreferences("LoginRecord",MODE_PRIVATE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();

        String username=pref.getString("USERNAME","");

        if(username!=""){
            Intent intent=new Intent(this,HomePage.class);
            startActivity(intent);
            finish();
        }
    }

    public void onLogin(View view) {

        String username=editUserName.getText().toString();
        String password=editPassword.getText().toString();
        if (username.equals("") || password.equals("")) {
            Toast.makeText(this, "Enter the user name and password correctly.", Toast.LENGTH_SHORT).show();
        } else{
            SharedPreferences.Editor prefEditor=pref.edit();
            prefEditor.putString("USERNAME",editUserName.getText().toString());
            prefEditor.putString("PASSWORD",editPassword.getText().toString());
            prefEditor.apply();

            Intent intent=new Intent(this,HomePage.class);
            startActivity(intent);
            finish();
        }
        
        
    }

    public void onRegister(View view) {
        Intent intent=new Intent(this,RegisterPage.class);
        startActivity(intent);
        finish();
    }
}
