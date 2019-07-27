package com.example.tourmate.mostafijur.tourmate;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateNewEventActivity extends AppCompatActivity {
    private EditText nameET, budgetET;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private DatabaseReference eventRef;
    private Context context;
    private List<Event>events = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private EventAdapter eventAdapter;
    private Button saveBTN,input_createdDate, input_startDate;
    private int year, month, day;
    private String dateOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_event);

        rootRef = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userRef = rootRef.child(user.getUid());
        eventRef = userRef.child("Event");


        nameET = findViewById(R.id.input_eventName);
        input_createdDate = findViewById(R.id.input_createdDate);
        input_startDate = findViewById(R.id.input_startDate);
        budgetET = findViewById(R.id.input_budget);
        saveBTN = findViewById(R.id.input_saveEvent);

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEvent();
            }
        });

    }




    public void saveEvent() {
        String eventName = nameET.getText().toString();
        String createDate = input_createdDate.getText().toString();
        String startDate = input_startDate.getText().toString();
        String budget = budgetET.getText().toString();
        /*try {

            String id = userRef.push().getKey();
            Event event = new Event( id, eventName, createDate, startDate, Double.parseDouble(budget));

            userRef.child(id).setValue(event);

            Toast.makeText(getApplicationContext(),"Save Successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,NavEventListActivity.class));
        }catch (Exception e){
            Toast.makeText(this,"Please fill up all fields",Toast.LENGTH_SHORT).show();

        }*/

        if (!TextUtils.isEmpty(eventName) && !TextUtils.isEmpty(createDate)
                && !TextUtils.isEmpty(startDate) && !TextUtils.isEmpty(budget)){
            String keyID = eventRef.push().getKey();
            Event event = new Event( keyID, eventName, createDate, startDate, Double.parseDouble(budget));


            eventRef.child(keyID).setValue(event);
            Toast.makeText(getApplicationContext(),"Save Successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,NavEventListActivity.class));
        }else {
            Toast.makeText(getApplicationContext(),"Please fill up all fields",Toast.LENGTH_SHORT).show();
        }
    }

    private DatePickerDialog.OnDateSetListener listener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    final Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, dayOfMonth);
                    dateOfBirth = sdf.format(calendar.getTime());
                    input_createdDate.setText(dateOfBirth);
                }
            };

    public void eventCreateDatePicker(View view) {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog =
                new DatePickerDialog(this,
                        listener,
                        year, month, day);
        dialog.show();
    }

    public void eventStartDatePicker(View view) {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog =
                new DatePickerDialog(this,
                        startdatelistener,
                        year, month, day);
        dialog.show();
    }
    private DatePickerDialog.OnDateSetListener startdatelistener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    final Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, dayOfMonth);
                    dateOfBirth = sdf.format(calendar.getTime());
                    input_startDate.setText(dateOfBirth);
                }
            };
}
