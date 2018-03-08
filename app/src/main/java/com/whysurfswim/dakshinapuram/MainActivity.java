package com.whysurfswim.dakshinapuram;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Resources resources;
    private AdView adView;
    private Toolbar toolbar;
    private ViewFlipper splViewFlipper, payViewFlipper;
    private TextView splTextView, timeTextView, managerPhoneTextView, managerMailTextView;
    private ImageView splStarterImageView, splMainImageView;
    private FloatingActionButton facebookButton, twitterButton, whatsappButton, instagramButton, menuButton, swiggyButton,
            zomotoButton, employeeButton, aboutButton, shareButton, infoButton, locationButton;
    private RelativeLayout homePageLayout;
    private ListView listView;
    private DrawerLayout drawerLayout;
    private String imageName;
    private long doubleBack = 0;
    private boolean fabMenu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        toolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                loadHome();
            }
        }, 2000);
    }

    /**
     * loadHome method is used to load home screen
     */
    private void loadHome() {
        setContentView(R.layout.home_page);
        adView = this.findViewById(R.id.adView);
        splMainImageView = this.findViewById(R.id.mainImageView);
        splStarterImageView = this.findViewById(R.id.starterImageView);
        splTextView = this.findViewById(R.id.splDishTextView);
        timeTextView = this.findViewById(R.id.timeTextView);
        splViewFlipper = this.findViewById(R.id.splViewFlipper);
        payViewFlipper = this.findViewById(R.id.payViewFlipper);
        listView = this.findViewById(R.id.sidemenuListView);
        drawerLayout = this.findViewById(R.id.sidemenuLayout);
        employeeButton = this.findViewById(R.id.employeeButton);
        aboutButton = this.findViewById(R.id.aboutButton);
        menuButton = this.findViewById(R.id.menuButton);
        swiggyButton = this.findViewById(R.id.swiggyButton);
        zomotoButton = this.findViewById(R.id.zomatoButton);
        facebookButton = this.findViewById(R.id.facebookButton);
        twitterButton = this.findViewById(R.id.twitterButton);
        instagramButton = this.findViewById(R.id.instagramButton);
        whatsappButton = this.findViewById(R.id.whatsappButton);
        shareButton = this.findViewById(R.id.shareButton);
        infoButton = this.findViewById(R.id.infoButton);
        locationButton = this.findViewById(R.id.locationButton);
        homePageLayout = this.findViewById(R.id.homePageLayout);
        addSidemenuItems();
        closeFloatingButtons();
        int today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        int currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (currentTime >= 7 && currentTime < 8) {
            homePageLayout.setBackground(resources.getDrawable(R.drawable.coffeetime));
            timeTextView.setText(resources.getString(R.string.coffee_time));
        } else if (currentTime >= 8 && currentTime < 11) {
            homePageLayout.setBackground(resources.getDrawable(R.drawable.breakfasttime));
            timeTextView.setText(resources.getString(R.string.breakfast_time));
        } else if (currentTime >= 11 && currentTime < 12) {
            homePageLayout.setBackground(resources.getDrawable(R.drawable.cookingtime2));
            timeTextView.setText(resources.getString(R.string.cooking_time2));
        } else if (currentTime >= 12 && currentTime < 15) {
            homePageLayout.setBackground(resources.getDrawable(R.drawable.lunchtime));
            timeTextView.setText(resources.getString(R.string.lunch_time));
        } else if (currentTime >= 15 && currentTime < 17) {
            homePageLayout.setBackground(resources.getDrawable(R.drawable.cookingtime));
            timeTextView.setText(resources.getString(R.string.cooking_time));
        } else if (currentTime >= 17 && currentTime < 19) {
            homePageLayout.setBackground(resources.getDrawable(R.drawable.teatime));
            timeTextView.setText(resources.getString(R.string.tea_time));
        } else if (currentTime >= 19 && currentTime < 23) {
            homePageLayout.setBackground(resources.getDrawable(R.drawable.dinnertime));
            timeTextView.setText(resources.getString(R.string.dinner_time));
        } else {
            homePageLayout.setBackground(resources.getDrawable(R.drawable.closedtime));
            timeTextView.setText(resources.getString(R.string.closed_time));
        }
        switch (today) {
            case Calendar.MONDAY:
                splMainImageView.setImageResource(R.drawable.mon_spl_main);
                splStarterImageView.setImageResource(R.drawable.mon_spl_starter);
                splTextView.setText(resources.getString(R.string.spl_starter_mon) + '\n' + "&" + '\n' + resources
                        .getString(R.string.spl_main_mon));
                break;
            case Calendar.TUESDAY:
                splMainImageView.setImageResource(R.drawable.tue_spl_main);
                splStarterImageView.setImageResource(R.drawable.tue_spl_starter);
                splTextView.setText(resources.getString(R.string.spl_starter_tue) + '\n' + "&" + '\n' + resources
                        .getString(R.string.spl_main_tue));
                break;
            case Calendar.WEDNESDAY:
                splMainImageView.setImageResource(R.drawable.wed_spl_main);
                splStarterImageView.setImageResource(R.drawable.wed_spl_starter);
                splTextView.setText(resources.getString(R.string.spl_starter_wed) + '\n' + "&" + '\n' + resources
                        .getString(R.string.spl_main_wed));
                break;
            case Calendar.THURSDAY:
                splMainImageView.setImageResource(R.drawable.thu_spl_main);
                splStarterImageView.setImageResource(R.drawable.thu_spl_starter);
                splTextView.setText(resources.getString(R.string.spl_starter_thu) + '\n' + "&" + '\n' + resources
                        .getString(R.string.spl_main_thu));
                break;
            case Calendar.FRIDAY:
                splMainImageView.setImageResource(R.drawable.fri_spl_main);
                splStarterImageView.setImageResource(R.drawable.fri_spl_starter);
                splTextView.setText(resources.getString(R.string.spl_starter_fri) + '\n' + "&" + '\n' + resources
                        .getString(R.string.spl_main_fri));
                break;
            case Calendar.SATURDAY:
                splMainImageView.setImageResource(R.drawable.sat_spl_main);
                splStarterImageView.setImageResource(R.drawable.sat_spl_starter);
                splTextView.setText(resources.getString(R.string.spl_starter_sat) + '\n' + "&" + '\n' + resources
                        .getString(R.string.spl_main_sat));
                break;
            case Calendar.SUNDAY:
                splMainImageView.setImageResource(R.drawable.sun_spl_main);
                splStarterImageView.setImageResource(R.drawable.sun_spl_starter);
                splTextView.setText(resources.getString(R.string.spl_starter_sun) + '\n' + "&" + '\n' + resources
                        .getString(R.string.spl_main_sun));
                break;
            default:
                break;
        }
        splViewFlipper.setFlipInterval(3000);
        splViewFlipper.animate();
        splViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        splViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
        splViewFlipper.startFlipping();
        payViewFlipper.setFlipInterval(2000);
        payViewFlipper.animate();
        payViewFlipper.startFlipping();
        adLoad();
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                closeFloatingButtons();
                infoButton.hide();
                menuButton.hide();
                facebookButton.hide();
                twitterButton.hide();
                instagramButton.hide();
                whatsappButton.hide();
                swiggyButton.hide();
                zomotoButton.hide();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                infoButton.show();
                menuButton.show();
                facebookButton.show();
                twitterButton.show();
                instagramButton.show();
                whatsappButton.show();
                swiggyButton.show();
                zomotoButton.show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        drawerLayout.closeDrawer(Gravity.START, true);
                        break;
                    case 1:
                        menuDialog(R.drawable.menu_soup);
                        break;
                    case 2:
                        menuDialog(R.drawable.menu_starters);
                        break;
                    case 3:
                        menuDialog(R.drawable.menu_rice);
                        break;
                    case 4:
                        menuDialog(R.drawable.menu_dosa1);
                        break;
                    case 5:
                        menuDialog(R.drawable.menu_dosa2);
                        break;
                    case 6:
                        menuDialog(R.drawable.menu_gravy1);
                        break;
                    case 7:
                        menuDialog(R.drawable.menu_gravy2);
                        break;
                    case 8:
                        menuDialog(R.drawable.menu_desserts);
                        break;
                    case 9:
                        menuDialog(R.drawable.menu_ice_cream);
                        break;
                    default:
                        drawerLayout.closeDrawer(Gravity.START, true);
                        break;
                }
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START, true);
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1577681315786891"));
                    if (checkPackage(shareIntent, "com.facebook.kata")
                            || checkPackage(shareIntent, "com.facebook.li")) {
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
                if (isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://twitter.com/intent/follow?user_id=961980384098463745"));
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (checkPackage(shareIntent, "com.twitter.android")) {
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
                if (isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/_u/dakshinapuram"));
                    if (checkPackage(shareIntent, "com.instagram.android")) {
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
                if (isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://chat.whatsapp.com/2lnQR35kzOlI9Nct437e06"));
                    if (checkPackage(shareIntent, "com.whatsapp")) {
                        startActivity(shareIntent);
                    } else {
                        shareIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://chat.whatsapp.com/2lnQR35kzOlI9Nct437e06"));
                        startActivity(shareIntent);
                    }
                }
            }
        });

        swiggyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.swiggy.com/chennai/dakshina-puram-karappakam"));
                    if (checkPackage(shareIntent, "in.swiggy")) {
                        startActivity(shareIntent);
                    } else {
                        shareIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.swiggy.com/chennai/dakshina-puram-karappakam"));
                        startActivity(shareIntent);
                    }
                }
            }
        });

        zomotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://zoma.to/r/72165"));
                    if (checkPackage(shareIntent, "com.application.zomato")) {
                        startActivity(shareIntent);
                    } else {
                        shareIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://zoma.to/r/72165"));
                        startActivity(shareIntent);
                    }
                }
            }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.co.in/maps/dir/My+location/Dakshina+Puram," +
                                    "+No.14,+Old+MahabaliPuram+Road,+Opp+to+Tcs," +
                                    "+Karapakkam,+Chennai,+Tamil+Nadu+600097"));
                    if (checkPackage(shareIntent, "com.google.android.apps.maps")) {
                        startActivity(shareIntent);
                    } else {
                        shareIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.google.co.in/maps/dir/My+location/Dakshina+Puram," +
                                        "+No.14,+Old+MahabaliPuram+Road,+Opp+to+Tcs," +
                                        "+Karapakkam,+Chennai,+Tamil+Nadu+600097"));
                        startActivity(shareIntent);
                    }
                }
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutDialog();
            }
        });

        employeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeeDialog();
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabMenu) {
                    openFloatingButtons();
                } else {
                    closeFloatingButtons();
                }
            }
        });

    }

    /**
     * captureImage is used to take picture from camera
     */
    private void captureImage() {

        // Creating folders for Image
        String imageFolderPath = Environment.getExternalStorageDirectory().toString()
                + "/Dakshinapuram";
        File imagesFolder = new File(imageFolderPath);
        imagesFolder.mkdirs();

        // Generating file name
        imageName = Calendar.getInstance().get(Calendar.DATE) + Calendar.getInstance().getTime().toString() + ".png";

        // Creating image here
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imageFolderPath, imageName)));
        startActivityForResult(takePictureIntent, 101);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK && requestCode == 101) {
            Toast.makeText(this, "Sharing pic on Facebook",
                    Toast.LENGTH_SHORT).show();
            MediaScannerConnection.scanFile(this, new String[]{new File(Environment.getExternalStorageDirectory()
                    + "/Dakshinapuram/" + imageName).getPath()}, new String[]{"image/png"}, null);


            this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(Environment
                    .getExternalStorageDirectory() + "/Dakshinapuram/" + imageName)));

            if (isOnline()) {
                ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                        .setShareHashtag(new ShareHashtag.Builder()
                                .setHashtag("#DakshinaPuram")
                                .build())
                        .build();
                ShareDialog.show(this, shareLinkContent);
            }
        } else {
            Toast.makeText(this, "Take picture failed or canceled",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * adLoad method is used to load the advertisements
     */
    private void adLoad() {
        if (isOnline()) {
            MobileAds.initialize(this, "ca-app-pub-6059528612565667/3631467294");
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }
    }

    /**
     * menuDialog is used to show the menu in a dialog
     *
     * @param menuItem seletcted menu
     */
    private void menuDialog(int menuItem) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.menu_list);
        ImageView menuImageView = dialog.findViewById(R.id.menuImageView);
        Button dialogButton = dialog.findViewById(R.id.closeDialogButton);
        menuImageView.setImageResource(menuItem);
        drawerLayout.closeDrawer(Gravity.START, true);
        splViewFlipper.stopFlipping();
        payViewFlipper.stopFlipping();
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                splViewFlipper.startFlipping();
                payViewFlipper.startFlipping();
            }
        });
        dialog.show();
    }

    /**
     * employeeDialog is used to show the employe details in a dialog
     */
    private void employeeDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.employee_list);
        managerPhoneTextView = dialog.findViewById(R.id.managerPhoneTextView);
        managerMailTextView = dialog.findViewById(R.id.managerMailTextView);
        Button dialogButton = dialog.findViewById(R.id.closeEmployeeDialogButton);
        drawerLayout.closeDrawer(Gravity.START, true);
        splViewFlipper.stopFlipping();
        payViewFlipper.stopFlipping();
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                splViewFlipper.startFlipping();
                payViewFlipper.startFlipping();
            }
        });
        managerPhoneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:91 9566188815"));
                startActivity(callIntent);
            }
        });
        managerMailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:vsganesan2k@gmail.com"));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.email_inquiry));
                    startActivity(Intent.createChooser(emailIntent, resources.getString(R.string.email_chooser)));
                }
            }
        });
        dialog.show();
    }

    /**
     * aboutDialog is used to show the about page in a dialog
     */
    private void aboutDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.about_page);
        TextView wssTextView = dialog.findViewById(R.id.wssTextView);
        Button dialogButton = dialog.findViewById(R.id.closeAboutDialogButton);
        splViewFlipper.stopFlipping();
        payViewFlipper.stopFlipping();
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                splViewFlipper.startFlipping();
                payViewFlipper.startFlipping();
            }
        });
        wssTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    Intent shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=com.whysurfswim.wss&hl=en"));
                    startActivity(shareIntent);
                }
            }
        });
        dialog.show();
    }

    /**
     * closeFloatingButtons is used to hide buttons in fab menu
     */
    private void closeFloatingButtons() {
        infoButton.setImageDrawable(resources.getDrawable(R.drawable.ic_home_black_24dp));
        fabMenu = true;
        locationButton.hide();
        aboutButton.hide();
        employeeButton.hide();
        shareButton.hide();
    }

    /**
     * openFloatingButtons is used to show buttons in fab menu
     */
    private void openFloatingButtons() {
        infoButton.setImageDrawable(resources.getDrawable(R.drawable.ic_close_black_24dp));
        fabMenu = false;
        locationButton.show();
        aboutButton.show();
        employeeButton.show();
        shareButton.show();
    }

    /**
     * addSidemenuItems is used to add items in side menu
     */
    private void addSidemenuItems() {
        List<String> sidemenuList = Arrays.asList("Home", "Soups", "Starters", "Rice", "Dosa-1", "Dosa-2",
                "Gravy-1", "Gravy-2", "Desserts", "Ice-Creams");
        ArrayAdapter<String> arrayAdapterSidemenu = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, sidemenuList);
        listView.setAdapter(arrayAdapterSidemenu);
    }

    /**
     * isOnline method is used to check the status of internet connection
     *
     * @return boolean
     */
    private boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        if (conMgr != null) {
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
            if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
                Toast.makeText(this, resources.getString(R.string.no_internet_text), Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    /**
     * checkPackage method is used to check for installed apps in phone
     *
     * @param intent     {intent variant to load package}
     * @param appPackage {app folder path}
     * @return boolean
     */
    private boolean checkPackage(Intent intent, String appPackage) {
        List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().contains(appPackage)) {
                intent.setPackage(info.activityInfo.packageName);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBack + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            doubleBack = System.currentTimeMillis();
            Toast.makeText(this, resources.getString(R.string.backbutton_exit), Toast.LENGTH_SHORT).show();
        }
    }
}
