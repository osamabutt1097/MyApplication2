package com.example.osamanadeem.myapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class W9_frag_MainActivity extends AppCompatActivity {

    AddNewFragment addNewFragment;
    LinearLayout ll;
    DBTool dBtool;
    ListView listView;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w9_frag__main);
        dBtool = new DBTool(this);
    addNewFragment = new AddNewFragment();
    fm = getSupportFragmentManager();
    ll = findViewById(R.id.ll);
    listView =findViewById(R.id.list);
        ArrayList<HashMap<String,String>> contactList =
                dBtool.getAllContacts();
        ListAdapter listAdapter = new SimpleAdapter(getApplicationContext(),contactList,
                R.layout.listweek9,new String[]{"Name","Type","DatenTime"},new int[] {R.id.name,R.id.type,R.id.datentime});
        listView.setAdapter(listAdapter);

    }

    public void addapointment(View view) {
        ll.setVisibility(View.VISIBLE);
       addNewFragment = (AddNewFragment) fm.findFragmentById(R.id.frag_granade);

    }
}
