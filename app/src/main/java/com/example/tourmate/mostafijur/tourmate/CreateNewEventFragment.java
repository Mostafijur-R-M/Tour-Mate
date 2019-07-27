package com.example.tourmate.mostafijur.tourmate;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateNewEventFragment extends Fragment {
    private EditText nameET, createDateET, startDateET, budgetET;
    private Button saveEvent;

    public CreateNewEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_new_event, container, false);
        /*nameET = view.findViewById(R.id.input_eventName);
        createDateET = view.findViewById(R.id.input_careateDatePickerButtonID);
        startDateET = view.findViewById(R.id.input_startDatePickerButtonID);
        budgetET = view.findViewById(R.id.input_budget);



        saveEvent = view.findViewById(R.id.input_saveEvent);
        saveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String createDate = createDateET.getText().toString();
                String startDate = startDateET.getText().toString();
                String budget = budgetET.getText().toString();
                *//*boolean status = ValidationHelper.validatesInput(name, createDate, startDate, budget);
                if (status){
                    Event event = new Event(name, createDate,startDate, Double.parseDouble(budget));
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.err_msg),Toast.LENGTH_SHORT).show();
                }*//*
            }
        });*/

        return view;
    }


}



