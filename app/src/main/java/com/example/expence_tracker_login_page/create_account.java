package com.example.expence_tracker_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import data.MyDbHandler;

public class create_account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        MyDbHandler db=new MyDbHandler(this);



        Button createaccount=(Button) findViewById(R.id.createaccount);


        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name=(EditText)findViewById(R.id.name);
                EditText username=(EditText)findViewById(R.id.username);
                EditText password=(EditText)findViewById(R.id.password);
                EditText confirmpassword=(EditText)findViewById(R.id.confirm_password);

                String n=name.getText().toString();
                String un=username.getText().toString();
                String pass=password.getText().toString();
                String cpass=confirmpassword.getText().toString();



                if(n.isEmpty() ||un.isEmpty()|| pass.isEmpty()|| cpass.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "All Fields Are required", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(cpass)) {
                    Toast.makeText(getApplicationContext(), "Password and confirm Password not matched", Toast.LENGTH_SHORT).show();
                } else {
                    if (!db.userExists(un,getApplicationContext())) {

                        String s = db.addUser(un, n, pass);
//                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Account created", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        finish();
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "User Already Exist Change Username", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




    }
}