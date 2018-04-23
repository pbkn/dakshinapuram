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
import android.widget.ImageView;

import com.google.android.gms.ads.AdView;
import com.whysurfswim.dakshinapuram.MainActivity;
import com.whysurfswim.dakshinapuram.R;

public class ContactTab extends Fragment {

    private AdView adView;
    private Button phoneButton, mailButton;
    private ImageView mapImage;
    private String mapUrl = "https://www.google.co.in/maps/dir/my location/Dakshina+Puram,+No.14," +
            "+Old+MahabaliPuram+Road,+Opp+to+Tcs,+Karapakkam,+Chennai,+Tamil+Nadu/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View tabView = inflater.inflate(R.layout.contact_tab, container, false);
        adView = tabView.findViewById(R.id.adView);
        mapImage = tabView.findViewById(R.id.mapImageView);
        mailButton = tabView.findViewById(R.id.mailButton);
        phoneButton = tabView.findViewById(R.id.phoneButton);

        mapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MainActivity) getActivity()).isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl));
                    if (((MainActivity) getActivity()).checkPackage(shareIntent, "com.google.android.apps.maps")) {
                        startActivity(shareIntent);
                    } else {
                        shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl));
                        startActivity(shareIntent);
                    }
                }
            }
        });

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:91 9629834444"));
                startActivity(callIntent);
            }
        });

        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MainActivity) getActivity()).isOnline()) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:info@shyamshospitality," +
                            "contactpbkn@whysurfswim.com,contactlakshadeep@whysurfswim.com"));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.email_inquiry));
                    startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.email_chooser)));
                }
            }
        });

        ((MainActivity) getActivity()).adLoad(adView);
        return tabView;
    }

}
