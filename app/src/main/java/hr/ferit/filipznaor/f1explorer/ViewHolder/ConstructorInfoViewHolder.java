package hr.ferit.filipznaor.f1explorer.ViewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hr.ferit.filipznaor.f1explorer.POJO.Results;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.Activity.RaceSummaryActivity;

public class ConstructorInfoViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvRaceName, mTvDriver, mTvGrid, mTvResult, mTvPoints;
    private int mSeason;
    private String mCircuitId, mCountry, mCircuitName;

    public ConstructorInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvRaceName = itemView.findViewById(R.id.tvResultRaceName);
        mTvDriver = itemView.findViewById(R.id.tvResultName);
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

    public void setDriver(List<Results> results){
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        for(i = 0; i < results.size()-1; i++){
            stringBuilder.append(results.get(i).getDriver().getGivenName().substring(0,1)).append('.')
                    .append(results.get(i).getDriver().getFamilyName()).append("\n");
        }
        stringBuilder.append(results.get(i).getDriver().getGivenName().substring(0,1)).append('.')
                .append(results.get(i).getDriver().getFamilyName());
        mTvDriver.setText(stringBuilder.toString());
    }

    public void setGridPosition(List<Results> results){
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        for(i = 0; i < results.size()-1; i++){
            String grid = String.valueOf(results.get(i).getGrid());
            if(grid.equals("0")){grid = "X";}
            stringBuilder.append(grid).append("\n");
        }
        String grid = String.valueOf(results.get(i).getGrid());
        if(grid.equals("0")){grid = "P";}
        stringBuilder.append(grid);
        mTvGrid.setText(stringBuilder.toString());
    }

    public void setResult(List<Results> results){
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        for(i = 0; i < results.size()-1; i++){
            stringBuilder.append(results.get(i).getPositionText()).append("\n");
        }
        stringBuilder.append(results.get(i).getPositionText());
        mTvResult.setText(stringBuilder.toString());
    }

    public void setPoints(List<Results> results){
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        for(i = 0; i < results.size()-1; i++){
            double points = results.get(i).getPoints();
            if(points == (int)points) {
                stringBuilder.append((int)points).append("\n");
            }
            else{
                stringBuilder.append(points).append("\n");
            }
        }
        double points = results.get(i).getPoints();
        if(points == (int)points) {
            stringBuilder.append((int)points);
        }
        else{
            stringBuilder.append(points);
        }
        mTvPoints.setText(stringBuilder.toString());
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
