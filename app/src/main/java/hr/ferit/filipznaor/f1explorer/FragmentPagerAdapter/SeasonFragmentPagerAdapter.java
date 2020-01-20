package hr.ferit.filipznaor.f1explorer.FragmentPagerAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import hr.ferit.filipznaor.f1explorer.Fragment.SeasonInfoFragment;
import hr.ferit.filipznaor.f1explorer.Fragment.SeasonStandingsFragment;
import hr.ferit.filipznaor.f1explorer.Fragment.StandingsType;

public class SeasonFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 3;
    private String mSeason;

    public SeasonFragmentPagerAdapter(FragmentManager fm, String season) {
        super(fm);
        mSeason = season;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return SeasonInfoFragment.newInstance(mSeason);
            case 1: return SeasonStandingsFragment.newInstance(mSeason, StandingsType.DRIVERS);
            default: return SeasonStandingsFragment.newInstance(mSeason, StandingsType.CONSTRUCTORS);
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0: return "Races";
            case 1: return "Driver standings";
            default: return "Constructor standings";
        }
    }
}
