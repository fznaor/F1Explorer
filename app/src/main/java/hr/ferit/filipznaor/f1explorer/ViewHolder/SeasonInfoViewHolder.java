package hr.ferit.filipznaor.f1explorer.ViewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import hr.ferit.filipznaor.f1explorer.API.CountryParser;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.Activity.RaceSummaryActivity;

public class SeasonInfoViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvRaceInfo;
    private ImageView mIvRaceCountry;
    private int mSeason;
    private String mCircuitId, mRaceName, mCountry, mCircuitName;

    public SeasonInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvRaceInfo = itemView.findViewById(R.id.tvName);
        mIvRaceCountry = itemView.findViewById(R.id.ivCountry);
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

    public void setRaceInfo(int round, String name, int season, String circuitId){
        String raceName = String.valueOf(round) + '.' + ' ' + name;
        mTvRaceInfo.setText(raceName);
        mSeason = season;
        mCircuitId = circuitId;
    }

    public void setRaceCountry(String country){
        String link = "https://www.countryflags.io/" + CountryParser.getCountryCode(country) + "/flat/64.png";
        Picasso.get()
                .load(link)
                .resize(60,70)
                .into(mIvRaceCountry);
        mCountry = country;
    }

    public void setRaceName(String name){
        name = name.replaceAll("Grand Prix","GP");
        mRaceName = name;
    }

    public void setCircuitName(String name){
        mCircuitName = name;
    }
}
