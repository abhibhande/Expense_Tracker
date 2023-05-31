package com.example.expence_tracker_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import data.MyDbHandler;

public class add_category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        MyDbHandler db=new MyDbHandler(this);

        TextView welcome=(TextView) findViewById(R.id.welcome_abh);
        welcome.setText(db.getWelcomeText());
        welcome.setTextSize(20);

        TableRow tbrow0 = new TableRow(this);
        Button add=(Button)findViewById(R.id.Add);
        EditText category=(EditText)findViewById(R.id.category);



        TableRow.LayoutParams textViewParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1.0f);
        TableLayout tableLayout = (TableLayout)findViewById(R.id.table1);

        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        tbrow0.setLayoutParams(tableRowParams);


        TableLayout table=(TableLayout) findViewById(R.id.table1);


        tbrow0.getMinimumWidth();
        TextView tv0 = new TextView(this);
        tv0.setText("SR.NO.");
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


        tv0.setGravity(Gravity.CENTER);
        tv1.setGravity(Gravity.CENTER);

        table.addView(tbrow0);

        List<String> data=db.getCategory();
//        Toast.makeText(getApplicationContext(),"data"+data.get(0),Toast.LENGTH_SHORT);
//        List<String> data=new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(String.valueOf(i+1));
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            t1v.setLayoutParams(textViewParam);
            Toast.makeText(getApplicationContext(),"data"+data.get(0),Toast.LENGTH_SHORT);

            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(data.get(i));
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            t2v.setLayoutParams(textViewParam);


            t1v.setTextAppearance(this,  R.style.createnew);
            t1v.setPadding(30,5,5,2);
            t2v.setTextAppearance(this,  R.style.createnew);
            t2v.setPadding(10,5,5,2);


            table.addView(tbrow);
        }


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cat=category.getText().toString();
                if(cat.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Enter Category", Toast.LENGTH_SHORT).show();
                }
                else{

                    db.addCategory(cat);
                    Toast.makeText(getApplicationContext(), "New Category Added", Toast.LENGTH_SHORT).show();
                    recreate();
                    category.setText("");

                }
            }
        });
    }
}