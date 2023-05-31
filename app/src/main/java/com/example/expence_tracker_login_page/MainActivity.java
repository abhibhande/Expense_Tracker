package com.example.expence_tracker_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.MyDbHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        MyDbHandler db=new MyDbHandler(MainActivity.this);


        Button login=(Button)findViewById(R.id.Login);
        EditText pass=(EditText)findViewById(R.id.password);
        EditText username=(EditText)findViewById(R.id.username);
        Button createnewaccount=(Button)findViewById(R.id.create_new_);

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                EditText username=(EditText)findViewById(R.id.username);
                EditText password=(EditText)findViewById(R.id.password);

                String un = username.getText().toString();
                String pass = password.getText().toString();

//                Toast.makeText(getApplicationContext(), "Pass"+pass, Toast.LENGTH_SHORT).show();

                if(un.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "All Fields Are required", Toast.LENGTH_SHORT).show();
                }else {

                    if(db.searchUser(un,pass))
                    {
                        Toast.makeText(getApplicationContext(), "successfully login", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),Home_page.class);
                        finish();
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Username or Password NOT Found", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        createnewaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),create_account.class);
                finish();
                startActivity(i);
            }
        });






//                String s=db.addUser("abhi","abhi123","pass");
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();


//                String records="",error="Errors:";
//                try {
//                    Class.forName("com.mysql.jdbc.Driver");
//
//                    Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.43.101:3306/android?useSSL=false&allowPublicKeyRetrieval=true","andro","andro");
//                    Statement statement=connection.createStatement();
//
//                    ResultSet rs=statement.executeQuery("select * from test");
//
//                    while (rs.next())
//                    {
//                        records+=rs.getString(1)+" "+rs.getString(2)+"\n";
//                    }
//
//                } catch (Exception e) {
//                    error=e.toString();
//                }


//                Toast.makeText(getApplicationContext(), "record:"+records, Toast.LENGTH_SHORT).show();

//                if(error != "")
//                {
//                    Toast.makeText(getApplicationContext(), "Error:"+error, Toast.LENGTH_SHORT).show();
//
//                    Log.d("Error:",error);
//                }

//                new Task.execute();

    }



//    class Task extends AsyncTask<Void ,Void ,Void>{
//        String records="",error="";
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//
//                Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.43.101:3306/android","andro","andro");
//                Statement statement=connection.createStatement();
//
//                ResultSet rs=statement.executeQuery("select * from test");
//
//                while (rs.next())
//                {
//                    records+=rs.getString(1)+" "+rs.getString(2)+"\n";
//                }
//
//            } catch (Exception e) {
//                error=e.toString();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//
//            Toast.makeText(getApplicationContext(),records,Toast.LENGTH_LONG);
//
//            if(error != "")
//            {
//                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG);
//                super.onPostExecute(aVoid);
//            }
//
//            super.onPostExecute(aVoid);
//        }
//    }
}