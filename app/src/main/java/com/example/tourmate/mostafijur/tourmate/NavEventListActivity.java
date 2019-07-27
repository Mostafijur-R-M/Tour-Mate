package com.example.tourmate.mostafijur.tourmate;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

public class NavEventListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, EventAdapter.ItemActionListener, EventAdapter.listItemActionClickListener{

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private DatabaseReference eventRef;

    private SearchView searchView;


    private List<Event> eventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private EventAdapter adapterlist;
    private LinearLayoutManager llm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_event_list);

        Toast.makeText(this,"Welcome!",Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.eventListRV);

        Intent intent = getIntent();

        rootRef = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userRef = rootRef.child(user.getUid());

        eventRef = userRef.child("Event");
        eventRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventList.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    Event event = d.getValue(Event.class);
                    eventList.add(event);
                }

              adapterlist.updateList(eventList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        adapterlist = new EventAdapter(this, eventList);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapterlist);


        fab = findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavEventListActivity.this,CreateNewEventActivity.class));
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(NavEventListActivity.this, s, Toast.LENGTH_SHORT).show();
                SearchRecentSuggestions suggestions =
                        new SearchRecentSuggestions(NavEventListActivity.this,
                                MySuggestionsProvider.AUTHORITY,
                                MySuggestionsProvider.MODE);
                suggestions.saveRecentQuery(s, null);
                searchView.setQuery("", false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_camera) {
            startActivity(new Intent(this,TakePhotoActivity.class));
        } else if (id == R.id.nav_gallery) {

        }*/
        if (id == R.id.nav_location) {
            Toast.makeText(this,"Please wait a moment",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,DeviceLocationActivity.class));

        } else if (id == R.id.nav_weather) {
            Toast.makeText(this,"Please wait a moment",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,WeatherActivity.class));

        }
       /* else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemDelete(String rowId) {

            eventRef.child(rowId).removeValue();
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onItemEdit(String rowId) {
        eventRef.child(rowId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                new AlertDialog.Builder(NavEventListActivity.this)
                .setView( LayoutInflater.from(NavEventListActivity.this).inflate(R.layout.activity_create_new_event,
                        null,false)).show();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        startActivity(new Intent(this,CreateNewEventActivity.class).putExtra("id", rowId));

    }

    @Override
    public void onListItemActionClickListener(String eventId, String eventName, String createDate, String startDate, String budget) {


    Intent intent = new Intent(this,show_Event.class);

    intent.putExtra("eventId", eventId);
    intent.putExtra("eventName", eventName);
    intent.putExtra("createDate", createDate);
    intent.putExtra("startDate", startDate);
    intent.putExtra("budget", budget);

    startActivity(intent);

    }



}
