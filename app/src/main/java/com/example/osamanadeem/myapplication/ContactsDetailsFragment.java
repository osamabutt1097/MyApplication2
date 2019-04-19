package com.example.osamanadeem.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactsDetailsFragment extends Fragment {
    private static TextView mContactDetailView = null ;
    private static int mCurrIdx = -1;
    private static int mArrayLen;

    public static int getShownIndex() {
    return mCurrIdx;
    }

    public static void showContactAtIndex(int newindex) {
        if (newindex < 0 || newindex >= mArrayLen)
            return;
        mCurrIdx = newindex;
        mContactDetailView.setText(FragmentMainActivity.mDetailsArray[mCurrIdx]);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_layout,container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContactDetailView = getActivity().findViewById(R.id.contactsdetailtxt);
        mArrayLen = FragmentMainActivity.mContactsArray.length;
    }

    public void ShowContactDetails(int index)
    {
        mContactDetailView.setText(FragmentMainActivity.mDetailsArray[index]);
    }
}
