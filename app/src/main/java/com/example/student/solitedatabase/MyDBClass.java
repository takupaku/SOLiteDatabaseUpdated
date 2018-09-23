package com.example.student.solitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDBClass extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "my_table";
    private static final String COL_1 = "id";
    private static final String COL_2 = "name";
    private static final String COL_3 = "area";
    private Context context;

    public MyDBClass(Context context) {//changing version will change(update,insert bla bla..) the table
        super(context, "my_db.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table " + TABLE_NAME +
                "(" + COL_1 + " integer primary key, " + COL_2 + " text, " + COL_3 + "text)";

        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            String sql = "alter table " + TABLE_NAME + " add column number text";
            sqLiteDatabase.execSQL(sql);
            Toast.makeText(context, "table updated", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            Toast.makeText(context, "table not updated ", Toast.LENGTH_SHORT).show();
        }
//        String sql = "drop table if exists " + TABLE_NAME;
//        sqLiteDatabase.execSQL(sql);
//        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String id, String name, String area) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_1, id);
        values.put(COL_2, name);
        values.put(COL_3, area);

        long result = database.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;


    }

    public Cursor viewData() {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select * from " + TABLE_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        return cursor;
    }

    public int deleteData(String id) {
        SQLiteDatabase database = getWritableDatabase();
        //return database.delete(TABLE_NAME, "where id = ?",new String[]{id});
        return database.delete(TABLE_NAME, " = ? " + COL_1, new String[]{id});
    }

    public int updateData(String id, String name, String area, String number) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, name);
        values.put(COL_3, area);
        values.put(number, number);

        return database.update(TABLE_NAME, values, " = ? " + COL_1, new String[]{id});


    }


    public Cursor searchItem(String id) {
        SQLiteDatabase database = getReadableDatabase();
        String sql= "select * from "+ TABLE_NAME+ " where id = "+ id;
        return  database.rawQuery(sql, null);
        //done

    }


}
