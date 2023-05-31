package com.example.expence_tracker_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import data.MyDbHandler;

public class this_month_expence extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_month_expence);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        MyDbHandler db=new MyDbHandler(this);
        TextView welcome=(TextView) findViewById(R.id.welcome_abh);
        welcome.setText(db.getWelcomeText());
        welcome.setTextSize(20);

        int total=0;

        TextView t=(TextView)findViewById(R.id.totalamt);

        ImageButton home=(ImageButton)findViewById(R.id.home);
        ImageButton new_Entry=(ImageButton)findViewById(R.id.new_Entry);

        TableRow tbrow0 = new TableRow(this);
        TableRow.LayoutParams textViewParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1.0f);
        TableLayout tableLayout = (TableLayout)findViewById(R.id.table);

        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        tbrow0.setLayoutParams(tableRowParams);


        TableLayout table=(TableLayout) findViewById(R.id.table);


        tbrow0.getMinimumWidth();
        TextView tv0 = new TextView(this);
        tv0.setText("Date");
        tv0.setPadding(10,5,5,2);
        tv0.setTextAppearance(this, R.style.createnew);
        tv0.setTextColor(Color.BLACK);

        tv0.setLayoutParams(textViewParam);
        tbrow0.addView(tv0);

        TextView tv1 = new TextView(this);
        tv1.setText("Category");
        tv1.setPadding(10,5,5,2);
        tv1.setTextAppearance(this,  R.style.createnew);
        tv1.setTextColor(Color.BLACK);
        tv1.setLayoutParams(textViewParam);

        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText("Amount");
        tv2.setPadding(10,5,5,2);
        tv2.setTextAppearance(this,  R.style.createnew);
        tv2.setTextColor(Color.BLACK);
        tv2.setLayoutParams(textViewParam);

        tv0.setGravity(Gravity.CENTER);
        tv1.setGravity(Gravity.CENTER);
        tv2.setGravity(Gravity.CENTER);


        tbrow0.addView(tv2);
        table.addView(tbrow0);


        List<String> data=db.getExpences();

        for (int i = 0; i < data.size(); i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(data.get(i));
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            t1v.setLayoutParams(textViewParam);

            i++;

            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(data.get(i));
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            t2v.setLayoutParams(textViewParam);

            i++;

            TextView t3v = new TextView(this);
            t3v.setText(data.get(i));
            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.CENTER);
            t3v.setLayoutParams(textViewParam);

            total+=Integer.parseInt(data.get(i));

            t.setText(String.valueOf(total)+" -/");
            t.setPadding(26,0,0,0);
            t.setTextSize(23);

            t1v.setTextAppearance(this,  R.style.createnew);
            t1v.setPadding(30,5,5,2);
            t2v.setTextAppearance(this,  R.style.createnew);
            t2v.setPadding(10,5,5,2);
            t3v.setTextAppearance(this,  R.style.createnew);
            t3v.setPadding(10,5,5,2);

            tbrow.addView(t3v);

            table.addView(tbrow);
        }



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Home_page.class);
                finish();
                startActivity(i);
            }
        });


        new_Entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),add_new_expences.class);
                finish();
                startActivity(i);
            }
        });

    }


}