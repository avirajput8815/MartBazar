package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class Sign_in extends AppCompatActivity {

    Context context;
    SharedPreferences sharedPreferences;

    ImageView img;
    TextView txt1;
    EditText userName;
    EditText userPassword;
    CheckBox checkBox;
    TextView txt2;
    CardView crdview;
    TextView button1;
    TextView txt4;
    ImageView imgview1;
    ImageView imgview2;
    ImageView imgview3;
    TextView txt5;
    TextView button2;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.sign_in);

            // this means (current class ka reference(context))
            context = Sign_in.this;

            img = findViewById(R.id.signImg);
            txt1 = findViewById(R.id.tv2);
            userName = findViewById(R.id.edittext1);
            userPassword = findViewById(R.id.edittext2);
            checkBox = findViewById(R.id.checkbx);
            txt2 = findViewById(R.id.tv3);
            crdview = findViewById(R.id.card);
            button1 = findViewById(R.id.btn1);
            txt4 = findViewById(R.id.tv4);
            imgview1 = findViewById(R.id.img2);
            imgview2 = findViewById(R.id.img3);
            imgview3 = findViewById(R.id.img4);
            txt5 = findViewById(R.id.tv5);
            button2 = findViewById(R.id.btn2);

            // method Calling
            signIn();


            // method Calling
            signUp();

        }

    // Method
    private void signIn(){

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = userName.getText().toString();
                String password = userPassword.getText().toString();

                if(email.isEmpty()){
                    userName.setError("User Name should not be empty");
                }
                else if(password.isEmpty()){
                    userPassword.setError("User Password should not be empty");
                }else{

                    // get liya hai
                   String name = Shared.getInstance(context).getStringData(Shared.USER_NAME);
                   String userPasswd = Shared.getInstance(context).getStringData(Shared.PASSWORD);

                   // match kraya hai password
                   if(!name.equals(email)){
                       userName.setError("Email not correct");
                       return;
                   } else if (!userPasswd.equals(password)) {
                       userPassword.setError("Password not correct");
                       return;
                   }


                    Intent intent = new Intent(Sign_in.this, Dashboard_Activity.class);
                    startActivity(intent);

                }
            }
        });
    }

    // Method
    private void signUp(){

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sign_in.this, Sign_up.class);
                startActivity(intent);
            }
        });

    }
}