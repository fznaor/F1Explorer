package hr.ferit.filipznaor.f1explorer.FragmentPagerAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import hr.ferit.filipznaor.f1explorer.Fragment.RaceResultsFragment;
import hr.ferit.filipznaor.f1explorer.Fragment.ResultsType;

public class RaceFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 2;
    private int mSeason;
    private String mCircuit;

    public RaceFragmentPagerAdapter(FragmentManager fm, int season, String circuit) {
        super(fm);
        mSeason = season;
        mCircuit = circuit;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return RaceResultsFragment.newInstance(mSeason, mCircuit, ResultsType.RACE);
            default: return RaceResultsFragment.newInstance(mSeason, mCircuit, ResultsType.QUALIFYING);
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
            case 0: return "Race results";
            default: return "Qualifying results";
        }
    }
}
