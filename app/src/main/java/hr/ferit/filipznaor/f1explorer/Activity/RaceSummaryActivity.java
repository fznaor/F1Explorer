package hr.ferit.filipznaor.f1explorer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import hr.ferit.filipznaor.f1explorer.API.CountryParser;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.FragmentPagerAdapter.RaceFragmentPagerAdapter;

public class RaceSummaryActivity extends AppCompatActivity {

    private int mSeason;
    private String mCircuitId, mCircuitName, mRaceName, mCountry;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_summary);
        Intent intent = getIntent();
        mSeason = intent.getIntExtra("season", 0);
        mCircuitId = intent.getStringExtra("id");
        mCircuitName = intent.getStringExtra("circuit");
        mRaceName = intent.getStringExtra("race");
        mCountry = intent.getStringExtra("country");
        setUpCircuitName();
        mViewPager = findViewById(R.id.viewPagerRace);
        mTabLayout = findViewById(R.id.tabRace);
        setUpToolbar();
        setUpPager(mSeason, mCircuitId);
    }

    private void setUpCircuitName(){
        TextView tvCircuitName = findViewById(R.id.tvRaceSummaryCircuit);
        tvCircuitName.setText(mCircuitName);
        tvCircuitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), CircuitInfoActivity.class);
                myIntent.putExtra("id", mCircuitId);
                myIntent.putExtra("country", mCountry);
                myIntent.putExtra("name", mCircuitName);
                v.getContext().startActivity(myIntent);
            }
        });
    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbarRaceSummary);
        toolbar.setTitle(String.valueOf(mSeason) + ' ' + mRaceName);
        setSupportActionBar(toolbar);
        ImageView ivCountry = findViewById(R.id.ivRaceSummaryCountry);
        Picasso.get().load("https://www.countryflags.io/" + CountryParser.getCountryCode(mCountry) + "/flat/64.png").resize(80,80).into(ivCountry);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), SeasonSummaryActivity.class);
                myIntent.putExtra("season", String.valueOf(mSeason));
                v.getContext().startActivity(myIntent);
            }
        });
    }

    private void setUpPager(int season, String circuit){
        PagerAdapter pagerAdapter = new RaceFragmentPagerAdapter(getSupportFragmentManager(), season, circuit);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
