package com.example.cloudtasks;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthlistener;

    private EditText ETemail;
    private EditText ETpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);
        mAuth = FirebaseAuth.getInstance();

        mAuthlistener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user =firebaseAuth.getCurrentUser();
                if(user!=null)
                {

                }else
                {

                }
            }
        };

        ETemail=(EditText) findViewById(R.id.et_email);
        ETpassword=(EditText) findViewById(R.id.et_password);

        findViewById(R.id.btn_sign_in).setOnClickListener(this);
        findViewById(R.id.btn_registration).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_sign_in)
        {
            signin(ETemail.getText().toString(), ETpassword.getText().toString());
        } else if (view.getId()==R.id.btn_registration) {
            registration(ETemail.getText().toString(), ETpassword.getText().toString());
        }
    }

    public void signin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(EmailPasswordActivity.this, "Авторизация выполнено успешно!!!", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(EmailPasswordActivity.this, "Авторизация провалено!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void registration(String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(EmailPasswordActivity.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(EmailPasswordActivity.this, "Регистрация провалено!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}