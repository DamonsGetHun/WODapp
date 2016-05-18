package com.example.sabod.testapp1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.reflect.Array;

/**
 * Created by sabod on 11/4/2015.
 */
public class FragmentLift1 extends Fragment implements View.OnClickListener{

    Button button;
    int counter = 0;
    Communicator comm;


    @Override
    public void onCreate(Bundle savedInstanceState) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lift1, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (Communicator) getActivity();
//        button = (Button) getActivity().findViewById(R.id.b_click);
//        button.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onClick(View v) {
        counter++;
        comm.respond("The button was clicked "+counter+" times");
    }
}
