package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText regUsername, regEmail, regNumber, regPassword, regConfirmPassword;
    private Button buttonRegister;
    private String strEmail, strPassword, strConfirmPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        regUsername = findViewById(R.id.regUsername);
        regEmail = findViewById(R.id.regEmail);
        regNumber = findViewById(R.id.regNumber);
        regPassword = findViewById(R.id.regPassword);
        regConfirmPassword = findViewById(R.id.regConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail = regEmail.getText().toString().trim();
                strPassword = regPassword.getText().toString().trim();
                strConfirmPassword = regConfirmPassword.getText().toString().trim();

//                form validation

                if (TextUtils.isEmpty(strEmail)) {
                    regEmail.setError("Required field...");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
                    regEmail.setError("Enter valid email address");
                    return;
                }
                if (strPassword.length() <= 7 || strPassword.length() >= 16) {
                    regPassword.setError("Password should be less than 16 or greater than 7");
                    return;
                }
                if (TextUtils.isEmpty(strPassword)) {
                    regPassword.setError("Required field...");
                    return;
                }
                if (TextUtils.isEmpty(strConfirmPassword)) {
                    regConfirmPassword.setError("Required field...");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

}
