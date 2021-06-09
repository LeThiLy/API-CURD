package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity {

    EditText ptEmail, ptPass;
    Button btnLogin;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        ptEmail = findViewById(R.id.ptEmail);
        ptPass = findViewById(R.id.ptPass);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ptEmail.getText().toString();
                String password = ptPass.getText().toString();
                if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password))
                    Toast.makeText(MainActivity.this, "Nhập đầy đủ các ô!", Toast.LENGTH_SHORT).show();
                else
                    DangNhap(email,password);
            }
        });
    }

    private void DangNhap(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent=new Intent(MainActivity.this, Action.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
