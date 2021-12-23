package com.jams.attendencesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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
        auth.signInWithEmailAndPassword(Email.getText().toString(),Password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser LoginUser = auth.getCurrentUser();
                          //  Toast.makeText(getApplicationContext(),LoginUser+"LOGIN",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), EmployeeDashboard.class);
                            startActivity(intent);

                        }
                       else
                        {
                            Toast.makeText(getApplicationContext(),"Incorrect User",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}