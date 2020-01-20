package hr.ferit.filipznaor.f1explorer.RecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import hr.ferit.filipznaor.f1explorer.ViewHolder.CircuitIndexViewHolder;
import hr.ferit.filipznaor.f1explorer.ViewHolder.IndexViewHolder;
import hr.ferit.filipznaor.f1explorer.POJO.Circuit;
import hr.ferit.filipznaor.f1explorer.R;

public class CircuitIndexRecyclerAdapter extends IndexRecyclerAdapter<Circuit> {

    @Override
    @NonNull
    public IndexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_info,parent,false);
        return new CircuitIndexViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexViewHolder holder, int position) {
        Circuit circuit = mFilteredData.get(position);
        holder.setName(circuit.getName());
        holder.setId(circuit.getCircuitId());
        holder.setCountry(circuit.getCountry());
    }


    public void filterData(String term){
        mFilteredData.clear();
        if(term.trim().isEmpty()){
            mFilteredData.addAll(mData);
        }
        else {
            for (Circuit circuit : mData) {
                String name = circuit.getName();
                if (name.toLowerCase().contains(term.trim().toLowerCase())) {
                    mFilteredData.add(circuit);
                }
            }
        }
        notifyDataSetChanged();
    }
}
