package com.example.myfirstapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Sign_up extends AppCompatActivity {

    Context context;

    ImageView img1;
    TextView tv1;
    EditText edtxt1;
    EditText edtxt2;
    EditText edtxt3;
    EditText edtxt4;
    CheckBox chkbox;
    CardView cardView;
    TextView btn1;
    TextView tv2;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    TextView tv3;
    TextView btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_up);

        context = Sign_up.this;

        img1 = findViewById(R.id.signImg);
        tv1 = findViewById(R.id.txtView1);
        edtxt1 = findViewById(R.id.edittext1);
        edtxt2 = findViewById(R.id.edittext2);
        edtxt3 = findViewById(R.id.edittext3);
        edtxt4 = findViewById(R.id.edittext4);
        chkbox = findViewById(R.id.checkbox);
        cardView = findViewById(R.id.card);
        btn1 = findViewById(R.id.btn1);
        tv2 = findViewById(R.id.txtView2);
        img2 = findViewById(R.id.googleImg);
        img3 = findViewById(R.id.facebookImg);
        img4 = findViewById(R.id.appleImg);
        tv3 = findViewById(R.id.txtView3);
        btn2 = findViewById(R.id.btn2);

        signIn();

        signUp();

    }

    // Method
    private void signUp() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = edtxt1.getText().toString();
                String userEmail = edtxt2.getText().toString();
                String userPassword = edtxt3.getText().toString();
                String confirmPassword = edtxt4.getText().toString();

                if(userName.isEmpty()){
                    edtxt1.setError("User Name should not be empty");
                }
                else if(userEmail.isEmpty()){
                    edtxt2.setError("Email should not be empty");
                }
                else if(userPassword.isEmpty()){
                    edtxt3.setError("Password should not be empty");
                }
                else if(confirmPassword.isEmpty()){
                    edtxt4.setError("Confirm Password should not be empty");
                }else{
                    Shared.getInstance(context).saveStringData(Shared.USER_NAME, userName);
                    Shared.getInstance(context).saveStringData(Shared.USER_EMAIL, userEmail);
                    Shared.getInstance(context).saveStringData(Shared.PASSWORD, userPassword);
                    Shared.getInstance(context).saveStringData(Shared.CONFIRM_PASSWORD, confirmPassword);

                    Intent intent = new Intent(Sign_up.this, Sign_in.class);
                    startActivity(intent);
                    finish();

                }
            }
        });

    }

    // Method
    private void signIn() {

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_up.this, Sign_in.class);
                startActivity(intent);
            }
        });
    }
}




