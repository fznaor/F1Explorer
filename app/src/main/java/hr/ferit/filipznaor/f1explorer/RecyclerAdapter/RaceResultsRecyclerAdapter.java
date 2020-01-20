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
import hr.ferit.filipznaor.f1explorer.POJO.QualifyingResults;
import hr.ferit.filipznaor.f1explorer.POJO.Results;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.Fragment.ResultsType;
import hr.ferit.filipznaor.f1explorer.ViewHolder.RaceResultsViewHolder;

public class RaceResultsRecyclerAdapter extends RecyclerView.Adapter<RaceResultsViewHolder> {

    private List<Results> mRaceResults = new ArrayList<>();
    private List<QualifyingResults> mQualifyingResults = new ArrayList<>();
    private ResultsType mResultsType;

    public RaceResultsRecyclerAdapter(Serializable type){
        mResultsType = (ResultsType)type;
    }

    @NonNull
    @Override
    public RaceResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_race_result, parent,false);
        return new RaceResultsViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull RaceResultsViewHolder holder, int position) {
        if(mResultsType == ResultsType.RACE) {
            Results results = mRaceResults.get(position);
            holder.setPosition(results.getPositionText());
            holder.setDriver(results.getDriver().getGivenName(), results.getDriver().getFamilyName());
            holder.setConstructor(results.getConstructor().getName());
            if (results.getResultsTime() == null) {
                holder.setStatus(results.getStatus());
            } else holder.setStatus(results.getResultsTime().getTime());
            holder.setGrid(results.getGrid());
            holder.setDriverId(results.getDriver().getDriverId());
            holder.setConstructorId(results.getConstructor().getConstructorId());
            holder.setDriverCountry(results.getDriver().getNationality());
            holder.setConstructorCountry(results.getConstructor().getNationality());
        }
        else{
            QualifyingResults results = mQualifyingResults.get(position);
            holder.setPosition(String.valueOf(results.getPosition()));
            holder.setDriver(results.getDriver().getGivenName(), results.getDriver().getFamilyName());
            holder.setConstructor(results.getConstructor().getName());
            if(results.getQ3() != null){
                holder.setStatus(results.getQ3());
            }
            else if(results.getQ2() != null){
                holder.setStatus(results.getQ2());
            }
            else{
                holder.setStatus(results.getQ1());
            }
            holder.setDriverId(results.getDriver().getDriverId());
            holder.setConstructorId(results.getConstructor().getConstructorId());
            holder.setDriverCountry(results.getDriver().getNationality());
            holder.setConstructorCountry(results.getConstructor().getNationality());
            holder.updateLayout();
        }
    }

    @Override
    public int getItemCount() {
        if(mResultsType == ResultsType.RACE) {
            return mRaceResults.size();
        }
        else return mQualifyingResults.size();
    }

    public void addRaceResults(APIResponse response){
        if(mResultsType == ResultsType.RACE) {
            mRaceResults.clear();
            List<Results> results = response.getRaceResults();
            mRaceResults.addAll(results);
        }
        else{
            mQualifyingResults.clear();
            List<QualifyingResults> qualifyingResults = response.getQualifyingResults();
            mQualifyingResults.addAll(qualifyingResults);
        }
        notifyDataSetChanged();
    }
}
