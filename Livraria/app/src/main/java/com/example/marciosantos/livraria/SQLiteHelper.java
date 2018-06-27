package com.example.marciosantos.livraria;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String nome, SQLiteDatabase.CursorFactory factory, int version){
        super(context, nome, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public void insertDataUsuario(String nome, String telefone, String endereco, String sexo){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO USUARIO VALUES(null, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, nome);
        statement.bindString(2, telefone);
        statement.bindString(3, endereco);
        statement.bindString(4, sexo);
        statement.executeInsert();
    }

    public void updateDataUsuario(String nome, String telefone, String endereco, String sexo,  int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE USUARIO set nome = ?, telefone = ?, endereco = ?, sexo = ? WHERE id = ? ";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, nome);
        statement.bindString(2, telefone);
        statement.bindString(3, endereco);
        statement.bindString(4, sexo);
        statement.bindLong(5, (long) id);
        statement.executeInsert();
        statement.close();
    }

    public void deleteDataUsuario(int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM USUARIO WHERE id = ?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double) id);
        statement.execute();
        database.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
