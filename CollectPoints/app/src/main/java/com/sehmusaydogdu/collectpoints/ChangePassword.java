package com.sehmusaydogdu.collectpoints;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends Activity {

    EditText editUserName,editOldPassword,editNewPassword;
    SharedPreferences pref;

    private void init(){
        editUserName=(EditText)findViewById(R.id.txtUserName);
        editOldPassword=(EditText)findViewById(R.id.txtOldPassword);
        editNewPassword=(EditText)findViewById(R.id.txtNewPassword);
        pref=getSharedPreferences("LoginRecord",MODE_PRIVATE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();
    }

    public void onCancel(View view) {
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
        finish();
    }

    public void onSaveChange(View view) {
        String username=editUserName.getText().toString();
        String oldpassword=editOldPassword.getText().toString();
        String newpassword=editNewPassword.getText().toString();

        if (username.equals("") || oldpassword.equals("") || newpassword.equals("")) {
            Toast.makeText(this, "Enter the user name and password correctly.", Toast.LENGTH_SHORT).show();
        } else{

            if (oldpassword.equals(pref.getString("PASSWORD",""))){
                SharedPreferences.Editor prefEditor=pref.edit();
                prefEditor.putString("USERNAME",editUserName.getText().toString());
                prefEditor.putString("PASSWORD",editNewPassword.getText().toString());
                prefEditor.apply();

                Intent intent=new Intent(this,HomePage.class);
                startActivity(intent);
                finish();
            }
            else
            {
               Toast.makeText(this,"Incorrect information entry",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
