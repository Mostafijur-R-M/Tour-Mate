package com.example.tourmate.mostafijur.tourmate;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements LoginFragment.UserAuthListener{
    private FragmentManager manager;
    private FirebaseAuth auth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        ft.add(R.id.frameLayoutContainer, loginFragment);
        ft.commit();


    }

    @Override
    public void onAuthComplete() {
        startActivity(new Intent(this,NavEventListActivity.class));

        finish();
        /*FragmentTransaction fragmentTransaction = manager.beginTransaction();
        EventListFragment eventListFragment = new EventListFragment();
        fragmentTransaction.replace(R.id.frameLayoutContainer, eventListFragment);
        fragmentTransaction.commit();*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        switch (item.getItemId()){

            /*case R.id.menu_login:

                break;*/
            case R.id.menu_logout:
                if(user != null){
                    auth.signOut();

                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
