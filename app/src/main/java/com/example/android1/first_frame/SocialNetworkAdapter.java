package com.example.android1.first_frame;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class SocialNetworkAdapter
        extends RecyclerView.Adapter<SocialNetworkAdapter.ViewHolder> {

    private Activity context;
    private String[][] dataSource;
    private ItemClickListener itemClickListener;


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onClick(View view, int i);
    }
    // Передаём в конструктор источник данных
    // В нашем случае это массив, но может быть и запрос к БД
    public SocialNetworkAdapter(String[][] dataSource) {
        this.dataSource = dataSource;
    }

    // Создать новый элемент пользовательского интерфейса
    // Запускается менеджером
    @NonNull
    @Override
    public SocialNetworkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Создаём новый элемент пользовательского интерфейса
        // Через Inflater

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    // Заменить данные в пользовательском интерфейсе
    // Вызывается менеджером
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // Получить элемент из источника данных (БД, интернет...)
        // Вынести на экран, используя ViewHolder
        if(viewHolder.textViewID != null) {
            viewHolder.textViewID.setText(dataSource[i][0]);
            viewHolder.textView.setText(dataSource[i][1]);
            viewHolder.textView.setOnClickListener(v -> {
                if (itemClickListener != null)
                    itemClickListener.onClick(viewHolder.textView, i);
            });
        }
    }

    // Вернуть размер данных, вызывается менеджером
    @Override
    public int getItemCount() {
        return dataSource.length;
    }


    // Интерфейс для обработки нажатий, как в ListView
    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    // Этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на один пункт списка
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView textViewID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.textView2);
            textView = itemView.findViewById(R.id.textView3);

        }

        public TextView getTextView() {
            return textView;
        }
        public TextView getTextViewID() {
            return textViewID;
        }
    }


}

