package com.example.mshehab.db_contacts.DB;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mshehab on 12/4/17.
 */

public class ContactsTable {
    static final String TABLENAME = "contacts";
    static final String COLUMN_ID = "id";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_EMAIL = "email";
    static final String COLUMN_DEPARTMENT = "department";
    static final String COLUMN_PHONE = "phone";

    public static void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + " (" );
        sb.append(COLUMN_ID + " integer primary key autoincrement, ");
        sb.append(COLUMN_NAME + " text not null, ");
        sb.append(COLUMN_EMAIL + " text not null, ");
        sb.append(COLUMN_DEPARTMENT + " text not null, ");
        sb.append(COLUMN_PHONE+ " text not null");
        sb.append(");");
        try {
            db.execSQL(sb.toString());
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME + ";");
        ContactsTable.onCreate(db);
    }
}
