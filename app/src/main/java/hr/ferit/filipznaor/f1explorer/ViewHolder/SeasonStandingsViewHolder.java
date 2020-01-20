package hr.ferit.filipznaor.f1explorer.ViewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hr.ferit.filipznaor.f1explorer.Activity.ConstructorInfoActivity;
import hr.ferit.filipznaor.f1explorer.Activity.DriverInfoActivity;
import hr.ferit.filipznaor.f1explorer.POJO.Constructors;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.Fragment.StandingsType;

public class SeasonStandingsViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvPosition, mTvName, mTvConstructor, mTvPoints, mTvWins;
    private String mDriverId, mDriverName, mConstructorId, mCountry;

    public SeasonStandingsViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvPosition = itemView.findViewById(R.id.tvStandingsPosition);
        mTvName = itemView.findViewById(R.id.tvStandingsName);
        mTvConstructor = itemView.findViewById(R.id.tvStandingsConstructor);
        mTvPoints = itemView.findViewById(R.id.tvStandingsPoints);
        mTvWins = itemView.findViewById(R.id.tvStandingsWins);
    }

    public void setPosition(int position){
        mTvPosition.setText(String.valueOf(position));
    }

    public void setDriverName(String given, String family){
        String name = given.substring(0,1) + '.' + family;
        mTvName.setText(name);
        mDriverName = given + ' ' + family;
    }

    public void setConstructorName(String name){
        mTvName.setText(name);
    }

    public void setConstructor(List<Constructors> constructors){
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        for(i = 0; i < constructors.size()-1; i++){
            stringBuilder.append(constructors.get(i).getName()).append("/");
        }
        if(constructors.size() != 0) {
            stringBuilder.append(constructors.get(i).getName());
        }
        mTvConstructor.setText(stringBuilder.toString());
    }

    public void setPoints(double points){
        if(points == (int)points){
            mTvPoints.setText(String.valueOf((int)points));
        }
        else mTvPoints.setText(String.valueOf(points));
    }

    public void setWins(int wins){
        mTvWins.setText(String.valueOf(wins));
    }

    public void setStandingsType(StandingsType type){
        if(type == StandingsType.DRIVERS){
            mTvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), DriverInfoActivity.class);
                    myIntent.putExtra("id", mDriverId);
                    myIntent.putExtra("name", mDriverName);
                    myIntent.putExtra("country", mCountry);
                    v.getContext().startActivity(myIntent);
                }
            });
        }
        else{
            mTvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), ConstructorInfoActivity.class);
                    myIntent.putExtra("id", mConstructorId);
                    myIntent.putExtra("name", mTvName.getText());
                    myIntent.putExtra("country", mCountry);
                    v.getContext().startActivity(myIntent);
                }
            });
        }
    }

    public void setDriverId(String ID){
        mDriverId = ID;
    }

    public void setConstructorID(String ID){
        mConstructorId = ID;
    }

    public void setCountry(String country){
        mCountry = country;
    }
}
