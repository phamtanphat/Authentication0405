package com.ptp.phamtanphat.authentication0405;

import android.net.Uri;
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
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    EditText edtTk, edtMk;
    Button btnDangky, btnDangnhap, btnCapnhat,btnXacthuc;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMk = findViewById(R.id.edittextPassword);
        edtTk = findViewById(R.id.edittextUser);
        btnDangky = findViewById(R.id.buttonDangky);
        btnDangnhap = findViewById(R.id.buttonDangnhap);
        btnCapnhat = findViewById(R.id.buttonUpdate);
        btnXacthuc = findViewById(R.id.buttonXacthuc);

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
                                    Log.d("BBB", task.getException().toString());
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
        btnCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null) {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName("Pham Tan Phat")
                            .setPhotoUri(Uri.parse("https://pbs.twimg.com/profile_images/875443327835025408/ZvmtaSXW_400x400.jpg"))
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(MainActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

        btnXacthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseUser user =  FirebaseAuth.getInstance().getCurrentUser();

                if (user != null){
                    user.sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(MainActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(MainActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }


            }
        });
    }

//    @Override
//    protected void onStart() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        Log.d("BBB",user.getPhotoUrl() + "");
//        super.onStart();
//    }
}
