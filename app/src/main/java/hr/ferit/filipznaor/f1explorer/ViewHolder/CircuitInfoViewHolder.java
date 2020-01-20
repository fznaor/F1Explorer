package hr.ferit.filipznaor.f1explorer.ViewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.Activity.RaceSummaryActivity;

public class CircuitInfoViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvCircuitInfo;
    private int mSeason;
    private String mCircuitId, mRaceName, mCountry, mCircuitName;

    public CircuitInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvCircuitInfo = itemView.findViewById(R.id.tvName);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), RaceSummaryActivity.class);
                myIntent.putExtra("season", mSeason);
                myIntent.putExtra("id", mCircuitId);
                myIntent.putExtra("race", mRaceName);
                myIntent.putExtra("country", mCountry);
                myIntent.putExtra("circuit", mCircuitName);
                v.getContext().startActivity(myIntent);
            }
        });
    }

    public void setSeason(int season){
        mSeason = season;
    }

    public void setCircuitId(String ID){
        mCircuitId = ID;
    }

    public void setRaceName(String race){
        String name = String.valueOf(mSeason) + ' ' + race;
        mTvCircuitInfo.setText(name);
        race = race.replaceAll("Grand Prix","GP");
        mRaceName = race;
    }

    public void setCountry(String country){
        mCountry = country;
    }

    public void setCircuitName(String name){
        mCircuitName = name;
    }
}
