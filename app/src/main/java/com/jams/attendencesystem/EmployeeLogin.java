package com.jams.attendencesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeLogin extends AppCompatActivity {
    EditText Email,Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);
        Email = findViewById(R.id.EmployeeEmailEditText);
        Password = findViewById(R.id.EmployeePasswordEditText);
        Button LoginBtn = findViewById(R.id.LoginEmployeeButton);

        String EmailValue = Email.getText().toString();
        String PasswordValue = Password.getText().toString();
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EmployeeDashboard.class);
                startActivity(intent);
            }
        });



    }
}