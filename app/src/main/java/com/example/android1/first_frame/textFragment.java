package com.example.android1.first_frame;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class textFragment extends Fragment {

    public static final String ARG_FRUIT_IDX = "FruitFragment.fruit_idx";

    private int mFruitIdx;

    public textFragment() {
        // Required empty public constructor
    }


    public static textFragment newInstance(int fruitIdx) {
        textFragment fragment = new textFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_FRUIT_IDX, fruitIdx);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFruitIdx = getArguments().getInt(ARG_FRUIT_IDX, -1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //   TypedArray img = getResources().obtainTypedArray(R.array.fruit_imgs);
        //   AppCompatImageView imageView = view.findViewById(R.id.fruit_image);
        //  imageView.setImageResource(img.getResourceId(mFruitIdx, -1));
        //  img.recycle();
    }
}
