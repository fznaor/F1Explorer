package hr.ferit.filipznaor.f1explorer.RecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import hr.ferit.filipznaor.f1explorer.POJO.Season;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.ViewHolder.IndexViewHolder;
import hr.ferit.filipznaor.f1explorer.ViewHolder.SeasonIndexViewHolder;

public class SeasonIndexRecyclerAdapter extends IndexRecyclerAdapter<Season> {

    @NonNull
    @Override
    public IndexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_info,parent,false);
        return new SeasonIndexViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexViewHolder holder, int position) {
        holder.setName(String.valueOf(mData.get(position).getSeason()));
    }

    @Override
    public void filterData(String term) {

    }
}
