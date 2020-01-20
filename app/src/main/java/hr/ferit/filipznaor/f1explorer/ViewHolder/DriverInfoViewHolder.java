package hr.ferit.filipznaor.f1explorer.ViewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.Activity.RaceSummaryActivity;

public class DriverInfoViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvRaceName, mTvConstructor, mTvGrid, mTvResult, mTvPoints;
    private int mSeason;
    private String mCircuitId, mCountry, mCircuitName;

    public DriverInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvRaceName = itemView.findViewById(R.id.tvResultRaceName);
        mTvConstructor = itemView.findViewById(R.id.tvResultName);
        mTvGrid = itemView.findViewById(R.id.tvResultGridPosition);
        mTvResult = itemView.findViewById(R.id.tvResultRacePosition);
        mTvPoints = itemView.findViewById(R.id.tvResultPoints);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), RaceSummaryActivity.class);
                myIntent.putExtra("season", mSeason);
                myIntent.putExtra("id", mCircuitId);
                myIntent.putExtra("race", mTvRaceName.getText());
                myIntent.putExtra("country", mCountry);
                myIntent.putExtra("circuit", mCircuitName);
                v.getContext().startActivity(myIntent);
            }
        });
    }

    public void setRaceName(String name){
        name = name.replaceAll("Grand Prix","GP");
        mTvRaceName.setText(name);
    }

    public void setConstructor(String constructor){
        mTvConstructor.setText(constructor);
    }

    public void setGridPosition(int gridPos){
        String grid = String.valueOf(gridPos);
        if(grid.equals("0")){grid = "X";}
        mTvGrid.setText(grid);
    }

    public void setResult(String pos){
        mTvResult.setText(pos);
    }

    public void setPoints(double points){
        if(points == (int)points){
            mTvPoints.setText(String.valueOf((int)points));
        }
        else mTvPoints.setText(String.valueOf(points));
    }

    public void setSeason(int season){
        mSeason = season;
    }

    public void setCircuitId(String ID){
        mCircuitId = ID;
    }

    public void setCountry(String country){
        mCountry = country;
    }

    public void setCircuitName(String name){
        mCircuitName = name;
    }
}
