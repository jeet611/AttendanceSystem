package com.jams.attendencesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
   // FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;


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
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String user_name = snapshot.child(uid).child("UserName").getValue(String.class);

                Toast.makeText(getApplicationContext(),"Welcome :"+ user_name , Toast.LENGTH_LONG).show();
                UserName.setText("Hello" + user_name);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}