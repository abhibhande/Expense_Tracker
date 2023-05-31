package com.example.expence_tracker_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import data.MyDbHandler;

public class Home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        MyDbHandler db=new MyDbHandler(this);

        TextView dateTimeDisplay;
         Calendar calendar;
         SimpleDateFormat dateFormat;
         String date;

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        calendar = Calendar.getInstance();
        date = dateFormat.format(calendar.getTime());


        int year = calendar.get(Calendar.YEAR);
        int month_ = calendar.get(Calendar.MONTH);
        int day = 1;
        calendar.set(year, month_, day);
        int numOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date first= calendar.getTime();

        calendar.add(Calendar.MONTH,-1);
        Date pre_month_first=calendar.getTime();
        calendar.add(Calendar.MONTH,-6);
        Date pre_six_first=calendar.getTime();


        calendar.set(year, month_, day);

//        Toast.makeText(getApplicationContext(), ""+pre_six_first, Toast.LENGTH_SHORT).show();

        calendar.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth-1);

        Date last=calendar.getTime();

        calendar.add(Calendar.MONTH,-1);
        Date pre_month_last=calendar.getTime();

        calendar.add(Calendar.MONTH,-6);
        Date pre_six_last=calendar.getTime();


//        Toast.makeText(getApplicationContext(), "pre last Date:"+pre_month_last, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();

        TextView welcome=(TextView) findViewById(R.id.welcome_abh);
        welcome.setText(db.getWelcomeText());
        welcome.setTextSize(20);

        TextView today=(TextView) findViewById(R.id.todays_spending);
        TextView month=(TextView) findViewById(R.id.month_spending);
        TextView pre_month=(TextView)findViewById(R.id.pre_month);
        TextView six_month=(TextView) findViewById(R.id.six_month);

        List<String> data=db.getExpences();
        int t1=0;
        int t2=0;
        int t3=0;
        int t4=0;
//        Toast.makeText(this, data.get(0), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < data.size();i++) {
            if(data.get(i).equals(date))
            {
                t1+=Integer.parseInt(data.get(i+2));
            }

            try {
                if(first.before((new SimpleDateFormat("dd/MM/yyyy")).parse(data.get(i))) ) //&& last.after((new SimpleDateFormat("dd/MM/yyyy")).parse(data.get(i)))
                {
                    t2 += Integer.parseInt(data.get(i+2));
                }
                if(pre_month_first.before((new SimpleDateFormat("dd/MM/yyyy")).parse(data.get(i))) && pre_month_last.after((new SimpleDateFormat("dd/MM/yyyy")).parse(data.get(i))))
                {
                    t3+=Integer.parseInt(data.get(i+2));
                }

                if(pre_six_first.before((new SimpleDateFormat("dd/MM/yyyy")).parse(data.get(i)))) //&& pre_six_last.after((new SimpleDateFormat("dd/MM/yyyy")).parse(data.get(i)))
                {
                    t4 += Integer.parseInt(data.get(i+2));
                }

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            i=i+2;
        }

        today.setText(String.valueOf(t1));
        month.setText(String.valueOf(t2));
        pre_month.setText(String.valueOf(t3));
        six_month.setText(String.valueOf(t4));






        ImageButton new_Entry=(ImageButton)findViewById(R.id.new_Entry);
        ImageButton archive_book=(ImageButton)findViewById(R.id.archive_book);

        new_Entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),add_new_expences.class);
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