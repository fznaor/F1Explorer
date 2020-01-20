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
import hr.ferit.filipznaor.f1explorer.ViewHolder.ConstructorInfoViewHolder;

public class ConstructorInfoRecyclerAdapter extends RecyclerView.Adapter<ConstructorInfoViewHolder> {

    private List<Race> mRaces = new ArrayList<>();

    @NonNull
    @Override
    public ConstructorInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_result,parent,false);
        return new ConstructorInfoViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConstructorInfoViewHolder holder, int position) {
        Race race = mRaces.get(position);
        holder.setRaceName(race.getRaceName());
        holder.setDriver(race.getRaceResults());
        holder.setGridPosition(race.getRaceResults());
        holder.setResult(race.getRaceResults());
        holder.setPoints(race.getRaceResults());
        holder.setSeason(race.getSeason());
        holder.setCircuitId(race.getCircuit().getCircuitId());
        holder.setCountry(race.getCircuit().getCountry());
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
