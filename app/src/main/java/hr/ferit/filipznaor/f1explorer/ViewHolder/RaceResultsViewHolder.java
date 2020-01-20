package hr.ferit.filipznaor.f1explorer.ViewHolder;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hr.ferit.filipznaor.f1explorer.Activity.ConstructorInfoActivity;
import hr.ferit.filipznaor.f1explorer.Activity.DriverInfoActivity;
import hr.ferit.filipznaor.f1explorer.R;

public class RaceResultsViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvPosition, mTvDriver, mTvConstructor, mTvStatus, mTvGrid;
    private String mDriverId, mConstructorId, mDriverName, mDriverCountry, mConstructorCountry;

    public RaceResultsViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvPosition = itemView.findViewById(R.id.tvRaceResultsPosition);
        mTvDriver = itemView.findViewById(R.id.tvRaceResultsDriver);
        mTvConstructor = itemView.findViewById(R.id.tvRaceResultsConstructor);
        mTvStatus = itemView.findViewById(R.id.tvRaceResultsStatus);
        mTvGrid = itemView.findViewById(R.id.tvRaceResultsGrid);
        mTvDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DriverInfoActivity.class);
                myIntent.putExtra("id", mDriverId);
                myIntent.putExtra("name", mDriverName);
                myIntent.putExtra("country", mDriverCountry);
                v.getContext().startActivity(myIntent);
            }
        });
        mTvConstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), ConstructorInfoActivity.class);
                myIntent.putExtra("id", mConstructorId);
                myIntent.putExtra("name", mTvConstructor.getText());
                myIntent.putExtra("country", mConstructorCountry);
                v.getContext().startActivity(myIntent);
            }
        });
    }

    public void setPosition(String position){
        mTvPosition.setText(position);
    }

    public void setDriver(String given, String family){
        String name =given.substring(0,1)+'.'+family;
        mTvDriver.setText(name);
        mDriverName = given + ' ' + family;
    }

    public void setConstructor(String constructor){
        mTvConstructor.setText(constructor);
    }

    public void setStatus(String status){
        mTvStatus.setText(status);
    }

    public void setGrid(int grid){
        if(grid == 0){
            mTvGrid.setText("X");
        }
        else{
            mTvGrid.setText(String.valueOf(grid));
        }
    }

    public void setDriverId(String ID){
        mDriverId = ID;
    }

    public void setConstructorId(String ID){
        mConstructorId = ID;
    }

    public void updateLayout(){
        mTvGrid.setVisibility(View.GONE);
        mTvStatus.setGravity(Gravity.CENTER);
    }

    public void setDriverCountry(String country){
        mDriverCountry = country;
    }

    public void setConstructorCountry(String country){
        mConstructorCountry = country;
    }
}
