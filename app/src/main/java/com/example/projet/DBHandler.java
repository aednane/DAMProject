package com.example.projet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;// Version de la base de données
    private static final String DATABASE_NAME = "dbproject";//Nom de la base de données

    // le constructeur pour créer la base de données avec une version définit
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Dans la méthode onCreate, on crée les tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE faculte(_id INTEGER, identifiant TEXT PRIMARY KEY, description TEXT, numTel TEXT, email TEXT, coords TEXT)";
        db.execSQL(CREATE_TABLE);
    }
    // La méthode onUpgrade permet de mettre à jours la base de données
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS FACULTE");// Drop older table if existed
        onCreate(db);// Creating tables again
    }

    public void addFaculte(Faculte f) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("identifiant", f.getIdentifiant()); val.put("description", f.getDesc()); val.put("numTel", f.getNumTel());
        val.put("email", f.getMail()); val.put("coords", f.getCoords());
        db.insert("faculte",null,val);
        db.close();
    }

    public Faculte getFaculte(String identifiant) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from faculte where identifiant = ?",new String[]{identifiant});
        if (cursor.getCount()==0) return null;
        cursor.moveToFirst();
        Faculte f = new Faculte(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return f;
    }

    public Cursor getFacultes(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from faculte", null);
        return cursor;
    }

    public int updateFaclute(Faculte f){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("identifiant", f.getIdentifiant()); values.put("description", f.getDesc()); values.put("numTel", f.getNumTel());
        values.put("email", f.getMail()); values.put("coords", f.getCoords());
        int rows = db.update("faculte", values, "identifiant = ?", new String[] {f.getIdentifiant()});
        db.close();
        return rows;
    }

    public int deleteFaculte(Faculte f){
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete("faculte", "identifiant = ?", new String[] {f.getIdentifiant()});
        db.close();
        return rows;
    }
}
