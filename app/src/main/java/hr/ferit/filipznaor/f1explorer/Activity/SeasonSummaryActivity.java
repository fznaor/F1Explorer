package hr.ferit.filipznaor.f1explorer.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import hr.ferit.filipznaor.f1explorer.FragmentPagerAdapter.SeasonFragmentPagerAdapter;
import hr.ferit.filipznaor.f1explorer.R;

public class SeasonSummaryActivity extends AppCompatActivity {

    private String mSeason;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_summary);
        Intent intent = getIntent();
        mSeason = intent.getStringExtra("season");
        setUpActionBar();
        mViewPager = findViewById(R.id.viewPagerSeason);
        mTabLayout = findViewById(R.id.tabSeason);
        setUpPager(mSeason);
    }

    private void setUpActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getApplicationContext().getString(R.string.stringSeasonLabel, mSeason));
    }

    private void setUpPager(String season){
        PagerAdapter pagerAdapter = new SeasonFragmentPagerAdapter(getSupportFragmentManager(), season);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
