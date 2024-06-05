package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText idEdit;
    private EditText passEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEdit = findViewById(R.id.editTextTextPersonName);
        passEdit = findViewById(R.id.editTextTextPassword2);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEdit.getText().toString();
                String pwd = passEdit.getText().toString();
            //    if (validatLogin(id, pwd).equalsIgnoreCase("Successfully")) {
//                if (true) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
         //       }

            }

        });
    }

    public String validatLogin(String id, String pwd) {
        String text;
        if (id.isEmpty() || pwd.isEmpty()) {
            if (id.isEmpty() && !pwd.isEmpty()) {
                Toast myToast = Toast.makeText(getApplicationContext(), "ID can't be blank", Toast.LENGTH_SHORT);
                text = "ID can't be blank";
                myToast.show();
            } else if (!id.isEmpty() && pwd.isEmpty()) {
                Toast myToast = Toast.makeText(getApplicationContext(), "Password can't be blank", Toast.LENGTH_SHORT);
                text = "Password can't be blank";
                myToast.show();
            } else {
                Toast myToast = Toast.makeText(getApplicationContext(), "ID and Password can't be blank", Toast.LENGTH_SHORT);
                text = "ID and Password can't be blank";
                myToast.show();
            }
        } else if (id.equals(getString(R.string.ID)) & pwd.equalsIgnoreCase(getString(R.string.PASSWORD))) {
            Toast myToast = Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT);
            text = "Successfully";
            myToast.show();

        } else {
            Toast myToast = Toast.makeText(getApplicationContext(), "Please provide correct ID or Password", Toast.LENGTH_SHORT);
            text = "Please provide correct ID or Password";
            myToast.show();

        }
        return (text);
    }
}
