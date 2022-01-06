package com.jams.attendencesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText Name,Password,Email,Designation;
    Button SignUp;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name = findViewById(R.id.NameEditText);
        Password = findViewById(R.id.PasswordEditText);
        Email = findViewById(R.id.EmailEditText);
        Designation = findViewById(R.id.DesignationEditText);
        SignUp = findViewById(R.id.SignUpButton);

        auth = FirebaseAuth.getInstance();
        
        SignUp.setOnClickListener(v -> NewRegisterUser());

    }

    private void NewRegisterUser() {
        setVisible(false);
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String name = Name.getText().toString().trim();
        String designation = Designation.getText().toString().trim();

        if (name.isEmpty())
        {
            Name.setError("Full Name Required");
            Name.requestFocus();
            return;

        }
        if (email.isEmpty())
        {
            Email.setError("Email Required");
            Email.requestFocus();
            return;

        }
        if (password.isEmpty())
        {
            Password.setError("Password Required");
            Password.requestFocus();
            return;

        }
        if(password.length()< 6)
        {
            Password.setError("Password Should Be Greater Than 6 Characters!.");
            Password.requestFocus();
        }
        if (designation.isEmpty())
        {
            Password.setError("Designation Required");
            Password.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Provide Valid Email");
            Email.requestFocus();
        }

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(name,email,designation);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Register.this, "User Registered.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), EmployeeDashboard.class);
                                startActivity(intent);

                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Registeration Failed. Try Again Later", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}