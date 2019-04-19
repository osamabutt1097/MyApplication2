package com.example.osamanadeem.myapplication;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentMainActivity extends AppCompatActivity implements ContactsFragment.ListSelectionListner {

    public static String[] mContactsArray,mDetailsArray;
    ContactsDetailsFragment mcontactsDetailsFragment;
    private FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);
        init();
        FragmentManager_init();
    }

    private void FragmentManager_init() {
        fragmentManager = getSupportFragmentManager();
        mcontactsDetailsFragment =
                (ContactsDetailsFragment) fragmentManager.findFragmentById(R.id.contacts_details_fragment2);
    }


    private void init(){
        mContactsArray = getResources().getStringArray(R.array.contact_array);
        mDetailsArray = getResources().getStringArray(R.array.details_array);
        mcontactsDetailsFragment = new ContactsDetailsFragment();
    }

    @Override
    public void onListSelection(int index) {
        if (ContactsDetailsFragment.getShownIndex() != index){
            ContactsDetailsFragment.showContactAtIndex(index);
        }
    }
}


