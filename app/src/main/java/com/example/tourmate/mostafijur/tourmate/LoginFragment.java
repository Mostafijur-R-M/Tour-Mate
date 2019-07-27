package com.example.tourmate.mostafijur.tourmate;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private EditText emailET, passwordET;
    private TextView statusTV;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Context context;
    private Button loginBTN, registerBTN;
    private UserAuthListener userAuthListener;





    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        userAuthListener = (UserAuthListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        emailET = view.findViewById(R.id.input_email);
        passwordET = view.findViewById(R.id.input_password);
        statusTV = view.findViewById(R.id.statusTVID);
        loginBTN = view.findViewById(R.id.loginBTNID);
        registerBTN = view.findViewById(R.id.registerBTNID);

            loginBTN.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        String email = emailET.getText().toString();
                        String password = passwordET.getText().toString();

                        auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            userAuthListener.onAuthComplete();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();

//                                statusTV.setText(e.getMessage());
                            }
                        });
                    }catch (Exception e){
                        Toast.makeText(context,"Please input valid information",Toast.LENGTH_SHORT).show();
                    }
                }
            });


            registerBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String email = emailET.getText().toString();
                        String password = passwordET.getText().toString();

                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            userAuthListener.onAuthComplete();
                                            Toast.makeText(context,"Login Successfully",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
//                        statusTV.setText(e.getMessage());
                            }
                        });
                    }catch (Exception e){
                        Toast.makeText(context,"Please input valid information",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        auth = FirebaseAuth.getInstance();
        return view;
    }
    private void UpdateUI() {
        statusTV.setText("You are logged in as "+user.getEmail());
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user !=null){
            userAuthListener.onAuthComplete();
        }
    }
    interface UserAuthListener{
        void onAuthComplete();
    }

}
