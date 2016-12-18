package com.bw.ynf.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.ynf.R;
import com.bw.ynf.interfaces.FragmentToFragment;
import com.bw.ynf.views.activity.ZhuYeActivity;

/**
 * Created by GaoJun on 2016/12/7 0007.
 */

public class ShopFragment extends Fragment {

    private FragmentToFragment toFragment;

    public ShopFragment(FragmentToFragment toFragment) {

        this.toFragment = toFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_shopp_car, null);
        TextView tvGuang= (TextView) view.findViewById(R.id.shopping_tv_guang);
        tvGuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFragment.shopToHome();

            }
        });

        return view;
    }
}
