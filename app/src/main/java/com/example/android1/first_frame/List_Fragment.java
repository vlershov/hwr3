package com.example.android1.first_frame;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class List_Fragment extends Fragment {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    static int POZISION_POZ = 0;
    private int mCurrentImageIdx = -1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_list_, container, false);

            sqlHelper = new DatabaseHelper(getActivity());
            // подключаемся к базе
            db = sqlHelper.getWritableDatabase();
            // проверка существования записей
            userCursor = db.query(DatabaseHelper.TABLE_CONSTANS, null, null, null, null, null, null);

            RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lines);
            String[][] data = new String[userCursor.getCount()][2];
            int i = 0;
            if (userCursor.moveToFirst()) {

                do {

                    data[i][0] = userCursor.getString(0);
                    data[i][1] = userCursor.getString(1);
                    ++i;

                } while (userCursor.moveToNext());
            } else {
                Log.d("mLog", "0 rows");
            }

            userCursor.close();
            sqlHelper.close();
            initRecyclerView(recyclerView, data);

        return view;
    }

    private void initRecyclerView(RecyclerView recyclerView, String[][] data){

        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        SocialNetworkAdapter adapter = new SocialNetworkAdapter(data);
        adapter.setItemClickListener((view, position) -> {
            if (getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_PORTRAIT) {
                goToSeparateActivity(++position);
            }else{

                showToTheRight(++position);
            }
        });
        recyclerView.setAdapter(adapter);

    }

    private void showToTheRight (int imageIdx) {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment1 frag1 = new Fragment1();

        frag1.INDEX_ID = imageIdx;
        transaction.add(R.id.fragment_container, frag1);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private void setCurrentImageIdx(int imageIdx) {
        mCurrentImageIdx = imageIdx;
    }

    private void goToSeparateActivity(int imageIdx) {
        Intent intent = new Intent(getActivity(), ShowText.class);
        intent.putExtra(ShowText.KEY_INDEX, imageIdx);
        startActivity(intent);
    }


}