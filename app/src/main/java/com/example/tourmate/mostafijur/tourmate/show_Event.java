package com.example.tourmate.mostafijur.tourmate;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class show_Event extends AppCompatActivity {



    private FirebaseAuth auth;
    private FirebaseUser user;

    private TextView eventNameTV, createDateTV, startDateTV, budgetTV;
    private Button addBudgetBTN, addExpenseBTN, showAllExpenseBTN,  cameraBTN, galleryBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__event);

        Intent intent = getIntent();
        String name = intent.getStringExtra("eventName");
        String createdate = intent.getStringExtra("createDate");
        String startdate = intent.getStringExtra("startDate");
        String budget = intent.getStringExtra("budget");

        eventNameTV = findViewById(R.id.showEventNameID);
        createDateTV = findViewById(R.id.eventcreatedateID);
        startDateTV = findViewById(R.id.eventstartdateID);
        budgetTV = findViewById(R.id.showEvnetBudgetID);

        eventNameTV.setText(name);
        createDateTV.setText(createdate);
        startDateTV.setText(startdate);
        budgetTV.setText(budget);

        cameraBTN = findViewById(R.id.cameraBTNID);
        /*addBudgetBTN = findViewById(R.id.addMoreBudgetBTNID);*/
        addExpenseBTN = findViewById(R.id.addMoreExpenseBTNID);
        showAllExpenseBTN = findViewById(R.id.showAllExpenseListBTNID);
        showAllExpenseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(show_Event.this, ExpenseListActivity.class));
            }
        });
        addExpenseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(show_Event.this, ExpenseActivity.class));
                /*new AlertDialog.Builder(show_Event.this)
                        .setView(LayoutInflater.from(show_Event.this).inflate(R.layout.expense_row,null, false))
                        .show();*/
//                startActivity(new Intent(show_Event.this, ExpenseActivity.class));

            }
        });
        cameraBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(show_Event.this, TakePhotoActivity.class));
            }
        });
        /*addBudgetBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                *//*new AlertDialog.Builder(show_Event.this)
                        .setView(LayoutInflater.from(show_Event.this).inflate(R.layout.budget_row,null, false))
                        .show();
                EditText expenseName = v.findViewById(R.id.addExpenseNameID);
                EditText expenseAmount = v.findViewById(R.id.addExpenseAmountID);

                String expense = expenseName.getText().toString();
                String amount = expenseAmount.getText().toString();*//*

            }
        });*/

        /*eventRef.child(rowId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Event event = (Event) dataSnapshot.getChildren();
                eventNameTV.setText(event.getEventName());
                budgetTV.setText(Double.toString(event.getBudget()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        switch (item.getItemId()){

            /*case R.id.menu_login:

                break;*/
            case R.id.menu_logout:
                if(user != null){
                    auth.signOut();
                    startActivity(new Intent(this,MainActivity.class));
                    finish();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
