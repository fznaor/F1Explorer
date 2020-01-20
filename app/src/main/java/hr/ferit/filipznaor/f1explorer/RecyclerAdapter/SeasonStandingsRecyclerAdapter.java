package hr.ferit.filipznaor.f1explorer.RecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import hr.ferit.filipznaor.f1explorer.POJO.ConstructorStandings;
import hr.ferit.filipznaor.f1explorer.POJO.DriverStandings;
import hr.ferit.filipznaor.f1explorer.POJO.StandingsLists;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.Fragment.StandingsType;
import hr.ferit.filipznaor.f1explorer.ViewHolder.SeasonStandingsViewHolder;

public class SeasonStandingsRecyclerAdapter extends RecyclerView.Adapter<SeasonStandingsViewHolder> {

    private List<DriverStandings> mDriverStandings = new ArrayList<>();
    private List<ConstructorStandings> mConstructorStandings = new ArrayList<>();
    private StandingsType mStandingsType;

    public SeasonStandingsRecyclerAdapter(Serializable type){
        mStandingsType = (StandingsType)type;
    }

    @NonNull
    @Override
    public SeasonStandingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_standings,parent,false);
        return new SeasonStandingsViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonStandingsViewHolder holder, int position) {
        holder.setStandingsType(mStandingsType);
        if(mStandingsType == StandingsType.DRIVERS) {
            DriverStandings standings = mDriverStandings.get(position);
            holder.setPosition(standings.getPosition());
            holder.setDriverName(standings.getDriver().getGivenName(), standings.getDriver().getFamilyName());
            holder.setConstructor(standings.getConstructors());
            holder.setPoints(standings.getPoints());
            holder.setWins(standings.getWins());
            holder.setDriverId(standings.getDriver().getDriverId());
            holder.setCountry(standings.getDriver().getNationality());
        }
        else {
            ConstructorStandings standings = mConstructorStandings.get(position);
            holder.setPosition(standings.getPosition());
            holder.setConstructorName(standings.getConstructor().getName());
            holder.setPoints(standings.getPoints());
            holder.setWins(standings.getWins());
            holder.setConstructorID(standings.getConstructor().getConstructorId());
            holder.setCountry(standings.getConstructor().getNationality());
        }
    }

    @Override
    public int getItemCount() {
        if(mStandingsType == StandingsType.DRIVERS) {
            return mDriverStandings.size();
        }
        else return mConstructorStandings.size();
    }

    public void addStandings(APIResponse response){
        if(mStandingsType == StandingsType.DRIVERS) {
            mDriverStandings.clear();
            List<StandingsLists> standings = response.getStandings();
            for (StandingsLists list : standings) {
                mDriverStandings.addAll(list.getDriverStandings());
            }
        }
        else{
            mConstructorStandings.clear();
            List<StandingsLists> standings = response.getStandings();
            for (StandingsLists list : standings){
                mConstructorStandings.addAll(list.getConstructorStandings());
            }
        }
        notifyDataSetChanged();
    }
}
