package hr.ferit.filipznaor.f1explorer.RecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import hr.ferit.filipznaor.f1explorer.POJO.Driver;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.ViewHolder.DriverIndexViewHolder;
import hr.ferit.filipznaor.f1explorer.ViewHolder.IndexViewHolder;

public class DriverIndexRecyclerAdapter extends IndexRecyclerAdapter<Driver> {

    @Override
    @NonNull
    public IndexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_info,parent,false);
        return new DriverIndexViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexViewHolder holder, int position) {
        Driver driver = mFilteredData.get(position);
        holder.setName(driver.getGivenName() + ' ' +driver.getFamilyName());
        holder.setCountry(driver.getNationality());
        holder.setId(driver.getDriverId());
    }

    public void filterData(String term){
        mFilteredData.clear();
        if(term.trim().isEmpty()){
            mFilteredData.addAll(mData);
        }
        else {
            for (Driver driver : mData) {
                String name = driver.getGivenName() + ' ' + driver.getFamilyName();
                if (name.toLowerCase().contains(term.trim().toLowerCase())) {
                    mFilteredData.add(driver);
                }
            }
        }
        notifyDataSetChanged();
    }
}
