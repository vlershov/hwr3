package com.example.android1.first_frame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android1.first_frame.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    public int INDEX_ID;


    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);

        TextView textnpd = view.findViewById(R.id.msgf);
        textnpd.setText("");
        sqlHelper = new DatabaseHelper(getContext());
        // подключаемся к базе
        db = sqlHelper.getWritableDatabase();
        // проверка существования записей
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_CONSTANS + " WHERE "+ DatabaseHelper.KEY_ID+ "="+String.valueOf(this.INDEX_ID);
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
        return view;
        //return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu, menu);
    }
}