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

    public void insertDataLivraria(String nome, String evento, String endereco, Integer id_localizacao){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO LIVRARIA VALUES(null, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, nome);
        statement.bindString(2, evento);
        statement.bindString(3, endereco);
        statement.bindLong(4, id_localizacao);
        statement.executeInsert();
    }

    public void insertDataUsuario(String nome, String endereco, String telefone){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO USUARIO VALUES(null, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, nome);
        statement.bindString(2, endereco);
        statement.bindString(3, telefone);
        statement.executeInsert();
    }

    public void updateDataLivraria(String nome, String evento, String endereco, Integer id_localizacao, int id){
            SQLiteDatabase database = getWritableDatabase();
            String sql = "UPDATE LIVRARIA SET nome = ?, evento = ?, endereco = ?, id_localizacao = ? WHERE id = ?";

            SQLiteStatement statement = database.compileStatement(sql);
            statement.bindString(1, nome);
            statement.bindString(2, evento);
            statement.bindString(3, endereco);
            statement.bindLong(4, id_localizacao);
            statement.bindDouble(5, (double) id);
            statement.executeInsert();
            statement.close();
    }

    public void deleteDataLivraria(int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM LIVRARIA WHERE id = ?";
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
