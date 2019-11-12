package com.leobrasileo.tp3ertrimestre;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.leobrasileo.tp3ertrimestre.Database.ProductosSQLiteHelper;

public class Estadisticas extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView textValtotProd;
    private TextView textStocktotProd;
    private TextView textcantProd;
    private TextView alarma;
    private SQLiteDatabase db;
    private ProductosSQLiteHelper pdbh;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Estadisticas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textValtotProd = findViewById(R.id.textView4);
        textStocktotProd = findViewById(R.id.textView5);
        textcantProd = findViewById(R.id.textView7);
        alarma = findViewById(R.id.textView6);

        pdbh = new ProductosSQLiteHelper(this, "MyDatabase.db", null, 1);
        db = pdbh.getWritableDatabase();
        String[] campos = new String[] {"nombre", "cantidad", "precio"};
        cursor = db.query("Productos",campos,null,null,null,null,null);
    }
}
