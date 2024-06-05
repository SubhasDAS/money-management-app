package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExpenseActivity extends AppCompatActivity {
    Button showPopupBtn, closePopupBtn;
    PopupWindow popupWindow;
    LinearLayout linearLayout;
    EditText editText;
    EditText editText1;
    int count=3;
    TextView  Text6;
    Button button;
    ArrayList<ExpenseDetails> arrayList;
    ProductAdopter adapter;
    ListView listView;

    popup3 popup = new popup3();
    EditText source ;
    EditText amount;
    Button update ;
    Button delete ;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_layout);
        showPopupBtn = (Button) findViewById(R.id.showPopupBtn);
        linearLayout = (LinearLayout) findViewById(R.id.constrainLayout1);
        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editTextNumber1);
        button = findViewById(R.id.closePopupBtn1);
        Text6 = findViewById(R.id.textView6);

        arrayList = new ArrayList<>();
        ExpenseDetailsDbHelper dbHelper = new ExpenseDetailsDbHelper(this);

                arrayList= dbHelper.get();


        adapter = new ProductAdopter();
        listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
        BigInteger total = new BigInteger("0");
        for (ExpenseDetails expenseDetail:arrayList ){
            BigInteger amount = new BigInteger(expenseDetail.getAmount());
            total = total.add(amount);

        }
        Text6.setText(total.toString());
        adapter.getCount();

        showPopupBtn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View View) {
                //instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) ExpenseActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.expense_add, null);
                closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn1);
                editText = customView.findViewById(R.id.editTextNumber1);
                editText1 = customView.findViewById(R.id.editTextTextSource);

                //instantiate popup window
                popupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                //display the popup window
                popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
                popupWindow.setFocusable(true);
                popupWindow.update();
                popupWindow.setTouchModal(false);
                popupWindow.update();
                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        String amo = editText.getText().toString();
                        String source = editText1.getText().toString();
                        Text6 = findViewById(R.id.textView6);


                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        Date date1 = new Date();
                       String date = dateFormat.format(date1);
                        count=dbHelper.getTotalNumberOfId();
                        count++;


                        if (!amo.isEmpty()&&!source.isEmpty()){

//                            IncomeDetails incomeDetails12 = new IncomeDetails(source, amo, count, date);
//                            arrayList.add(incomeDetails12);
                            // Add data in database
                            dbHelper.add(source, amo, date);

                            editText.setText("");
                            popupWindow.dismiss();

                            arrayList= dbHelper.get();
                            adapter = new ProductAdopter();
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            BigInteger total = new BigInteger("0");
                            for (ExpenseDetails expenseDetails:arrayList ){
                                BigInteger amount = new BigInteger(expenseDetails.getAmount());
                                total = total.add(amount);

                            }
                            Text6.setText(total.toString());
                        }
                        else{
//                            Toast myToast = Toast.makeText(getApplicationContext() , "Amount or should not be empty", Toast.LENGTH_SHORT);

//                            myToast.show();
                        }



                    }
                });


            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext() , "Item is clicked at " + "position " +position, Toast.LENGTH_SHORT).show();
                dialog = new Dialog(ExpenseActivity.this);
                dialog.setContentView(R.layout.custom_dialog_layout);
                dialog.show();
                source = dialog.findViewById(R.id.editTextTextSource);
                amount = dialog.findViewById(R.id.editTextNumber1);
                update = dialog.findViewById(R.id.closePopupBtn1);
                delete = dialog.findViewById(R.id.closePopupBtn2);


                source.setText(arrayList.get(position).getSourceOfIncome());
                amount.setText(arrayList.get(position).getAmount());

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        {
                            String source1 = source.getText().toString();
                            String amount1 = amount.getText().toString();
                            Text6 = findViewById(R.id.textView6);


                            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = new Date();
                            String date = dateFormat.format(date1);
//                            count=dbHelper.getTotalNumberOfId();


                            if (!amount1.isEmpty()&&!source1.isEmpty()){

//                        IncomeDetails incomeDetails12 = new IncomeDetails(source1, amount1, count, date);
//                        arrayList.add(incomeDetails12);
                                // Add data in database
                                dbHelper.update(source1, amount1, date, Integer.toString(position+1));


//                                editText.setText("");
                                dialog.dismiss();
                                arrayList= dbHelper.get();
                                adapter = new ProductAdopter();
                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                BigInteger total = new BigInteger("0");
                                for (ExpenseDetails expenseDetails:arrayList ){
                                    BigInteger amount = new BigInteger(expenseDetails.getAmount());
                                    total = total.add(amount);

                                }
                                Text6.setText(total.toString());
                            }
                            else{
//                            Toast myToast = Toast.makeText(getApplicationContext() , "Amount or should not be empty", Toast.LENGTH_SHORT);

//                            myToast.show();
                            }



                        }
                    }
                });


                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        {
                                // Add data in database
                                dbHelper.delete( Integer.toString(position+1));


//                                editText.setText("");
                                dialog.dismiss();
                                arrayList= dbHelper.get();
                                adapter = new ProductAdopter();
                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                BigInteger total = new BigInteger("0");
                                for (ExpenseDetails expenseDetails:arrayList ){
                                    BigInteger amount = new BigInteger(expenseDetails.getAmount());
                                    total = total.add(amount);

                                }
                                Text6.setText(total.toString());


                        }
                    }
                });
            }
        });


    }



    class ProductAdopter extends ArrayAdapter
    {
        public ProductAdopter()
        {
             super(ExpenseActivity.this, R.layout.text_view_resource, arrayList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

LayoutInflater layoutInflater= getLayoutInflater();
View productItemView = layoutInflater.inflate(R.layout.text_view_resource, null);
            TextView slNo = productItemView.findViewById(R.id.textView1);
            TextView date = productItemView.findViewById(R.id.textView2);
            TextView sourceName = productItemView.findViewById(R.id.textView3);
            TextView ammount = productItemView.findViewById(R.id.textView4);

            ExpenseDetails expenseDetails= arrayList.get(position);
            slNo.setText(Integer.toString(expenseDetails.getIncomeDetailId()));
            date.setText(expenseDetails.getDateOfAddIncome());
            sourceName.setText(expenseDetails.getSourceOfIncome());
            ammount.setText(expenseDetails.getAmount());

            return productItemView;
        }
    }


}
