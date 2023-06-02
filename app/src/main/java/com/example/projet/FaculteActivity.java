package com.example.projet;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FaculteActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getSupportActionBar().setTitle("Facultés UMBB");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculte);
        BottomNavigationView nav = findViewById(R.id.bottom_bar);
        nav.setSelectedItemId(R.id.facultes);
        nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                    return true;

                case R.id.facultes:
                    return true;

                case R.id.list:
                    startActivity(new Intent(getApplicationContext(), ListActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });

        RelativeLayout expandable_view = findViewById(R.id.expandable_view);
        CardView card_add = findViewById(R.id.add);

        RelativeLayout expandable_view2 = findViewById(R.id.expandable_view2);
        CardView card_edit = findViewById(R.id.edit);

        RelativeLayout expandable_view3 = findViewById(R.id.expandable_view3);
        CardView card_delete = findViewById(R.id.delete);

        card_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandable_view.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(card_add, new AutoTransition());
                    expandable_view.setVisibility(View.VISIBLE);
                }
                else {
                    TransitionManager.beginDelayedTransition(card_add, new AutoTransition());
                    expandable_view.setVisibility(View.GONE);
                }
            }
        });

        card_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandable_view2.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(card_edit, new AutoTransition());
                    expandable_view2.setVisibility(View.VISIBLE);
                }
                else {
                    TransitionManager.beginDelayedTransition(card_edit, new AutoTransition());
                    expandable_view2.setVisibility(View.GONE);
                }
            }
        });

        card_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandable_view3.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(card_delete, new AutoTransition());
                    expandable_view3.setVisibility(View.VISIBLE);
                }
                else {
                    TransitionManager.beginDelayedTransition(card_delete, new AutoTransition());
                    expandable_view3.setVisibility(View.GONE);
                }
            }
        });

        EditText idf_field = findViewById(R.id.idf_field);
        EditText desc_field = findViewById(R.id.desc_field);
        EditText phone_field = findViewById(R.id.phone_field);
        EditText email_field = findViewById(R.id.email_field);
        EditText coords_field = findViewById(R.id.coords_field);

        Button add_button1 = findViewById(R.id.add_button1);
        Button cancel_button1 = findViewById(R.id.cancel_button1);


        EditText idf_field2 = findViewById(R.id.idf_field2);
        EditText desc_field2 = findViewById(R.id.desc_field2);
        EditText phone_field2 = findViewById(R.id.phone_field2);
        EditText email_field2 = findViewById(R.id.email_field2);
        EditText coords_field2 = findViewById(R.id.coords_field2);

        Button edit_button = findViewById(R.id.edit_button);
        Button cancel_button2 = findViewById(R.id.cancel_button2);

        EditText rm_field = findViewById(R.id.rm_field);
        Button delete_btn = findViewById(R.id.delete_btn);
        Button cancel_button3 = findViewById(R.id.cancel_button3);


        DBHandler database = new DBHandler(this);

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idf = idf_field2.getText().toString();
                String desc = desc_field2.getText().toString();
                String tel = phone_field2.getText().toString();
                String email = email_field2.getText().toString();
                String coords = coords_field2.getText().toString();

                if (idf.equals("")) {
                    Toast.makeText(getApplicationContext(), "Le champ Identifiant ne doit pas être vide", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (desc.equals("")) {
                    Toast.makeText(getApplicationContext(), "Le champ Description ne doit pas être vide", Toast.LENGTH_SHORT).show();
                    return;
                }

                Faculte f = new Faculte(idf, desc, tel, email, coords);
                database.updateFaclute(f);

                idf_field2.setText("");
                desc_field2.setText("");
                phone_field2.setText("");
                email_field2.setText("");
                coords_field2.setText("");
                Toast.makeText(getApplicationContext(), "Faculté Modifiée", Toast.LENGTH_SHORT).show();
            }
        });

        cancel_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idf_field2.setText("");
                desc_field2.setText("");
                phone_field2.setText("");
                email_field2.setText("");
                coords_field2.setText("");

                TransitionManager.beginDelayedTransition(card_edit, new AutoTransition());
                expandable_view2.setVisibility(View.GONE);
            }
        });

        add_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idf = idf_field.getText().toString();
                String desc = desc_field.getText().toString();
                String tel = phone_field.getText().toString();
                String email = email_field.getText().toString();
                String coords = coords_field.getText().toString();

                if (idf.equals("")) {
                    Toast.makeText(getApplicationContext(), "Le champ Identifiant ne doit pas être vide", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (desc.equals("")) {
                    Toast.makeText(getApplicationContext(), "Le champ Description ne doit pas être vide", Toast.LENGTH_SHORT).show();
                    return;
                }

                Faculte f = new Faculte(idf, desc, tel, email, coords);
                database.addFaculte(f);

                idf_field.setText("");
                desc_field.setText("");
                phone_field.setText("");
                email_field.setText("");
                coords_field.setText("");
                Toast.makeText(getApplicationContext(), "Faculté Ajoutée", Toast.LENGTH_SHORT).show();
            }
        });

        cancel_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idf_field.setText("");
                desc_field.setText("");
                phone_field.setText("");
                email_field.setText("");
                coords_field.setText("");

                TransitionManager.beginDelayedTransition(card_add, new AutoTransition());
                expandable_view.setVisibility(View.GONE);
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idf = rm_field.getText().toString();

                if (idf.equals("")) {
                    Toast.makeText(getApplicationContext(), "Le champ ne doit pas être vide", Toast.LENGTH_SHORT).show();
                    return;
                }

                Faculte f = new Faculte(idf, null, null, null, null);
                database.deleteFaculte(f);
                rm_field.setText("");
                Toast.makeText(getApplicationContext(), "Faculté supprimmée", Toast.LENGTH_SHORT).show();
            }
        });

        cancel_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rm_field.setText("");
                TransitionManager.beginDelayedTransition(card_delete, new AutoTransition());
                expandable_view3.setVisibility(View.GONE);
            }
        });
    }
}
