package com.jams.attendencesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EmployeeDashboard extends AppCompatActivity {
    TextView UserName;
    Button LogoutUser,PunchInBtn;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);

        Calendar calendar = Calendar.getInstance();
        String currentdate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView DateTextView = findViewById(R.id.DateTextview);
        DateTextView.setText(currentdate);
        TextView currentTime = findViewById(R.id.timeTextview);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
        String DateTime = simpleDateFormat.format(calendar.getTime());
        currentTime.setText(DateTime);
        UserName = findViewById(R.id.UserNameTextView);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        uid = user.getUid();
        PunchInBtn = findViewById(R.id.PunchInBtn);
        PunchInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PunchIn.class);
                startActivity(intent);
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String user_name = snapshot.child(uid).child("name").getValue(String.class);

                Toast.makeText(getApplicationContext(),"Welcome : "+ user_name , Toast.LENGTH_LONG).show();
                UserName.setText("Hello " + user_name);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        LogoutUser = findViewById(R.id.LogoutButton);
        LogoutUser.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              auth.signOut();
                                              Intent intent = new Intent(getApplicationContext(),EmployeeLogin.class);
                                              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                              intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                              intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                              startActivity(intent);


                                          }
                                      });
    }


}