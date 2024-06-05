package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {
    Button showPopupBtn, closePopupBtn;
    PopupWindow popupWindow;
    LinearLayout linearLayout;
    EditText editText;
    EditText editText1;
    int count=3;
    TextView  Text6;
    Button button;
    ArrayList<IncomeDetails> arrayList;
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
        setContentView(R.layout.activity_main3);
        showPopupBtn = (Button) findViewById(R.id.showPopupBtn);
        linearLayout = (LinearLayout) findViewById(R.id.constrainLayout1);
        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editTextNumber1);
        button = findViewById(R.id.closePopupBtn1);
        Text6 = findViewById(R.id.textView6);

        arrayList = new ArrayList<>();
        System.out.println("Subhas " + getApplicationContext());
        IncomeDetailsDbHelper dbHelper = new IncomeDetailsDbHelper(this);

//        IncomeDetails incomeDetails1 = new IncomeDetails("Business1", "190000", 1, "18-02-2023");
//        IncomeDetails incomeDetails2 = new IncomeDetails("Business1", "55", 2, "18-02-2023");
//        IncomeDetails incomeDetails3 = new IncomeDetails("Business1", "455", 3, "18-02-2023");
//        arrayList.add(incomeDetails1);
//        arrayList.add(incomeDetails2);
//        arrayList.add(incomeDetails3);

        arrayList= dbHelper.get();


        adapter = new ProductAdopter();
        listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
        BigInteger total = new BigInteger("0");
        for (IncomeDetails incomeDetail:arrayList ){
            BigInteger amount = new BigInteger(incomeDetail.getAmount());
            total = total.add(amount);

        }
        Text6.setText(total.toString());
        adapter.getCount();

        showPopupBtn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View View) {
                //instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) MainActivity3.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.activity_popup2, null);
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
                            for (IncomeDetails incomeDetail:arrayList ){
                                BigInteger amount = new BigInteger(incomeDetail.getAmount());
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
                dialog = new Dialog(MainActivity3.this);
                dialog.setContentView(R.layout.custom_dialog_layout);
                dialog.show();
                source = dialog.findViewById(R.id.editTextTextSource);
                amount = dialog.findViewById(R.id.editTextNumber1);
                update = dialog.findViewById(R.id.closePopupBtn1);
                delete = dialog.findViewById(R.id.closePopupBtn2);

             int id =   arrayList.get(position).getIncomeDetailId();
                String date =   arrayList.get(position).getDateOfAddIncome();

                source.setText(arrayList.get(position).getSourceOfIncome());
                amount.setText(arrayList.get(position).getAmount());

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        {
                            String source1 = source.getText().toString();
                            String amount1 = amount.getText().toString();
                            Text6 = findViewById(R.id.textView6);


//                            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                            Date date1 = new Date();
//                            String date = dateFormat.format(date1);
//                            count=dbHelper.getTotalNumberOfId();


                            if (!amount1.isEmpty()&&!source1.isEmpty()){

//                        IncomeDetails incomeDetails12 = new IncomeDetails(source1, amount1, count, date);
//                        arrayList.add(incomeDetails12);
                                // Add data in database
                                dbHelper.update(source1, amount1, date, Integer.toString(id));


//                                editText.setText("");
                                dialog.dismiss();
                                arrayList= dbHelper.get();
                                adapter = new ProductAdopter();
                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                BigInteger total = new BigInteger("0");
                                for (IncomeDetails incomeDetail:arrayList ){
                                    BigInteger amount = new BigInteger(incomeDetail.getAmount());
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
                                dbHelper.delete( Integer.toString(id));


//                                editText.setText("");
                                dialog.dismiss();
                                arrayList= dbHelper.get();
                                adapter = new ProductAdopter();
                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                BigInteger total = new BigInteger("0");
                                for (IncomeDetails incomeDetail:arrayList ){
                                    BigInteger amount = new BigInteger(incomeDetail.getAmount());
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
             super(MainActivity3.this, R.layout.text_view_resource, arrayList);
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

            IncomeDetails incomeDetails= arrayList.get(position);
            slNo.setText(Integer.toString(incomeDetails.getIncomeDetailId()));
            date.setText(incomeDetails.getDateOfAddIncome());
            sourceName.setText(incomeDetails.getSourceOfIncome());
            ammount.setText(incomeDetails.getAmount());

            return productItemView;
        }
    }


}
