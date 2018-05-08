package com.whysurfswim.dakshinapuram;import android.app.Dialog;import android.content.Intent;import android.content.pm.ActivityInfo;import android.content.pm.ResolveInfo;import android.content.res.Resources;import android.net.ConnectivityManager;import android.net.NetworkInfo;import android.net.Uri;import android.os.Bundle;import android.os.Handler;import android.support.design.widget.NavigationView;import android.support.design.widget.TabLayout;import android.support.v4.app.Fragment;import android.support.v4.app.FragmentManager;import android.support.v4.app.FragmentPagerAdapter;import android.support.v4.view.GravityCompat;import android.support.v4.view.ViewPager;import android.support.v4.widget.DrawerLayout;import android.support.v7.app.AppCompatActivity;import android.support.v7.widget.Toolbar;import android.view.MenuItem;import android.view.View;import android.widget.TextView;import android.widget.Toast;import com.google.android.gms.ads.AdRequest;import com.google.android.gms.ads.AdView;import com.google.android.gms.ads.MobileAds;import com.whysurfswim.dakshinapuram.fragments.ContactTab;import com.whysurfswim.dakshinapuram.fragments.FollowTab;import com.whysurfswim.dakshinapuram.fragments.GamesTab;import com.whysurfswim.dakshinapuram.fragments.MenuTab;import com.whysurfswim.dakshinapuram.fragments.OffersTab;import com.whysurfswim.dakshinapuram.fragments.OrderTab;import com.whysurfswim.dakshinapuram.fragments.TodaysSplTab;import java.util.ArrayList;import java.util.Calendar;import java.util.List;public class MainActivity extends AppCompatActivity {    private Resources resources;    private Toolbar toolbar, menuToolbar;    private TextView timeTextView;    private ViewPager viewPager;    private TabLayout tabLayout;    private DrawerLayout sidemenuLayout;    private NavigationView sidemenuView;    private long doubleBack = 0;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        resources = getResources();        setContentView(R.layout.activity_main);        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);        toolbar = this.findViewById(R.id.toolbar);        setSupportActionBar(toolbar);        if (getSupportActionBar() != null) {            getSupportActionBar().setDisplayHomeAsUpEnabled(true);            getSupportActionBar().setDisplayShowHomeEnabled(true);        }        new Handler().postDelayed(new Runnable() {            public void run() {                loadHome();            }        }, 2000);    }    @Override    public void onBackPressed() {        if (doubleBack + 2000 > System.currentTimeMillis()) {            super.onBackPressed();        } else {            doubleBack = System.currentTimeMillis();            Toast.makeText(this, resources.getString(R.string.backbutton_exit), Toast.LENGTH_SHORT).show();        }    }    @Override    public boolean onOptionsItemSelected(MenuItem item) {        switch (item.getItemId()) {            case android.R.id.home:                if (sidemenuLayout.isDrawerOpen(GravityCompat.START)) {                    sidemenuLayout.closeDrawers();                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);                } else {                    sidemenuLayout.openDrawer(GravityCompat.START);                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);                }                return true;        }        return super.onOptionsItemSelected(item);    }    /**     * loadHome method is used to load home screen     */    private void loadHome() {        setContentView(R.layout.home_page);        timeTextView = this.findViewById(R.id.timeTextView);        sidemenuLayout = this.findViewById(R.id.sidemenuDrawerLayout);        sidemenuView = this.findViewById(R.id.sidemenuNavigationView);        menuToolbar = this.findViewById(R.id.menuToolbar);        tabLayout = findViewById(R.id.tabLayout);        setSupportActionBar(menuToolbar);        getSupportActionBar().setDisplayHomeAsUpEnabled(true);        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);        sidemenuLayout.addDrawerListener(                new DrawerLayout.DrawerListener() {                    @Override                    public void onDrawerSlide(View drawerView, float slideOffset) {                        tabLayout.setEnabled(false);                    }                    @Override                    public void onDrawerOpened(View drawerView) {                        tabLayout.setEnabled(false);                        unCheckSidemenu();                        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);                    }                    @Override                    public void onDrawerClosed(View drawerView) {                        tabLayout.setEnabled(true);                        unCheckSidemenu();                        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);                    }                    @Override                    public void onDrawerStateChanged(int newState) {                        // Respond when the drawer motion state changes                    }                }        );        sidemenuView.setNavigationItemSelectedListener(                new NavigationView.OnNavigationItemSelectedListener() {                    @Override                    public boolean onNavigationItemSelected(MenuItem menuItem) {                        switch (menuItem.getTitle().toString()) {                            case "OFFERS":                                tabLayout.getTabAt(0).select();                                break;                            case "TODAY'S SPL":                                tabLayout.getTabAt(1).select();                                break;                            case "MENU":                                tabLayout.getTabAt(2).select();                                break;                            case "ORDER ONLINE":                                tabLayout.getTabAt(3).select();                                break;                            case "FOLLOW US":                                tabLayout.getTabAt(4).select();                                break;                            case "CONTACT US":                                tabLayout.getTabAt(5).select();                                break;                            case "MINI GAMES":                                tabLayout.getTabAt(6).select();                                break;                            case "ABOUT":                                loadAbout();                                break;                            case "REVIEW":                                Intent shareIntent = new Intent(Intent.ACTION_VIEW,                                        Uri.parse("https://play.google.com/store/apps/details?id=com.whysurfswim.dakshinapuram"));                                if (checkPackage(shareIntent, "com.android.vending")) {                                    startActivity(shareIntent);                                } else {                                    Intent viewIntent = new Intent(Intent.ACTION_VIEW,                                            Uri.parse("https://play.google.com/store/apps/details?id=com.whysurfswim.dakshinapuram"));                                    startActivity(viewIntent);                                }                                break;                            case "SUBSCRIBE":                                Intent viewIntent = new Intent(Intent.ACTION_VIEW,                                        Uri.parse("https://goo.gl/forms/3ZMvC0ya4egqxTh42"));                                startActivity(viewIntent);                                break;                            default:                                tabLayout.getTabAt(3).select();                                break;                        }                        sidemenuLayout.closeDrawers();                        menuItem.setChecked(false);                        return true;                    }                });        int currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);        if (currentTime >= 7 && currentTime < 8) {            timeTextView.setText(resources.getString(R.string.coffee_time));        } else if (currentTime >= 8 && currentTime < 11) {            timeTextView.setText(resources.getString(R.string.breakfast_time));        } else if (currentTime >= 11 && currentTime < 12) {            timeTextView.setText(resources.getString(R.string.cooking_time2));        } else if (currentTime >= 12 && currentTime < 15) {            timeTextView.setText(resources.getString(R.string.lunch_time));        } else if (currentTime >= 15 && currentTime < 17) {            timeTextView.setText(resources.getString(R.string.cooking_time));        } else if (currentTime >= 17 && currentTime < 19) {            timeTextView.setText(resources.getString(R.string.tea_time));        } else if (currentTime >= 19 && currentTime < 23) {            timeTextView.setText(resources.getString(R.string.dinner_time));        } else {            timeTextView.setText(resources.getString(R.string.closed_time));        }        viewPager = findViewById(R.id.viewPager);        setupViewPager(viewPager);        tabLayout.setupWithViewPager(viewPager);        tabLayout.getTabAt(0).setIcon(resources.getDrawable(R.drawable.ic_local_offer_black_24dp));        tabLayout.getTabAt(1).setIcon(resources.getDrawable(R.drawable.ic_restaurant_menu_black_24dp));        tabLayout.getTabAt(2).setIcon(resources.getDrawable(R.drawable.ic_import_contacts_black_24dp));        tabLayout.getTabAt(3).setIcon(resources.getDrawable(R.drawable.ic_phone_android_black_24dp));        tabLayout.getTabAt(4).setIcon(resources.getDrawable(R.drawable.ic_photo_filter_black_24dp));        tabLayout.getTabAt(5).setIcon(resources.getDrawable(R.drawable.ic_place_black_24dp));        tabLayout.getTabAt(6).setIcon(resources.getDrawable(R.drawable.ic_videogame_asset_black_24dp));        tabLayout.getTabAt(2).select();    }    /**     * loadAbout method used to load about dialog     */    private void loadAbout() {        final Dialog dialog = new Dialog(this);        dialog.setContentView(R.layout.about);        TextView wssText = dialog.findViewById(R.id.whysurfswimTextView);        wssText.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://whysurfswim.com/"));                startActivity(viewIntent);            }        });        dialog.show();    }    /**     * unCheckSidemenu method used to uncheck all sidemenu items selected     */    private void unCheckSidemenu() {        int size = sidemenuView.getMenu().size();        for (int i = 0; i < size; i++) {            sidemenuView.getMenu().getItem(i).setChecked(false);        }    }    /**     * setupViewPager sets up the tab layout fragments     *     * @param viewPager linking tabs with xml     */    private void setupViewPager(ViewPager viewPager) {        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());        adapter.addFragment(new OffersTab(), "OFFERS");        adapter.addFragment(new TodaysSplTab(), "TODAY'S SPL");        adapter.addFragment(new MenuTab(), "MENU");        adapter.addFragment(new OrderTab(), "ORDER ONLINE");        adapter.addFragment(new FollowTab(), "FOLLOW US");        adapter.addFragment(new ContactTab(), "CONTACT US");        adapter.addFragment(new GamesTab(), "MINI GAMES");        viewPager.setAdapter(adapter);    }    /**     * isOnline method is used to check the status of internet connection     *     * @return boolean     */    public boolean isOnline() {        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);        if (conMgr != null) {            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();            if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {                Toast.makeText(this, resources.getString(R.string.no_internet_text), Toast.LENGTH_LONG).show();                return false;            }        }        return true;    }    /**     * checkPackage method is used to check for installed apps in phone     *     * @param intent     {intent variant to load package}     * @param appPackage {app folder path}     * @return boolean     */    public boolean checkPackage(Intent intent, String appPackage) {        List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);        for (ResolveInfo info : matches) {            if (info.activityInfo.packageName.toLowerCase().contains(appPackage)) {                intent.setPackage(info.activityInfo.packageName);                return true;            }        }        return false;    }    /**     * adLoad method is used to load the advertisements     *     * @param adView the ad view component     */    public void adLoad(AdView adView) {        if (isOnline()) {            MobileAds.initialize(this, "ca-app-pub-6059528612565667/3631467294");            AdRequest adRequest = new AdRequest.Builder().build();            adView.loadAd(adRequest);        }    }    /**     * ViewPagerAdapter class to set up tab layout     */    class ViewPagerAdapter extends FragmentPagerAdapter {        private final List<Fragment> mFragmentList = new ArrayList<>();        private final List<String> mFragmentTitleList = new ArrayList<>();        private ViewPagerAdapter(FragmentManager manager) {            super(manager);        }        @Override        public Fragment getItem(int position) {            return mFragmentList.get(position);        }        @Override        public int getCount() {            return mFragmentList.size();        }        private void addFragment(Fragment fragment, String title) {            mFragmentList.add(fragment);            mFragmentTitleList.add(title);        }        @Override        public CharSequence getPageTitle(int position) {            return mFragmentTitleList.get(position);        }    }}