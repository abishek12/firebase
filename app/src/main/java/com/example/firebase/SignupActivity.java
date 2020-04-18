package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    EditText regUsername, regEmail, regNumber, regPassword, regConfirmPassword;
    Button buttonRegister;
    String strEmail,strPassword,strConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findId();
        registration();

    }

    private void findId(){
        regUsername = findViewById(R.id.regUsername);
        regEmail = findViewById(R.id.regEmail);
        regNumber = findViewById(R.id.regNumber);
        regPassword = findViewById(R.id.regPassword);
        regConfirmPassword = findViewById(R.id.regConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
    }

    private void registration(){
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail = regEmail.getText().toString().trim();
                strPassword = regPassword.getText().toString().trim();
                strConfirmPassword = regConfirmPassword.getText().toString().trim();

//                form validation

                if (TextUtils.isEmpty(strEmail)){
                    regEmail.setError("Required field...");
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()){
                    regEmail.setError("Enter valid email address");
                }
                if (strPassword.length()<=7 || strPassword.length() >=16){
                    regPassword.setError("Password should be less than 16 or greater than 7");
                }
                if (TextUtils.isEmpty(strPassword)){
                    regPassword.setError("Required field...");
                }
                if (TextUtils.isEmpty(strConfirmPassword)){
                    regConfirmPassword.setError("Required field...");
                }
                if (strPassword != strConfirmPassword){
                    regPassword.setError("Password did not matched");
                    regConfirmPassword.setText("");
                }
            }
        });
    }
}
