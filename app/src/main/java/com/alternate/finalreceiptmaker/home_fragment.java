package com.alternate.finalreceiptmaker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class home_fragment extends Fragment implements  View.OnClickListener {
    CardView card1,card2,card3,card4,card5;


    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        card1 = v.findViewById(R.id.card1);
        card2 = v.findViewById(R.id.card2);
        card3 = v.findViewById(R.id.card3);
        card4 = v.findViewById(R.id.card4);
        card5 = v.findViewById(R.id.card5);



        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);

        return v;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.card1:
                i = new Intent(getActivity(), Ganpati_Activity.class);
                startActivity(i);
                break;

            case R.id.card2:
                i = new Intent(getActivity(),Devi_Activity.class);
                startActivity(i);
                break;
            case R.id.card3:
                i = new Intent(getActivity(),Janmashtami_Activity.class);
                startActivity(i);
                break;
            case R.id.card4:
                i = new Intent(getActivity(),Holi_Activity.class);
                startActivity(i);
                break;

            case R.id.card5:
                i = new Intent(getActivity(),School_Activity.class);
                startActivity(i);
                break;

        }
    }
}