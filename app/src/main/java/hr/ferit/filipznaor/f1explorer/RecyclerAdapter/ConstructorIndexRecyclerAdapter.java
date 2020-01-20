package hr.ferit.filipznaor.f1explorer.RecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import hr.ferit.filipznaor.f1explorer.POJO.Constructor;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.ViewHolder.ConstructorIndexViewHolder;
import hr.ferit.filipznaor.f1explorer.ViewHolder.IndexViewHolder;

public class ConstructorIndexRecyclerAdapter extends IndexRecyclerAdapter<Constructor> {

    @Override
    @NonNull
    public IndexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_info,parent,false);
        return new ConstructorIndexViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexViewHolder holder, int position) {
        Constructor constructor = mFilteredData.get(position);
        holder.setName(constructor.getName());
        holder.setCountry(constructor.getNationality());
        holder.setId(constructor.getConstructorId());
    }

    public void filterData(String term){
        mFilteredData.clear();
        if(term.trim().isEmpty()){
            mFilteredData.addAll(mData);
        }
        else {
            for (Constructor data : mData) {
                String name = data.getName();
                if (name.toLowerCase().contains(term.trim().toLowerCase())) {
                    mFilteredData.add(data);
                }
            }
        }
        notifyDataSetChanged();
    }
}
