package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class SavingActivity extends AppCompatActivity {
    ArrayList<SavingDetails> arrayList;
   ListView listView;
    ProductAdopter adapter;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saving_layout);
        listView = findViewById(R.id.listView);
        SavingDetailsDbHelper dbHelper = new SavingDetailsDbHelper(this);

//        arrayList = new ArrayList<>();
        dbHelper.add();
     arrayList=   dbHelper.get();

        adapter = new ProductAdopter();
       listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    class ProductAdopter extends ArrayAdapter
    {
        public ProductAdopter()
        {
            super(SavingActivity.this, R.layout.text_view_resource, arrayList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater= getLayoutInflater();
            View productItemView = layoutInflater.inflate(R.layout.text_view_resource, null);
            TextView date = productItemView.findViewById(R.id.textView1);
            TextView income = productItemView.findViewById(R.id.textView2);
            TextView expense = productItemView.findViewById(R.id.textView3);
            TextView saving = productItemView.findViewById(R.id.textView4);

            SavingDetails savingDetails=arrayList.get(position);
            date.setText(savingDetails.getDate());
            income.setText(Integer.toString(savingDetails.getIncome()));
            expense.setText(Integer.toString(savingDetails.getExpense()));
            saving.setText(Integer.toString(savingDetails.getSaving()));

            return productItemView;
        }
    }
}

