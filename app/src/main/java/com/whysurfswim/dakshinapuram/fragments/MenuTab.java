package com.whysurfswim.dakshinapuram.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.ads.AdView;
import com.whysurfswim.dakshinapuram.MainActivity;
import com.whysurfswim.dakshinapuram.R;

import java.util.Arrays;
import java.util.List;

public class MenuTab extends Fragment {

    private AdView adView;
    private View tabView;
    private Button soupButton, starterButton, biryaniButton, friedRiceButton, mealsButton, dosaButton, tiffinButton,
            dryButton, gravyButton, dessertsButton;
    private ListView menuListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        tabView = inflater.inflate(R.layout.menu_tab, container, false);
        adView = tabView.findViewById(R.id.adView);
        soupButton = tabView.findViewById(R.id.soupButton);
        starterButton = tabView.findViewById(R.id.starterButton);
        biryaniButton = tabView.findViewById(R.id.biryaniButton);
        friedRiceButton = tabView.findViewById(R.id.friedRiceButton);
        mealsButton = tabView.findViewById(R.id.mealsButton);
        dosaButton = tabView.findViewById(R.id.dosaButton);
        tiffinButton = tabView.findViewById(R.id.tiffinButton);
        dryButton = tabView.findViewById(R.id.dryButton);
        gravyButton = tabView.findViewById(R.id.gravyButton);
        dessertsButton = tabView.findViewById(R.id.dessertsButton);

        soupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDialog(1);
            }
        });
        starterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDialog(2);
            }
        });
        biryaniButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDialog(3);
            }
        });
        friedRiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDialog(4);
            }
        });
        mealsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDialog(5);
            }
        });
        dosaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDialog(6);
            }
        });
        tiffinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDialog(7);
            }
        });
        dryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDialog(8);
            }
        });
        gravyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDialog(9);
            }
        });
        dessertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDialog(10);
            }
        });

        ((MainActivity) getActivity()).adLoad(adView);
        return tabView;
    }

    /**
     * menuDialog is used to show the menu in a dialog
     *
     * @param menuItem seletcted menu
     */
    private void menuDialog(int menuItem) {
        final Dialog dialog = new Dialog(tabView.getContext());
        dialog.setContentView(R.layout.menu_list);
        menuListView = dialog.findViewById(R.id.menuListView);
        loadMenu(menuItem);
        dialog.show();
    }

    /**
     * loadMenu is used to load menu items
     *
     * @param menuItem menu items to be loaded
     */
    private void loadMenu(int menuItem) {
        List<String> sidemenuList;
        switch (menuItem) {
            case 1:
                sidemenuList = Arrays.asList(tabView.getResources().getStringArray(R.array.soups_array));
                break;
            case 2:
                sidemenuList = Arrays.asList(tabView.getResources().getStringArray(R.array.starters_array));
                break;
            case 3:
                sidemenuList = Arrays.asList(tabView.getResources().getStringArray(R.array.biryani_array));
                break;
            case 4:
                sidemenuList = Arrays.asList(tabView.getResources().getStringArray(R.array.fried_rice_array));
                break;
            case 5:
                sidemenuList = Arrays.asList(tabView.getResources().getStringArray(R.array.rice_array));
                break;
            case 6:
                sidemenuList = Arrays.asList(tabView.getResources().getStringArray(R.array.dosa_array));
                break;
            case 7:
                sidemenuList = Arrays.asList(tabView.getResources().getStringArray(R.array.tiffin_array));
                break;
            case 8:
                sidemenuList = Arrays.asList(tabView.getResources().getStringArray(R.array.fry_array));
                break;
            case 9:
                sidemenuList = Arrays.asList(tabView.getResources().getStringArray(R.array.gravy_array));
                break;
            case 10:
                sidemenuList = Arrays.asList(tabView.getResources().getStringArray(R.array.desserts_array));
                break;
            default:
                sidemenuList = Arrays.asList("Pls Restart app", "To load menu");
                break;
        }
        ArrayAdapter<String> arrayAdapterSidemenu = new ArrayAdapter<>(tabView.getContext(),
                android.R.layout.simple_list_item_1, sidemenuList);
        menuListView.setAdapter(arrayAdapterSidemenu);
    }
}
