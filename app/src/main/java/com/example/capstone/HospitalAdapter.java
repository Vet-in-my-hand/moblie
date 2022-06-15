package com.example.capstone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HospitalAdapter extends ArrayAdapter<hospital> {

    public HospitalAdapter(Context context, int resource, List<hospital> hospitalList){
        super(context, resource, hospitalList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        hospital hospital = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hospital_item, parent, false);
        }

        TextView tv = convertView.findViewById(R.id.hospital_name);

        tv.setText(hospital.getName());

        return convertView;
    }
}
