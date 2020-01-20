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
import hr.ferit.filipznaor.f1explorer.ViewHolder.CircuitInfoViewHolder;

public class CircuitInfoRecyclerAdapter extends RecyclerView.Adapter<CircuitInfoViewHolder> {

    private List<Race> mRaces = new ArrayList<>();

    @NonNull
    @Override
    public CircuitInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_info,parent,false);
        return new CircuitInfoViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull CircuitInfoViewHolder holder, int position) {
        holder.setSeason(mRaces.get(position).getSeason());
        holder.setCircuitId(mRaces.get(position).getCircuit().getCircuitId());
        holder.setRaceName(mRaces.get(position).getRaceName());
        holder.setCountry(mRaces.get(position).getCircuit().getCountry());
        holder.setCircuitName(mRaces.get(position).getCircuit().getName());
    }

    @Override
    public int getItemCount() {
        return mRaces.size();
    }

    public void addSeasons(APIResponse response){
        mRaces.clear();
        List<Race> races = response.getRaces();
        mRaces.addAll(races);
        notifyDataSetChanged();
    }
}
