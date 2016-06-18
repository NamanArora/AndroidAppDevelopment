package com.example.naman_3uwwmg4.material;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TabB extends AppCompatActivity implements Phone.OnFragmentInteractionListener,Logs.OnFragmentInteractionListener ,
Contacts.OnFragmentInteractionListener{

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_b);

        Intent intent=getIntent();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        SamplePagerAdapter adapter= new SamplePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(Contacts.newInstance("gdfsfg","dsf"));
        adapter.addFragment(Logs.newInstance("",""));
        adapter.addFragment(Phone.newInstance("",""));
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class SamplePagerAdapter extends FragmentPagerAdapter{
        @Override



        public CharSequence getPageTitle(int position) {
            return names[position];
        }
        private final String[] names={"Phone","Logs","Contacts"};
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private int COUNT = 3;

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return COUNT;
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        public void addFragment(Fragment fragment)
        {
            mFragmentList.add(fragment);
        }
    }
}
