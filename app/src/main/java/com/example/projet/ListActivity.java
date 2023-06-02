package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getSupportActionBar().setTitle("Facultés UMBB");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        BottomNavigationView nav = findViewById(R.id.bottom_bar);
        nav.setSelectedItemId(R.id.list);
        nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                    return true;

                case R.id.facultes:
                    startActivity(new Intent(getApplicationContext(), FaculteActivity.class));
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                    return true;

                case R.id.list:
                    return true;
            }
            return false;
        });


        DBHandler bdd = new DBHandler(this);

        // Récupérer tous le contenu de la table
        Cursor cursor = (Cursor) bdd.getFacultes();    // une méthode qui renvoi la table faculte
        MyCursorAdapter adapter = new MyCursorAdapter(this, cursor);
        ListView lv=(ListView)this.findViewById(R.id.list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView sms_icon = view.findViewById(R.id.sms_icon);
                ImageView call_icon = view.findViewById(R.id.call_icon);
                ImageView email_icon = view.findViewById(R.id.email_icon);
                ImageView coords_icon = view.findViewById(R.id.coords_icon);

                TextView phone_item = findViewById(R.id.phone_item);
                TextView email_item = findViewById(R.id.email_item);
                TextView coords_item = findViewById(R.id.coords_item);

                sms_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String phone = phone_item.getText().toString();
                        if (phone.equals("")) {
                            Toast.makeText(getApplicationContext(), "Le champ Téléphone ne doit pas être vide", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Uri uri = Uri.parse("smsto:" + phone);
                        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                        startActivity(intent);
                    }
                });

                call_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String phone = phone_item.getText().toString();
                        if (phone.equals("")) {
                            Toast.makeText(getApplicationContext(), "Le champ Téléphone ne doit pas être vide", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Uri uri = Uri.parse("tel:" + phone);
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        startActivity(intent);
                    }
                });

                email_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email = email_item.getText().toString();
                        if (email.equals("")) {
                            Toast.makeText(getApplicationContext(), "Le champ Email ne doit pas être vide", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:" + email));
                        startActivity(intent);
                    }
                });

                coords_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String coords = coords_item.getText().toString();
                        if (coords.equals("")) {
                            Toast.makeText(getApplicationContext(), "Le champ Coordonnées ne doit pas être vide", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Uri gmmIntentUri = Uri.parse("geo:" + coords);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                });
            }
        });
    }

}