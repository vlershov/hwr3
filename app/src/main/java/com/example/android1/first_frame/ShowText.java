package com.example.android1.first_frame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class ShowText  extends AppCompatActivity {

    public static final String KEY_INDEX = "Activity.fruit_idx";
    DatabaseHelper sqlHelper;
    int imageIdx;
    SQLiteDatabase db;
    Cursor userCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_show_text);

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {

            imageIdx = getIntent().getIntExtra(KEY_INDEX, -1);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            TextView textnpd = findViewById(R.id.textnpd);

            sqlHelper = new DatabaseHelper(this);
            // подключаемся к базе
            db = sqlHelper.getWritableDatabase();
            // проверка существования записей
            String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_CONSTANS + " WHERE "+ DatabaseHelper.KEY_ID+ "="+String.valueOf(imageIdx);
            userCursor = db.rawQuery(selectQuery, null);

            if (userCursor.moveToFirst()) {

                do {

                    textnpd.setText(userCursor.getString(2) + " запись от: "+userCursor.getString(3) );

                } while (userCursor.moveToNext());
            } else {
                Log.d("mLog", "0 rows");
            }

            userCursor.close();
            sqlHelper.close();

            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }
    }

    public void setArguments(Bundle args) {
        this.imageIdx = getIntent().getIntExtra(String.valueOf(args), -1);;
    }


}