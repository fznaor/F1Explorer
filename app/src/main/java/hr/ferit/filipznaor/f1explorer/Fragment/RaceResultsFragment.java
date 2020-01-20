package hr.ferit.filipznaor.f1explorer.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hr.ferit.filipznaor.f1explorer.API.NetworkUtils;
import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.RecyclerAdapter.RaceResultsRecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RaceResultsFragment extends Fragment {

    private RaceResultsRecyclerAdapter mAdapter;
    private Call<APIResponse> mApiCall;
    private TextView mTvErrorMessage;
    private final static String BUNDLE_SEASON = "season";
    private final static String BUNDLE_CIRCUIT = "circuit";
    private final static String BUNDLE_TYPE = "type";

    public static RaceResultsFragment newInstance(int season, String circuit, ResultsType type){
        RaceResultsFragment fragment = new RaceResultsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_SEASON, season);
        args.putSerializable(BUNDLE_CIRCUIT, circuit);
        args.putSerializable(BUNDLE_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_race_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpLayout(view);
        setUpRecycler(view);
        setUpApiCall();
    }

    private void setUpLayout(View view){
        TextView tvStatusLabel = view.findViewById(R.id.tvRaceResultsStatusLabel);
        TextView tvGridLabel = view.findViewById(R.id.tvRaceResultsGridLabel);
        mTvErrorMessage = view.findViewById(R.id.tvRaceResultsError);
        if(getArguments().getSerializable(BUNDLE_TYPE) == ResultsType.QUALIFYING){
            tvStatusLabel.setText(R.string.stringTimeLabel);
            tvGridLabel.setVisibility(View.GONE);
        }
    }

    private void setUpRecycler(View view){
        RecyclerView rvRaceResults = view.findViewById(R.id.rvRaceResults);
        rvRaceResults.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mAdapter = new RaceResultsRecyclerAdapter(getArguments().getSerializable(BUNDLE_TYPE));
        rvRaceResults.setAdapter(mAdapter);
    }

    private void setUpApiCall(){
        if(getArguments().getSerializable(BUNDLE_TYPE) == ResultsType.RACE) {
            mApiCall = NetworkUtils.getApiInterface().getRaceResults(getArguments().getInt(BUNDLE_SEASON), getArguments().getString(BUNDLE_CIRCUIT), 100);
        }
        else{
            mApiCall = NetworkUtils.getApiInterface().getQualifyingResults(getArguments().getInt(BUNDLE_SEASON), getArguments().getString(BUNDLE_CIRCUIT), 40);
        }
        mApiCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    mAdapter.addRaceResults(response.body());
                    if(mAdapter.getItemCount() == 0){
                        mTvErrorMessage.setText(R.string.stringResultsError);
                        mTvErrorMessage.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                if(getContext() != null) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mApiCall != null)
            mApiCall.cancel();
    }
}
