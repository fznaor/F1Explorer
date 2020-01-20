package hr.ferit.filipznaor.f1explorer.RecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import hr.ferit.filipznaor.f1explorer.POJO.Race;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.ViewHolder.SeasonInfoViewHolder;

public class SeasonInfoRecyclerAdapter extends RecyclerView.Adapter<SeasonInfoViewHolder> {

    private List<Race> mRaces = new ArrayList<>();

    @NonNull
    @Override
    public SeasonInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_info,parent,false);
        return new SeasonInfoViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonInfoViewHolder holder, int position) {
        Race race = mRaces.get(position);
        holder.setRaceInfo(race.getRound(), race.getRaceName(), race.getSeason(),
                race.getCircuit().getCircuitId());
        holder.setRaceCountry(race.getCircuit().getCountry());
        holder.setRaceName(race.getRaceName());
        holder.setCircuitName(race.getCircuit().getName());
    }

    @Override
    public int getItemCount() {
        return mRaces.size();
    }

    public void addRaces(APIResponse response){
        mRaces.clear();
        List<Race> races = response.getRaces();
        mRaces.addAll(races);
        notifyDataSetChanged();
    }
}
