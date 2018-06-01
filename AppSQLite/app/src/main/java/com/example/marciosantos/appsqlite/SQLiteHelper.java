package com.example.marciosantos.appsqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    SQLiteHelper(Context context, String nome, SQLiteDatabase.CursorFactory factory, int version){
        super(context, nome, factory, version);
    }

    public void queryData(String sql){
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL(sql);
    }

    public void insertData(String nome, String quantidade, String tipo){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO CERVEJA VALUES(null, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, nome);
        statement.bindString(2, quantidade);
        statement.bindString(3, tipo);
        statement.executeInsert();
    }

    public void updateData(String nome, String quantidade, String tipo, int id){
            SQLiteDatabase database = getWritableDatabase();
            String sql = "UPDATE CERVEJA SET nome = ?, quantidade = ?, tipo = ?";

            SQLiteStatement statement = database.compileStatement(sql);
            statement.bindString(1, nome);
            statement.bindString(2, quantidade);
            statement.bindString(3, tipo);
            statement.bindDouble(4, (double) id);
            statement.executeInsert();
            statement.close();
    }

    public void deleteData(int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM CERVEJA WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.clearBindings();
        statement.bindDouble(1, (double) id);
        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
