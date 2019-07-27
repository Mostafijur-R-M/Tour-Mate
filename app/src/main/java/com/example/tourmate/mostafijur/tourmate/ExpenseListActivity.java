package com.example.tourmate.mostafijur.tourmate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExpenseListActivity extends AppCompatActivity implements ExpenseAdapter.ItemActionListener, ExpenseAdapter.listItemActionClickListener{

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private DatabaseReference eventRef;
    private DatabaseReference expenseRef;


    private List<Expense> expenseList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ExpenseAdapter adapterlist;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        recyclerView = findViewById(R.id.expenseListRV);

        rootRef = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userRef = rootRef.child(user.getUid());

        expenseRef = userRef.child("Expense");
        expenseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                expenseList.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    Expense expense = d.getValue(Expense.class);
                    expenseList.add(expense);
                }

                adapterlist.updateList(expenseList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        adapterlist = new ExpenseAdapter(this, expenseList);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapterlist);
    }

    public void addMoreExpense(View view) {
        startActivity(new Intent(this, ExpenseActivity.class));
    }

    @Override
    public void onItemDelete(String rowId) {
        expenseRef.child(rowId).removeValue();
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemEdit(String rowId) {

    }

    @Override
    public void onListItemActionClickListener(String eventId, String expenseName, String amount) {

    }
}
