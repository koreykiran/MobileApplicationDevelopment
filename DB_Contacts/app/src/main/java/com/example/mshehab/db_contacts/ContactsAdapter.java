package com.example.mshehab.db_contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mshehab on 11/20/17.
 */

public class ContactsAdapter extends ArrayAdapter<Contact> {
    public ContactsAdapter(Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
        TextView textViewPhone = convertView.findViewById(R.id.textViewPhone);
        TextView textViewDept = convertView.findViewById(R.id.textViewDept);

        //set the data from the contact object
        textViewName.setText(contact.getName());
        textViewEmail.setText(contact.getEmail());
        textViewPhone.setText(contact.getPhone());
        textViewDept.setText(contact.getDept());

        return convertView;
    }
}
