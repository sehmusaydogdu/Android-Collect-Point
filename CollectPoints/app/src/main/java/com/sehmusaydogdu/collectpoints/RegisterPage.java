package com.sehmusaydogdu.collectpoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends Activity {


    EditText editNewUser,editNewPass,editAgainPass;

    private void init(){
        editNewUser=(EditText)findViewById(R.id.txtNewUser);
        editNewPass=(EditText)findViewById(R.id.txtNewPass);
        editAgainPass=(EditText)findViewById(R.id.txtAgainPass);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        init();
    }

    public void onCancel(View view) {
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
        finish();
    }

    public void onSaveRegister(View view) {

        String user=editNewUser.getText().toString();
        String password=editNewPass.getText().toString();
        String againPass=editAgainPass.getText().toString();

        if(user.equals("") || password.equals("")||againPass.equals("")){
            Toast.makeText(this, "Fill in empty spaces.", Toast.LENGTH_SHORT).show();
        }
        else {
            if(password.equals(againPass)){
                Toast.makeText(this, "Successfull Record !!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,Login.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(this, "Passwords are different", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
