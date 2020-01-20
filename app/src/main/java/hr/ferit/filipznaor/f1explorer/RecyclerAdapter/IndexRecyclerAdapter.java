package hr.ferit.filipznaor.f1explorer.RecyclerAdapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hr.ferit.filipznaor.f1explorer.ViewHolder.IndexViewHolder;

public abstract class IndexRecyclerAdapter<T> extends RecyclerView.Adapter<IndexViewHolder> {

    protected List<T> mData = new ArrayList<>();
    protected List<T> mFilteredData = new ArrayList<>();

    @NonNull
    @Override
    public abstract IndexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(@NonNull IndexViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mFilteredData.size();
    }

    public void addData(List<T> data){
        mData.clear();
        mData.addAll(data);
        mFilteredData.addAll(data);
        notifyDataSetChanged();
    }

    public abstract void filterData(String term);
}
