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
import hr.ferit.filipznaor.f1explorer.POJO.Results;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.ViewHolder.DriverInfoViewHolder;

public class DriverInfoRecyclerAdapter extends RecyclerView.Adapter<DriverInfoViewHolder> {

    private List<Results> mResults = new ArrayList<>();
    private List<Race> mRaces = new ArrayList<>();

    @NonNull
    @Override
    public DriverInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_result,parent,false);
        return new DriverInfoViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverInfoViewHolder holder, int position) {
        Race race = mRaces.get(position);
        Results results = mResults.get(position);
        holder.setResult(results.getPositionText());
        holder.setRaceName(race.getRaceName());
        holder.setConstructor(results.getConstructor().getName());
        holder.setPoints(results.getPoints());
        holder.setGridPosition(results.getGrid());
        holder.setSeason(race.getSeason());
        holder.setCircuitId(race.getCircuit().getCircuitId());
        holder.setCountry(race.getCircuit().getCountry());
        holder.setCircuitName(race.getCircuit().getName());
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public void addData(APIResponse response){
        mRaces.clear();
        mResults.clear();
        List<Race> races = response.getRaces();
        for(Race race : races){
            for(Results res : race.getRaceResults()){
                mResults.add(res);
                mRaces.add(race);
            }
        }
        mRaces.addAll(races);
        notifyDataSetChanged();
    }
}
