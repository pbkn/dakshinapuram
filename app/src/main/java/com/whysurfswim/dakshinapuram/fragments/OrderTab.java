package com.whysurfswim.dakshinapuram.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdView;
import com.whysurfswim.dakshinapuram.MainActivity;
import com.whysurfswim.dakshinapuram.R;

public class OrderTab extends Fragment {

    private AdView adView;
    private Button swiggyButton, zomatoButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View tabView = inflater.inflate(R.layout.order_tab, container, false);
        adView = tabView.findViewById(R.id.adView);
        swiggyButton = tabView.findViewById(R.id.swiggyButton);
        zomatoButton = tabView.findViewById(R.id.zomatoButton);

        swiggyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MainActivity) getActivity()).isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.swiggy.com/chennai/dakshina-puram-karappakam"));
                    if (((MainActivity) getActivity()).checkPackage(shareIntent, "in.swiggy")) {
                        startActivity(shareIntent);
                    } else {
                        shareIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.swiggy.com/chennai/dakshina-puram-karappakam"));
                        startActivity(shareIntent);
                    }
                }
            }
        });

        zomatoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MainActivity) getActivity()).isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://zoma.to/r/72165"));
                    if (((MainActivity) getActivity()).checkPackage(shareIntent, "com.application.zomato")) {
                        startActivity(shareIntent);
                    } else {
                        shareIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://zoma.to/r/72165"));
                        startActivity(shareIntent);
                    }
                }
            }
        });
        ((MainActivity) getActivity()).adLoad(adView);
        return tabView;
    }
}
