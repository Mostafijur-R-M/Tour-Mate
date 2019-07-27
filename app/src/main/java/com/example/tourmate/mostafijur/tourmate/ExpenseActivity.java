package com.example.tourmate.mostafijur.tourmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExpenseActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private DatabaseReference eventRef;
    private DatabaseReference expenseRef;

    private EditText addExpenseNameET, addExpenseAmountET;
    private Button addExpenseBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        rootRef = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userRef = rootRef.child(user.getUid());


        expenseRef = userRef.child("Expense");

        addExpenseNameET = findViewById(R.id.addExpenseNameID);
        addExpenseAmountET = findViewById(R.id.addExpenseAmountID);
        addExpenseBTN = findViewById(R.id.addExpenseButtonID);

        addExpenseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMoreExpense();
            }
        });
    }

    private void addMoreExpense() {
        String expenseName = addExpenseNameET.getText().toString();
        String expenseAmount = addExpenseAmountET.getText().toString();

        if (!TextUtils.isEmpty(expenseName) && !TextUtils.isEmpty(expenseAmount)){

            String keyID = expenseRef.push().getKey();
            Expense expense = new Expense(keyID, expenseName, Double.parseDouble(expenseAmount));



            expenseRef.child(keyID).setValue(expense);
            Toast.makeText(getApplicationContext(),"Add Expense Successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,show_Event.class));
        }else {
            Toast.makeText(getApplicationContext(),"Please fill up all fields",Toast.LENGTH_SHORT).show();
        }
    }


}
