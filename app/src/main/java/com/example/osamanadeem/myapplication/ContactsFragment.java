package com.example.osamanadeem.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactsFragment extends ListFragment {
    private ListSelectionListner mListner = null;

    public interface ListSelectionListner{
        public void onListSelection(int index);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListner = (ListSelectionListner) context;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(
                new ArrayAdapter<String>(
                getActivity(),
                R.layout.textview,
                FragmentMainActivity.mContactsArray
        ));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        getListView().setItemChecked(position,true);
        mListner.onListSelection(position);
    }
}
