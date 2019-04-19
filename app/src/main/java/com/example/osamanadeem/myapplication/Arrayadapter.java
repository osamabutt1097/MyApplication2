package com.example.osamanadeem.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Arrayadapter extends ArrayAdapter<appointment> {
    public Arrayadapter(Context context, ArrayList<appointment> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        appointment user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listweek9, parent, false);
        }
        // Lookup view for data population
        TextView Name =  convertView.findViewById(R.id.name);
        TextView Type =  convertView.findViewById(R.id.type);
        TextView DatenTime = convertView.findViewById(R.id.datentime);
        // Populate the data into the template view using the data object
        Name.setText(user.name);
        Type.setText(user.type);
        DatenTime.setText(user.datentime);
        // Return the completed view to render on screen
        return convertView;
    }
}