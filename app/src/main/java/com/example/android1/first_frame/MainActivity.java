package com.example.android1.first_frame;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toolbar;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        db = databaseHelper .getWritableDatabase();
        databaseHelper.onCreate(db);
        //  databaseHelper.create_db(db,"Иванов","2020-12-30","Запись блога");
        //  databaseHelper.create_db(db,"Петров","2021-01-30","Запись в блог");
        //  databaseHelper.create_db(db,"Сидоров","2021-02-12","Даныне");

    }


}