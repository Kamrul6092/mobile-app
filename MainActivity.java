package com.example.user.Eventship;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class MainActivity extends AppCompatActivity { private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAut;private FirebaseAuth.AuthStateListener authStateListener;  EditText Email;  EditText Password;

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(this);
        firebaseAut=FirebaseAuth.getInstance();
        Button SignIN = (Button) findViewById(R.id.SignIn);
        Button Register = (Button) findViewById(R.id.Register);
        Email = (EditText) findViewById(R.id.EmailPhone);
        Password = (EditText) findViewById(R.id.PassWord);








        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               // if (firebaseAuth.getCurrentUser()!=null){
                   // startActivity(new Intent(MainActivity.this,Nevigation.class));}

            }
        }
        ;


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, registers.class);
                startActivity(myIntent);



            }
        });




        SignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startsignin();

            }
        });









    }









    @Override
    protected void onStart() {
        super.onStart();
        firebaseAut.addAuthStateListener(authStateListener);
    }




    private void startsignin(){
        String email=Email.getText().toString();
        String password=Password.getText().toString();
        if (TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)){

            Toast.makeText(MainActivity.this,"Fields Are Empty", Toast.LENGTH_SHORT).show();
        }
        else{  progressDialog.setMessage("Secure Internet Connection Needed Please Wait...");

            progressDialog.show();
            firebaseAut.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){ progressDialog.dismiss();
                        startActivity(new Intent(MainActivity.this,Nevigation.class));
                    }
                }
            });}

  }}
