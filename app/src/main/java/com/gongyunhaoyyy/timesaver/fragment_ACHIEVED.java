package com.gongyunhaoyyy.timesaver;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yuanyuanlai on 2017/12/6.
 */

public class fragment_ACHIEVED extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState){
        final View v = inflater.inflate(R.layout.fragment_achieved,parent,false);



        return v;
    }

}
