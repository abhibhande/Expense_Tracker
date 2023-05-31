package com.example.expence_tracker_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import data.MyDbHandler;

public class add_new_expences extends AppCompatActivity {


    AutoCompleteTextView autoCompleteTextView;
    String category="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expences);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        MyDbHandler db=new MyDbHandler(this);
        TextView welcome=(TextView) findViewById(R.id.welcome_abh);
        welcome.setText(db.getWelcomeText());
        welcome.setTextSize(20);

        List<String> list=db.getCategory();


        ArrayAdapter<String> adapterItems;
        autoCompleteTextView=findViewById(R.id.Select_Category);
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,list);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getItemAtPosition(i).toString();

                category=item;
//                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            }
        });



        EditText date1=(EditText)findViewById(R.id.date);

        EditText amt_=(EditText)findViewById(R.id.amount);


//        EditText category_=(EditText)findViewById(R.id.category);


        Button add_category=(Button) findViewById(R.id.add_category);
        Button add=(Button)findViewById(R.id.Add);

        ImageButton archive_book=(ImageButton)findViewById(R.id.archive_book);
        ImageButton home=(ImageButton)findViewById(R.id.home);


        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(add_new_expences.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                if((monthOfYear + 1)>9) {

                                    date1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                                else
                                {
                                    date1.setText(dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                                }

                            }
                        },
                        year, month, day);

                datePickerDialog.show();
            }

        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date=date1.getText().toString();
                String amt=amt_.getText().toString();
//                String category=category_.getText().toString();

                             if(date.isEmpty() || amt.isEmpty() || category.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "All Fields Are required", Toast.LENGTH_SHORT).show();
                }
                else {
                        db.addExpence(date,amt,category);
                    Toast.makeText(getApplicationContext(), "Data Added", Toast.LENGTH_SHORT).show();
                    date1.setText("");
                    amt_.setText("");
//                    category_.setText("");
                }
            }
        });

        add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),add_category.class);
                startActivity(i);
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Home_page.class);
                finish();
                startActivity(i);
            }
        });



        archive_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),this_month_expence.class);
                finish();
                startActivity(i);
            }
        });



    }


    }
