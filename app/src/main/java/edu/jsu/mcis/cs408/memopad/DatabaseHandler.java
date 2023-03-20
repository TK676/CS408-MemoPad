package edu.jsu.mcis.cs408.memopad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase.db";

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE memo (_id integer primary key autoincrement, message text)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS memo");
        onCreate(db);
    }

    public void addMemo(String memo) {
        ContentValues values = new ContentValues();
        values.put("message", memo);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("memo", null, values);
        db.close();
    }

    public void deleteMemo(String ID) {
//        String query = "DELETE * FROM memo WHERE _id = " + ID;
        String[] temp = {ID};
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("memo", "_id = " + ID, null);
    }

    public Memo getMemo(int ID) {
        String query = "SELECT * FROM memo WHERE _id = " + ID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Memo memo = null;

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            int newID = cursor.getInt(0);
            String newMessage = cursor.getString(1);
            cursor.close();
            memo = new Memo(newMessage, newID);
        }

        db.close();
        return memo;

    }

    public ArrayList<Memo> getMemosAsList() {
        String query = "SELECT * FROM memo";

        ArrayList<Memo> memos = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                int newID = cursor.getInt(0);
                String newMessage = cursor.getString(1);
                memos.add(new Memo(newMessage, newID));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return memos;
    }
}
