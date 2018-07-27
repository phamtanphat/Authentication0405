package com.ptp.phamtanphat.authentication0405;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText edtTk, edtMk;
    Button btnDangky,btnDangnhap;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMk = findViewById(R.id.edittextPassword);
        edtTk = findViewById(R.id.edittextUser);
        btnDangky = findViewById(R.id.buttonDangky);
        btnDangnhap = findViewById(R.id.buttonDangnhap);

        mAuth = FirebaseAuth.getInstance();

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtTk.getText().toString();
                String password = edtMk.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d("BBB",task.getException().toString());
                                }
                            }
                        });
            }
        });
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtTk.getText().toString();
                String password = edtMk.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
