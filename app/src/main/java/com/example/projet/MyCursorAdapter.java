package com.example.projet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyCursorAdapter extends CursorAdapter {
    public MyCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }


    // The newView method is used to inflate a new view and return it
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent,
                false);
    }

    // The bindView method is used to bind all data to a given view
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Find fields to populate in inflated template
        TextView txt_idf = (TextView) view.findViewById(R.id.idf_item);
        TextView txt_desc = (TextView) view.findViewById(R.id.desc_item);
        TextView txt_tel = (TextView) view.findViewById(R.id.phone_item);
        TextView txt_email = (TextView) view.findViewById(R.id.email_item);
        TextView txt_coords = (TextView) view.findViewById(R.id.coords_item);

        ImageView sms_icon = view.findViewById(R.id.sms_icon);
        ImageView call_icon = view.findViewById(R.id.call_icon);
        ImageView email_icon = view.findViewById(R.id.email_icon);

        // Extract properties from cursor
        String idf = cursor.getString(1);
        String desc = cursor.getString(2);
        String tel = cursor.getString(3);
        String email = cursor.getString(4);
        String coords = cursor.getString(5);

        // Populate fields with extracted properties
        txt_idf.setText(idf);
        txt_desc.setText(desc);
        txt_tel.setText(tel);
        txt_email.setText(email);
        txt_coords.setText(coords);
    }
}
