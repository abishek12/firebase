package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView actSignup;
    EditText logUsername,logPassword;
    String strLogUsername, strLogPassword;
    Button buttonLogin;

    FirebaseAuth lAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lAuth = FirebaseAuth.getInstance();

        actSignup = findViewById(R.id.actSignup);
        logUsername = findViewById(R.id.username);
        logPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btnLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strLogUsername = logUsername.getText().toString().trim();
                strLogPassword = logPassword.getText().toString().trim();

                if (TextUtils.isEmpty(strLogUsername)){
                    logUsername.setError("Required Field");
                    return;
                }
                if (TextUtils.isEmpty(strLogPassword)){
                    logUsername.setError("Required Field");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(strLogUsername).matches()){
                    logUsername.setError("Enter valid email address");
                    return;
                }
                lAuth.signInWithEmailAndPassword(strLogUsername,strLogPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"unsuccessfull",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        actSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actSignup = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(actSignup);
            }
        });

    }
}
