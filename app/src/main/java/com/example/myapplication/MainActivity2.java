package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity2 extends AppCompatActivity {
    private ImageButton income;
    private ImageButton expence;
    private ImageButton saving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        income = findViewById(R.id.imageButton3);
        expence = findViewById(R.id.imageButton4);
        saving = findViewById(R.id.imageButton5);

        income.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                    Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                    startActivity(intent);


            }

        });
        expence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(MainActivity2.this,ExpenseActivity.class);
                startActivity(intent);


            }

        });
        saving.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(MainActivity2.this,SavingActivity.class);
                startActivity(intent);


            }

        });
    }
}