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

public class FollowTab extends Fragment {

    private AdView adView;
    private Button facebookButton, twitterButton, instagramButton, whatsappButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View tabView = inflater.inflate(R.layout.follow_tab, container, false);
        adView = tabView.findViewById(R.id.adView);
        facebookButton = tabView.findViewById(R.id.facebookButton);
        twitterButton = tabView.findViewById(R.id.twitterButton);
        instagramButton = tabView.findViewById(R.id.instagramButton);
        whatsappButton = tabView.findViewById(R.id.whatsappButton);

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MainActivity) getActivity()).isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1577681315786891"));
                    if (((MainActivity) getActivity()).checkPackage(shareIntent, "com.facebook.kata")
                            || ((MainActivity) getActivity()).checkPackage(shareIntent, "com.facebook.li")) {
                        startActivity(shareIntent);
                    } else {
                        shareIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.facebook.com/DakshinaPuram/"));
                        startActivity(shareIntent);
                    }
                }
            }
        });

        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MainActivity) getActivity()).isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://twitter.com/intent/follow?user_id=961980384098463745"));
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (((MainActivity) getActivity()).checkPackage(shareIntent, "com.twitter.android")) {
                        startActivity(shareIntent);
                    } else {
                        shareIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://twitter.com/intent/follow?user_id=961980384098463745"));
                        startActivity(shareIntent);
                    }
                }
            }
        });

        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MainActivity) getActivity()).isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/_u/dakshinapuram"));
                    if (((MainActivity) getActivity()).checkPackage(shareIntent, "com.instagram.android")) {
                        startActivity(shareIntent);
                    } else {
                        shareIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://instagram.com/_u/dakshinapuram"));
                        startActivity(shareIntent);
                    }
                }
            }
        });

        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MainActivity) getActivity()).isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://chat.whatsapp.com/2lnQR35kzOlI9Nct437e06"));
                    if (((MainActivity) getActivity()).checkPackage(shareIntent, "com.whatsapp")) {
                        startActivity(shareIntent);
                    } else {
                        shareIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://chat.whatsapp.com/2lnQR35kzOlI9Nct437e06"));
                        startActivity(shareIntent);
                    }
                }
            }
        });

        ((MainActivity) getActivity()).adLoad(adView);
        return tabView;
    }
}
