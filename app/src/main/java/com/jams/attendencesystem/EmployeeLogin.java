package com.jams.attendencesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class EmployeeLogin extends AppCompatActivity {
    EditText Email,Password;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_employee_login);
        Email = findViewById(R.id.EmployeeEmailEditText);
        Password = findViewById(R.id.EmployeePasswordEditText);
        Button LoginBtn = findViewById(R.id.LoginEmployeeButton);
        auth = FirebaseAuth.getInstance();
        TextView SignUpTextView = findViewById(R.id.SignUpTextView);
        SignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
                /*Intent intent = new Intent(getApplicationContext(), EmployeeDashboard.class);
                startActivity(intent);*/
            }
        });



    }

    private void LoginUser() {
      String  email = Email.getText().toString().trim();
      String password = Password.getText().toString().trim();

      if(email.isEmpty())
      {
          Email.setError("Cannot Be Empty");
          Email.requestFocus();
          return;
      }
      if(! Patterns.EMAIL_ADDRESS.matcher(email).matches()){
          Email.setError("Enter Correct Email");
          Email.requestFocus();
      }
      if(password.isEmpty() || password.length() < 6)
      {
          Password.setError("Password cannot be neither Empty nor less than 6 characters!");
          Password.requestFocus();
      }

        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser LoginUser = auth.getCurrentUser();

                            Intent intent = new Intent(getApplicationContext(), EmployeeDashboard.class);
                            startActivity(intent);

                        }
                       else
                        {
                            Toast.makeText(getApplicationContext(),"Incorrect User Id or Password",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}